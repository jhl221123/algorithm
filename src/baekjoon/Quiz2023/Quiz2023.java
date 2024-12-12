package baekjoon.Quiz2023;

import java.io.*;

public class Quiz2023 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();

		dfs(0, N, sb);
		System.out.print(sb);
	}

	private static void dfs(int num, int e, StringBuilder sb) {
		if(e == 0) {
			sb.append(num).append("\n");
			return;
		}

		for(int i=1; i<10; i++) {
			int temp = num * 10 + i;
			if(isPrime(temp)) dfs(temp, e-1, sb);
		}
	}

	private static boolean isPrime(int num) {
		if(num < 2) return false;
		for(int i=2; i*i<=num; i++) {
			if(num % i == 0) return false;
		}
		return true;
	}
}
