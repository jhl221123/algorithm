package baekjoon.Quiz2662;

import java.io.*;

/*
Gold2: 기업투자 / [dp]
*/
public class Quiz2662 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] NM = br.readLine().split(" ");
		int N = Integer.parseInt(NM[0]);
		int M = Integer.parseInt(NM[1]);
		int[][] info = new int[M + 1][N + 1];
		for(int i=1; i<=N; i++) {
			String[] m = br.readLine().split(" ");
			for(int j=1; j<=M; j++) {
				info[j][i] = Integer.parseInt(m[j]);
			}
		}

		int[][] dp = new int[M + 1][N + 1];
		int[][] invest = new int[M + 1][N + 1];
		for(int i=1; i<=M; i++) {
			for(int j=0; j<=N; j++) {
				for(int k=0; k<=j; k++) {
					if(dp[i][j] < dp[i - 1][j - k] + info[i][k]) {
						dp[i][j] = dp[i - 1][j - k] + info[i][k];
						invest[i][j] = k;
					}

				}
			}
		}

		StringBuilder sb = new StringBuilder();
		sb.append(dp[M][N]).append("\n");

		int[] path = new int[M + 1];
		int remainingAmount = N;
		for (int i = M; i > 0; i--) {
			int investedAmount = invest[i][remainingAmount];
			path[i] = investedAmount;
			remainingAmount -= investedAmount;
		}

		for (int i = 1; i <= M; i++) {
			sb.append(path[i]).append(" ");
		}
		System.out.println(sb.toString().trim());
	}
}
