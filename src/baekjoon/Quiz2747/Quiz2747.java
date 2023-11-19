package baekjoon.Quiz2747;

import java.util.Scanner;

// 전체 시간 복잡도: O(N)
public class Quiz2747 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int[] cache = new int[46];
		int result = fibo(n, cache);
		System.out.println(result);
	}
	private static int fibo(int n, int[] cache) {
		if(n==1 || n==2) return 1;
		if(cache[n] != 0) return cache[n];
		cache[n] = fibo(n-1, cache) + fibo(n-2, cache);
		return cache[n];
	}
}
