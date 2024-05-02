package baekjoon.Quiz11066;

import java.util.*;
import java.io.*;

public class Quiz11066 {
	static int T, K;
	static int[] arr;
	static int[] acc;
	static int[][] dp;
	static final int INF = 1500000000;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		T = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		while(T-- > 0) {
			K = Integer.parseInt(br.readLine());
			arr = new int[K+1];
			acc = new int[K+1];
			dp = new int[K+1][K+1];
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int i=1; i<=K; i++) {
				arr[i] = Integer.parseInt(st.nextToken());
				acc[i] = arr[i] + acc[i-1];
			}
			for(int i=0; i<=K; i++) {
				for(int j=0; j<=K; j++) {
					dp[i][j] = INF;
					if(i==j) dp[i][j] = 0;
				}
			}
			sb.append(cal(1, K)).append("\n");
		}
		System.out.print(sb);
	}
	private static int cal(int s, int e) {
		if(dp[s][e] < INF) return dp[s][e];
		if(e-s==1) return dp[s][e] = sum(s, e);
		for(int i=s; i<e; i++) {
			dp[s][e] = Math.min(dp[s][e], cal(s, i) + cal(i+1, e) + sum(s, e));
		}
		return dp[s][e];
	}
	private static int sum(int s, int e) {
		return acc[e] - acc[s-1];
	}
}
