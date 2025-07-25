package baekjoon.Quiz2579;

import java.io.*;

/*
Silver3: 계단 오르기 / [dp]
*/
public class Quiz2579 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[] arr = new int[N + 1];
		for(int i=1; i<=N; i++) {
			arr[i] = Integer.parseInt(br.readLine());
		}
		if(N == 1) {
			System.out.println(arr[1]);
			return;
		} else if(N == 2) {
			System.out.println(arr[1] + arr[2]);
			return;
		}

		int[][] dp = new int[N + 1][2];
		dp[1][0] = arr[1];
		dp[2][0] = arr[2];
		dp[2][1] = arr[1] + arr[2];
		for(int i=3; i<=N; i++) {
			dp[i][0] = Math.max(dp[i-2][0], dp[i-2][1]) + arr[i];
			dp[i][1] = dp[i-1][0] + arr[i];
		}

		System.out.println(Math.max(dp[N][0], dp[N][1]));
	}
}
