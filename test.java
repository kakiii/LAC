import java.util.Arrays;

public class test {

	public static int LSC(int[] A, int n) {
		return Main201521233.LongestComb(A, n);

	}

	public static void main(String[] args) {
		int[] testA = {10,11,2,3,1,7,2,5,10,11};
		System.out.println("Input array is"+Arrays.toString(testA));
		System.out.println("Number for this Array is: " + LSC(testA, testA.length));
	}

}
