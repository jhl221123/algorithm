package baekjoon.Quiz13156;

import java.io.*;
import java.util.*;

/*
Gold5: Selling CPUs / [dp]
1. 현재 상인에게 판매 가능한 모든 상황을 고려해서 최대값을 갱신힌다.
*/
public class Quiz13156 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] cm = br.readLine().split(" ");
		int c = Integer.parseInt(cm[0]);
		int m = Integer.parseInt(cm[1]);
		int[][] price = new int[m][c + 1];
		for(int i=0; i<m; i++) {
			String[] p = br.readLine().split(" ");
			for(int j=1; j<=c; j++) {
				price[i][j] = Integer.parseInt(p[j - 1]);
			}
		}

		int[][] dp = new int[m + 1][c + 1];
		for(int[] row : dp) {
			Arrays.fill(row, -1);
		}
		dp[0][c] = 0;

		for(int i=0; i<m; i++) {
			for(int j=0; j<=c; j++) {
				if(dp[i][j] == -1) continue;
				for(int k=0; k<=j; k++) {
					dp[i+1][j-k] = Math.max(dp[i+1][j-k], dp[i][j] + price[i][k]);
				}
			}
		}

		int max = 0;
		for(int i=0; i<=c; i++) {
			max = Math.max(max, dp[m][i]);
		}
		System.out.println(max);
	}
}
