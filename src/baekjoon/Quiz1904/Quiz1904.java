package baekjoon.Quiz1904;

import java.io.*;

/*
Silver3: 01타일 / [dp]
*/
public class Quiz1904 {

	private static int MOD = 15746;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		if(N == 1) {
			System.out.println(1);
			return;
		}

		int[] dp = new int[N + 1];
		dp[1] = 1;
		dp[2] = 2;
		for(int i=3; i<=N; i++) {
			dp[i] = (dp[i-1] + dp[i-2]) % MOD;
		}

		System.out.println(dp[N]);
	}
}
