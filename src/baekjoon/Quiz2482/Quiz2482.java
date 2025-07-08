package baekjoon.Quiz2482;

import java.io.*;

/*
Gold3: 색상환 / [dp]
*/
public class Quiz2482 {

	private static final int MOD = 1_000_000_003;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int K = Integer.parseInt(br.readLine());
		int[][] dp = new int[N + 1][N + 1];
		for(int i=0; i<=N; i++) {
			dp[i][1] = i;
			dp[i][0] = 1;
		}

		for(int i=3; i<=N; i++) {
			for(int j=1; j<=(i + 1) / 2; j++) {
				dp[i][j] = (dp[i - 2][j - 1] + dp[i - 1][j]) % MOD;
			}
		}

		System.out.println((dp[N - 3][K - 1] + dp[N - 1][K]) % MOD);
	}
}
