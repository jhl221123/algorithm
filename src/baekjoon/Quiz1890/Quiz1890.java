package baekjoon.Quiz1890;

import java.io.*;

/*
Silver1: 점프 / [dp]
*/
public class Quiz1890 {

	private static int N;
	private static int[][] map;
	private static long[][] dp;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		map = new int[N][N];
		for(int i=0; i<N; i++) {
			String[] row = br.readLine().split(" ");
			for(int j=0; j<N; j++) {
				map[i][j] = Integer.parseInt(row[j]);
			}
		}

		dp = new long[N][N];
		dfs(0, 0);
		System.out.println(dp[0][0]);
	}

	private static long dfs(int r, int c) {
		if(dp[r][c] > 0) return dp[r][c];
		if(r == N -1 && c == N -1) return 1;
		if(map[r][c] == 0) return 0;
		int next = r + map[r][c];
		if(next < N) {
			dp[r][c] += dfs(r + map[r][c], c);
		}
		next = c + map[r][c];
		if(next < N) {
			dp[r][c] += dfs(r, c + map[r][c]);
		}
		return dp[r][c];
	}
}
