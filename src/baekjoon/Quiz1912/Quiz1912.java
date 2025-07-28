package baekjoon.Quiz1912;

import java.io.*;

/*
Silver2: 연속합 / [dp]
*/
public class Quiz1912 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		String[] strs = br.readLine().split(" ");
		int[] arr = new int[N + 1];
		for(int i=1; i<=N; i++) {
			arr[i] = Integer.parseInt(strs[i-1]);
		}
		int[] dp = new int[N + 1];
		for(int i=1; i<=N; i++) {
			dp[i] = Math.max(arr[i], dp[i - 1] + arr[i]);
		}

		int max = -200_000_000;
		for(int i=1; i<=N; i++) {
			max = Math.max(max, dp[i]);
		}
		System.out.println(max);
	}
}
