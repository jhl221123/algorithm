package baekjoon.Quiz14728;

import java.io.*;

/*
Gold5: 벼락치기 / [dp]
*/
public class Quiz14728 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] nt = br.readLine().split(" ");
		int N = Integer.parseInt(nt[0]);
		int T = Integer.parseInt(nt[1]);

		int[] times = new int[N + 1];
		int[] scores = new int[N + 1];
		for(int i=1; i<=N; i++) {
			String[] ks = br.readLine().split(" ");
			times[i] = Integer.parseInt(ks[0]);
			scores[i] = Integer.parseInt(ks[1]);
		}

		int[][] dp = new int[N + 1][T + 1];
		for(int i=1; i<=N; i++) {
			for(int j=0; j<=T; j++) {
				if(times[i] <= j) {
					dp[i][j] = Math.max(dp[i-1][j], dp[i-1][j - times[i]] + scores[i]);
				} else {
					dp[i][j] = dp[i-1][j];
				}
			}
		}

		System.out.println(dp[N][T]);
	}
}
