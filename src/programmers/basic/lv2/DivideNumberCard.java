package programmers.basic.lv2;

public class DivideNumberCard {
	public static void main(String[] args) {
		int result = solution(new int[] {14, 35, 119}, new int[] {18, 30, 102});// 7
		System.out.println(result);
	}
	public static int solution(int[] arrayA, int[] arrayB) {
		return Math.max(getMax(arrayA, arrayB), getMax(arrayB, arrayA));
	}
	private static int getMax(int[] arr1, int[] arr2) {
		int gcd = getGcdInArr(arr1);
		for(int num : arr2) {
			if(num % gcd == 0) return 0;
		}
		return gcd;
	}
	private static int getGcdInArr(int[] arr) {
		int gcd = arr[0];
		int next = 0;
		for(int i=0; i<arr.length; i++) {
			next = arr[i];
			gcd = getGcd(Math.max(gcd, next), Math.min(gcd, next));
		}
		return gcd;
	}
	private static int getGcd(int num1, int num2) {
		if(num2 == 0) return num1;
		return getGcd(num2, num1 % num2);
	}
}
