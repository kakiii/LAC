import java.io.File;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Arrays;

public class Main201521233 {

	public static ArrayList<String> ReadData(String pathname) {
		ArrayList<String> strlist = new ArrayList<String>();
		try {

			File filename = new File(pathname);
			InputStreamReader reader = new InputStreamReader(new FileInputStream(filename));
			BufferedReader br = new BufferedReader(reader);
			int j = 0;
			String line = "";
			while ((line = br.readLine()) != null) {
				strlist.add(line);
			}
			return strlist;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return strlist;
	}

	public static ArrayList<ArrayList<Integer>> DataWash(ArrayList<String> Datalist) {
		ArrayList<ArrayList<Integer>> AIS = new ArrayList<ArrayList<Integer>>();
		ArrayList<Integer> IS = new ArrayList<Integer>();
		for (int i = 0; i < Datalist.size(); i++) {
			String Tstr = Datalist.get(i);
			if (Tstr.equals("A") == false) {
				IS.add(Integer.parseInt(Tstr));
			}
			if (Tstr.equals("A")) {
				ArrayList<Integer> elemAIS = new ArrayList<Integer>(IS);
				AIS.add(elemAIS);
				IS.clear();
			}
		}
		return AIS;
	}

	// %%%%%%%%%%%%%%%%%%%%%%% Procedure LongestComb() that will contain your code:

	public static int LongestComb(int[] A, int n) {

		/*
		 * Input is array of input sequence (a_1 <= a_2 <= ... <= a_n) as
		 * A[0,1,...,n-1], that is, a_1 = A[0], a_2 = A[1], ..., a_n = A[n-1]. n =
		 * number of integers in sequence A This procedure should return the value of
		 * the longest anchored comb (>= 1) or 0 if there is no anchored comb. It should
		 * NOT return the respective anchored comb!
		 */

		/*
		 * Here should be the description of the main ideas of your solution: describe
		 * the recursive relation used in your dynamic programming solution and outline
		 * how you implement it sequentially in your code below.
		 *
		 * SOME NOTATION: s.t. is abbreviation of "such that" a <= b (a is smaller than
		 * or equal to b) a >= b (a is greater than or equal to b) max [ a , b ] denotes
		 * the larger among a and b Given an array T[1,...,n] then M = max_{k: some
		 * condition C(k) holds} [ T[k] ], M denotes the largest value T[k] over all
		 * indices k such that condition C(k) holds.
		 *
		 * Definition:
		 * In a tooth there are two numbers, say A[i] and A[i+1] with the property of
		 * A[i] < A[i+1], and we call the first number, A[i], as "valley", and the last name being "peak".
		 *
		 * 		 *
		 * recursive code for this problem:
		 *
		 * start <- 0
		 * end <- length of croppedInput
		 * lastPeak <- peak of left anchor
		 * longestComb(start,end,lastPeak)
		 * if start = end :
		 * 		return 0
		 * if end - start = 1:
		 * 		if A[start] < A[end]:
		 * 			lastPeak <- A[end]
		 * 			if this is the first possible tooth:
		 * 				if A[start] is less than the peak of left anchor:
		 * 					return 1 , and skip this step in the future
		 * 				else return 0
		 * 			else if A[start] < lastPeak:
		 * 					return 1
		 * 				else return 0
		 * else:
		 * 		return  max{longestComb(start,start+1,lastPeak)+longestComb(start+1,end,lastPeak),
		 * 				longestComb(start,start+2,lastPeak)+longestComb(start+2,end,lastPeak),
		 * 				......
		 * 				longestComb(start,end-1,lastPeak)+longestComb(end-1,end,lastPeak),}
		 *
		 *	if lastPeak < valley of
		 *
		 *
		 */

		/*
		 * Here should be the statement and description of the running time analysis of
		 * your implementation: describe how the running time depends on n (length of
		 * the input sequence), and give short argument.
		 *
		 * ..... .....
		 */

		if (n == 0) {
			return 0;
		}

		boolean haveAnchor = false, haveComb = false;
		int start = 0, end = A.length - 1, num = 0, dist = 0;
		for (int i = 0; i < A.length; i++) {
			for (int j = A.length - 2; j > i; j--) {
				if (!haveComb && (A[i] < A[i + 1] || A[j] < A[j + 1])) {
					haveComb = true;
				}
				if (A[i] == A[j] && A[i] < A[i + 1] && A[j] < A[j + 1]) {
					//System.out.println(i+" "+j);
					if (!haveAnchor) {
						haveAnchor = true;
						num = 2;
					}
					//System.out.println("dist= " + (j - i >= dist ));
					if (dist <= j - i) {
						dist = j - i;
						start = i;
						end = j;
					} else {
						if (dist - 4 <= j - i && A[j+1] < A[end]) {
							boolean findPeak = true, isPossible = true;
							for (int k = i + 1; k < j + 2; k++) {
								if (findPeak) {
									if (A[k - 1] >= A[k]) {
										isPossible = false;
										break;
									} else {
										findPeak = false;
									}
								} else {
									if (A[k - 1] <= A[k]) {
										isPossible = false;
										break;
									} else {
										findPeak = true;
									}
								}
							}
							if (isPossible) {
								dist = j - i;
								start = i;
								end = j;
							}
						}
					}
				}
			}
		}
		if (!haveAnchor) {
			return haveComb ? 1 : 0;
		} else {
			int length = end - start - 2;
			System.out.println("Start with A[" + start + "] = " + A[start] + ", to A[" + end + "] = " + A[end]);
			int[] croppedInput = new int[length];
			System.arraycopy(A, start + 2, croppedInput, 0, length);
			int lastPeak = A[start + 1];
			System.out.println(Arrays.toString(croppedInput));
			int[][] dp = new int[length][length];
			boolean findFirstPeak = true;
			int bestVal = 0;

			for (int i = 0; i < dp.length; i++) {
				for (int j = i; j >= 0; j--) {
					if (i == j) dp[j][i] = 0;
					else if (i - j == 1) {
						if (croppedInput[j] < croppedInput[i]) {
							//System.out.println("first peak is "+lastPeak+" and A["+j+"] is "+A[j]);
							if (findFirstPeak) {
								if (croppedInput[j] < A[start + 1]) {
									dp[j][i] = 1;
									findFirstPeak = false;
								} else {
									dp[j][i] = 0;
								}
							} else dp[j][i] = croppedInput[j] < lastPeak ? 1 : 0;
							lastPeak = croppedInput[i];
						}
					} else {
						for (int k = j; k < i; k++) {
							dp[j][i] = Math.max(dp[j][k] + dp[k + 1][i], dp[j][i]);
						}
						//System.out.println(bestVal);
					}
					bestVal = Math.max(bestVal, dp[j][i]);
					// if (dp[j][i]==1&&i-j==1) System.out.println(j+" "+i);
					//System.out.println("dp[" + j + "][" + i + "]= " + dp[j][i]);
				}
			}

			if (lastPeak < A[end]) bestVal--;
			num += bestVal;

		}
		return num;

	} // end of procedure LongestComb()

	public static int Computation(ArrayList<Integer> Instance, int opt) {
		// opt=1 here means option 1 as in -opt1, and opt=2 means option 2 as in -opt2
		int NGounp = 0;
		int size = 0;
		int Correct = 0;
		size = Instance.size();

		int[] A = new int[size - opt];
		// NGounp = Integer.parseInt((String)Instance.get(0));
		NGounp = Instance.get(0); // NOTE: NGounp = 0 always, as it is NOT used for any purpose
		// It is just the first "0" in the first line before instance starts.
		for (int i = opt; i < size; i++) {
			A[i - opt] = Instance.get(i);
		}
		int Size = A.length;
		if (NGounp > Size) {
			return (-1);
		} else {
			// Size
			int R = LongestComb(A, Size);
			return (R);
		}
	}

	public static String Test;

	public static void main(String[] args) {
		if (args.length == 0) {
			String msg = "Rerun with flag: " + "\n\t -opt1 to get input from dataOne.txt "
					+ "\n\t -opt2 to check results in dataTwo.txt";
			System.out.println(msg);
			return;
		}
		Test = args[0];
		int opt = 2;
		String pathname = "dataTwo.txt";
		if (Test.equals("-opt1")) {
			opt = 1;
			pathname = "dataOne.txt";
		}

		ArrayList<String> Datalist = new ArrayList<String>();
		Datalist = ReadData(pathname);
		ArrayList<ArrayList<Integer>> AIS = DataWash(Datalist);

		int Nins = AIS.size();
		int NGounp = 0;
		int size = 0;
		if (Test.equals("-opt1")) {
			for (int t = 0; t < Nins; t++) {
				int Correct = 0;
				int Result = 0;
				ArrayList<Integer> Instance = AIS.get(t);
				Result = Computation(Instance, opt);

				System.out.println(Result);
			}
		} else {
			int wrong_no = 0;
			int Correct = 0;
			int Result = 0;
			ArrayList<Integer> Wrong = new ArrayList<Integer>();
			for (int t = 0; t < Nins; t++) {
				ArrayList<Integer> Instance = AIS.get(t);
				System.out.println("Input array " + (t + 1) + " is: " + Instance.subList(2, Instance.size()));
				Result = Computation(Instance, opt);
				// System.out.println(Result);

				Correct = Instance.get(1);

				System.out.println("Correct answer should be " + (Correct) + ", and my answer is " + Result);
				if (Correct != Result) {
					Wrong.add(t + 1);
					wrong_no = wrong_no + 1;
				}
			}
			if (Wrong.size() > 0) {
				System.out.println("Index of wrong instance(s):");
			}
			for (int j = 0; j < Wrong.size(); j++) {
				System.out.print(Wrong.get(j));
				System.out.print(",");

				/*
				 * ArrayList Instance = (ArrayList)Wrong.get(j); for (int k = 0; k <
				 * Instance.size(); k++){ System.out.println(Instance.get(k)); }
				 */
			}
			System.out.println("");
			System.out.println("Percentage of correct answers:");
			System.out.println(((double) (Nins - wrong_no) / (double) Nins) * 100);

		}

	}
}