package baekjoon.Quiz17404;

import java.util.*;
import java.io.*;

public class Quiz17404 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[][][] dp = new int[N][3][3];
		for(int i=0; i<3; i++) {
			dp[0][0][i] = 1000000000;
			dp[0][1][i] = 1000000000;
			dp[0][2][i] = 1000000000;
		}
		for(int i=0; i<N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			if(i==0) {
				dp[i][0][0] = a;
				dp[i][1][1] = b;
				dp[i][2][2] = c;
			}else {
				for(int j=0; j<3; j++) {
					dp[i][0][j] = a;
				}
				for(int j=0; j<3; j++) {
					dp[i][1][j] = b;
				}
				for(int j=0; j<3; j++) {
					dp[i][2][j] = c;
				}
			}
		}
		for(int i=1; i<N; i++) {
			dp[i][0][0] += Math.min(dp[i - 1][1][0], dp[i - 1][2][0]);
			dp[i][0][1] += Math.min(dp[i - 1][1][1], dp[i - 1][2][1]);
			dp[i][0][2] += Math.min(dp[i - 1][1][2], dp[i - 1][2][2]);

			dp[i][1][0] += Math.min(dp[i - 1][0][0], dp[i - 1][2][0]);
			dp[i][1][1] += Math.min(dp[i - 1][0][1], dp[i - 1][2][1]);
			dp[i][1][2] += Math.min(dp[i - 1][0][2], dp[i - 1][2][2]);

			dp[i][2][0] += Math.min(dp[i - 1][0][0], dp[i - 1][1][0]);
			dp[i][2][1] += Math.min(dp[i - 1][0][1], dp[i - 1][1][1]);
			dp[i][2][2] += Math.min(dp[i - 1][0][2], dp[i - 1][1][2]);
		}
		int ans = Integer.MAX_VALUE;
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				if(i==j) continue;
				if (ans > dp[N - 1][i][j]) {
					ans = dp[N - 1][i][j];
				}
			}
		}
		System.out.println(ans);
	}
}
