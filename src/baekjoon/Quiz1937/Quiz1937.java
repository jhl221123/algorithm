package baekjoon.Quiz1937;

import java.io.*;

/*
Gold3: 욕심쟁이 판다 / [dp]
*/
public class Quiz1937 {

	private static final int[] dy = {-1, 1, 0, 0};
	private static final int[] dx = {0, 0, -1, 1};

	private static int N;
	private static int[][] map;
	private static int[][] dp;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		map = new int[N][N];
		for(int i=0; i<N; i++) {
			String[] cols = br.readLine().split(" ");
			for(int j=0; j<N; j++) {
				map[i][j] = Integer.parseInt(cols[j]);
			}
		}

		dp = new int[N][N];
		int max = 0;
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				max = Math.max(max, search(i, j));
			}
		}
		System.out.println(max);
	}

	private static int search(int row, int col) {
		if(dp[row][col] > 0) return dp[row][col];

		dp[row][col] = 1;
		for(int d=0; d<4; d++) {
			int nr = row + dy[d];
			int nc = col + dx[d];
			if(nr < 0 || nc < 0 || nr >= N || nc >= N) continue;
			if(map[nr][nc] <= map[row][col]) continue;
			dp[row][col] = Math.max(dp[row][col], search(nr, nc) + 1);
		}

		return dp[row][col];
	}
}
