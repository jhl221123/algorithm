package baekjoon.Quiz14855;

import java.io.*;

/*
Gold4: 만두 가게 사장 박승원 / [dp]
dp[m + 1][n + 1]
dp[0][2] = 1, dp[0][3] = 1, dp[0][4] = 2 ...
dp[1][2] = 100, dp[1][4] = 200, dp[1][6] = 300, dp[1][8] = 301, dp[1][10] = 302
dp[2][1] = 10, dp[2][2] = 100, dp[2][3] = 110 ...
*/
public class Quiz14855 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] nmcd = br.readLine().split(" ");
		int n = Integer.parseInt(nmcd[0]);
		int m = Integer.parseInt(nmcd[1]);
		int c = Integer.parseInt(nmcd[2]);
		int d = Integer.parseInt(nmcd[3]);
		int[][] dumpling = new int[m + 1][4];

		for(int i=1; i<=m; i++) {
			String[] abcd = br.readLine().split(" ");
			for(int j=0; j<4; j++) {
				dumpling[i][j] = Integer.parseInt(abcd[j]);
			}
		}

		int[][] dp = new int[m + 1][n + 1];
		for(int i=c; i<=n; i++) {
			dp[0][i] = dp[0][i - c] + d;
		}

		for(int i=1; i<=m; i++) {
			for(int j=0; j<=n; j++) {
				dp[i][j] = dp[i - 1][j];
				int iter = dumpling[i][0] / dumpling[i][1];

				for(int k=1; k<=iter; k++) {
					int neededFlour = dumpling[i][2] * k;
					if(neededFlour <= j) {
						dp[i][j] = Math.max(dp[i][j], dp[i - 1][j - neededFlour] + (dumpling[i][3] * k));
					}
				}
			}
		}

		System.out.println(dp[m][n]);
	}
}
