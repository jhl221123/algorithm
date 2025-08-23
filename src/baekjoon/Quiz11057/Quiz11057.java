package baekjoon.Quiz11057;

import java.io.*;
import java.util.*;

/*
Silver1: 오르막 수 / [dp]
*/
public class Quiz11057 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[][] dp = new int[N + 1][10];
		Arrays.fill(dp[1], 1);
		for(int i=2; i<=N; i++) {
			for(int j=9; j>=0; j--) {
				int sum = 0;
				for(int k=0; k<=j; k++) {
					sum += dp[i-1][k];
					sum %= 10007;
				}
				dp[i][j] = sum;
			}
		}

		int ans = 0;
		for(int i=0; i<=9; i++) {
			ans += dp[N][i];
			ans %= 10007;
		}
		System.out.println(ans);
	}
}
