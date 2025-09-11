package baekjoon.Quiz11048;

import java.io.*;

/*
Silver2: 이동하기 / [dp]
*/
public class Quiz11048 {
	public static void main(String[] arg) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] nm = br.readLine().split(" ");
		int N = Integer.parseInt(nm[0]);
		int M = Integer.parseInt(nm[1]);
		int[][] map = new int[N + 1][M + 1];
		for(int i=1; i<=N; i++) {
			String[] row = br.readLine().split(" ");
			for(int j=1; j<=M; j++) {
				map[i][j] = Integer.parseInt(row[j-1]);
			}
		}

		int[][] dp = new int[N + 1][M + 1];
		for(int i=1; i<=N; i++) {
			for(int j=1; j<=M; j++) {
				dp[i][j] = Math.max(dp[i-1][j], Math.max(dp[i][j-1], dp[i-1][j-1]));
				dp[i][j] += map[i][j];
			}
		}

		System.out.println(dp[N][M]);
	}
}
