package baekjoon.Quiz2225;

import java.util.*;
import java.io.*;

public class Quiz2225 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		long[][] dp = new long[N+1][K+1];
		for(int i=1; i<=N; i++) {
			dp[i][1] = 1;
		}

		for(int i=1; i<=K; i++) {
			dp[1][i] = i;
		}

		for(int i=2; i<=N; i++) {
			for(int j=2; j<=K; j++) {
				dp[i][j] = (dp[i-1][j] + dp[i][j-1])  % 1_000_000_000;
			}
		}

		System.out.println(dp[N][K] % 1_000_000_000);
	}
}
