import java.util.Arrays;

public class test {

	public static int LSC(int[] A, int n) {
		return Main201521233.LongestComb(A, n);

	}

	public static void main(String[] args) {
		int[] testA = {10, 11, 2, 3, 1, 7, 2, 5, 10, 11};
		int[] testB = {2, 2,2,2,2,2};
		int[] testC = {10,11,9,100,99,120,9,101,10,11};
		int[] testE= {9,12,1,3,2,3,2,9,1,12,9,11};
		int[] testF= {1,2,1,2,1,4,2,4,1,7,3,2,1,3};
		int[] testG = {3,7,3,7,3,4,7,9,1,4,3,7};
		int[] testH ={3,3,4,7,1,7,3,3,3,7,1,4,7};
		extracted(testB);
	}

	private static void extracted(int[] testA) {
		System.out.println("Input array is" + Arrays.toString(testA));
		System.out.println("Number for this Array is: " + LSC(testA, testA.length));
	}

}
