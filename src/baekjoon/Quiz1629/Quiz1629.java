package baekjoon.Quiz1629;

import java.util.*;

public class Quiz1629 {
	static long A;
	static long B;
	static long C;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		A = sc.nextInt();
		B = sc.nextInt();
		C = sc.nextInt();
		long ans = dfs(B);
		System.out.println(ans);
	}
	private static long dfs(long base) {
		if(base == 1) return A % C;
		long half = dfs(base/2);
		if(base % 2 == 0) {
			return (half * half) % C;
		}
		if(base % 2 == 1) {
			return ((half * half) % C * (A % C)) % C;
		}
		return -1;
	}
}
