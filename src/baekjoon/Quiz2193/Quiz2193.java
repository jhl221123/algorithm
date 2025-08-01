package baekjoon.Quiz2193;

import java.io.*;

/*
Silver3: 이친수 / [dp]
*/
public class Quiz2193 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		long[][] dp = new long[N + 1][2];
		dp[1][1] = 1;
		if(N == 1) {
			System.out.println(1);
			return;
		}
		dp[2][0] = 1;

		for(int i=3; i<=N; i++) {
			dp[i][1] += dp[i-1][0];
			dp[i][0] += dp[i-1][0] + dp[i-1][1];
		}

		System.out.println(dp[N][0] + dp[N][1]);
	}
}
