package baekjoon.Quiz2854;

import java.util.*;
import java.io.*;

public class Quiz2854 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		long[] statics = new long[N+1];
		long[] dynamics = new long[N+1];
		StringTokenizer st = new StringTokenizer(br.readLine());;
		for(int i=1; i<=N; i++) {
			statics[i] = Integer.parseInt(st.nextToken());
		}
		st = new StringTokenizer(br.readLine());;
		for(int i=1; i<=N-1; i++) {
			dynamics[i] = Integer.parseInt(st.nextToken());
		}
		long[][] dp = new long[3][N+1];
		int MOD = 1000000007;
		dp[0][0] = 1;
		dp[1][0] = 0;
		dp[2][0] = 0;
		dp[0][1] = statics[1];
		dp[1][1] = dynamics[1];
		dp[2][1] = 0;
		for(int i=2; i<=N; i++) {
			dp[0][i] = (dp[0][i-1] + dp[1][i-1] + dp[2][i-1]) * statics[i];
			dp[0][i] %= MOD;
			dp[1][i] = (dp[0][i-1] + dp[1][i-1] + dp[2][i-1]) * dynamics[i];
			dp[1][i] %= MOD;
			dp[2][i] = (dp[0][i-1] + dp[2][i-1]) * dynamics[i-1] % MOD
				+ (dynamics[i-1] * (dynamics[i-1] -1) % MOD)
				* (dp[0][i-2] + dp[1][i-2] + dp[2][i-2]) % MOD;
		}
		System.out.println((dp[0][N] + dp[1][N] + dp[2][N])%MOD);
	}
}
