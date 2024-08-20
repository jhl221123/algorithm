package baekjoon.Quiz9084;

import java.util.*;
import java.io.*;

public class Quiz9084 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		while(T-- > 0) {
			int N = Integer.parseInt(br.readLine());
			int[] coins = new int[N + 1];
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int i=1; i<=N; i++) {
				coins[i] = Integer.parseInt(st.nextToken());
			}
			int M = Integer.parseInt(br.readLine());
			int[] dp = new int[M + 1];

			for(int i=1; i<=N; i++) {
				for(int j=1; j<=M; j++) {
					if(j - coins[i] > 0) {
						dp[j] = dp[j] + dp[j - coins[i]];
					} else if(j - coins[i] == 0) {
						dp[j]++;
					}
				}
			}

			sb.append(dp[M]).append("\n");
		}
		System.out.println(sb);
	}
}
