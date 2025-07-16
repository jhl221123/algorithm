package baekjoon.Quiz11726;

import java.io.*;

/*
Silver3: 2×n 타일링 / [dp]
*/
public class Quiz11726 {

	private static final int MOD = 10_007;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[] dp = new int[N + 1];
		dp[0] = 1;
		dp[1] = 1;
		for(int i=2; i<=N; i++) {
			dp[i] = (dp[i - 1] + dp[i - 2]) % MOD;
		}

		System.out.println(dp[N]);
	}
}
