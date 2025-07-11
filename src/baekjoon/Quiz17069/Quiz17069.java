package baekjoon.Quiz17069;

import java.io.*;

/*
Gold4: 파이프 옮기기 2 / [dp]
dp[r][c][l] = dp[r][c-1][l] + dp[r][c-1][li]
dp[r][c][t] = dp[r-1][c][t] + dp[r-1][c][li]
dp[r][c][li] = dp[r-1][c-1][l] + dp[r-1][c-1][t] + dp[r-1][c-1][li]
if(wall[r][c]) continue;
*/
public class Quiz17069 {

	private static final int LEFT = 0;
	private static final int TOP = 1;
	private static final int LEAN = 2;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		boolean[][] wall = new boolean[N + 1][N + 1];
		for(int i=1; i<=N; i++) {
			String[] info = br.readLine().split(" ");
			for(int j=1; j<=N; j++) {
				if("1".equals(info[j - 1])) {
					wall[i][j] = true;
				}
			}
		}

		long[][][] dp = new long[N + 1][N + 1][3];
		dp[1][2][LEFT] = 1;
		for(int r=1; r<=N; r++) {
			for(int c=3; c<=N; c++) {
				if(wall[r][c]) continue;
				dp[r][c][LEFT] = dp[r][c-1][LEFT] + dp[r][c-1][LEAN];
				dp[r][c][TOP] = dp[r-1][c][TOP] + dp[r-1][c][LEAN];
				if(wall[r-1][c] || wall[r][c-1]) continue;
				dp[r][c][LEAN] = dp[r-1][c-1][LEFT] + dp[r-1][c-1][TOP] + dp[r-1][c-1][LEAN];
			}
		}

		System.out.println(dp[N][N][LEFT] + dp[N][N][TOP] + dp[N][N][LEAN]);
	}
}
