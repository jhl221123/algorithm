package baekjoon.Quiz9184;

import java.io.*;
import java.util.*;

/*
Silver2: 신나는 함수 실행 / [dp, dfs]
*/
public class Quiz9184 {

	private static final int INF = -1000000000;
	private static int[][][] dp = new int[101][101][101];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		for(int i=0; i<101; i++) {
			for(int j=0; j<101; j++) {
				Arrays.fill(dp[i][j], INF);
			}
		}

		StringBuilder sb = new StringBuilder();
		while(true) {
			String[] abc = br.readLine().split(" ");
			int a = Integer.parseInt(abc[0]) + 50;
			int b = Integer.parseInt(abc[1]) + 50;
			int c = Integer.parseInt(abc[2]) + 50;
			if(a==49 && b==49 && c==49) {
				break;
			}

			sb.append("w(")
				.append(a - 50).append(",").append(" ")
				.append(b - 50).append(",").append(" ")
				.append(c - 50).append(")").append(" ")
				.append("=").append(" ");
			sb.append(w(a, b, c)).append("\n");
		}
		System.out.print(sb);
	}

	private static int w(int a, int b, int c) {
		if(dp[a][b][c] > INF) {
			return dp[a][b][c];
		}

		if(a <= 50 || b <= 50 || c <= 50) {
			return dp[a][b][c] = 1;
		}

		if(a > 70 || b > 70 || c > 70) {
			return dp[a][b][c] = w(70, 70, 70);
		}

		if(a < b && b < c) {
			return dp[a][b][c] = w(a, b, c-1) + w(a, b-1, c-1) - w(a, b-1, c);
		}

		return dp[a][b][c] = w(a-1, b, c) + w(a-1, b-1, c) + w(a-1, b, c-1) - w(a-1, b-1, c-1);
	}
}
