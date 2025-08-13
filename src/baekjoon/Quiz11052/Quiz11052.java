package baekjoon.Quiz11052;

import java.io.*;

/*
Silver1: 카드 구매하기 / [dp]
*/
public class Quiz11052 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		String[] p = br.readLine().split(" ");
		int[] price = new int[N + 1];
		for(int i=1; i<=N; i++) {
			price[i] = Integer.parseInt(p[i-1]);
		}

		int[] dp = new int[N + 1];
		dp[1] = price[1];
		for(int i=2; i<=N; i++) {
			for(int j=0; j<i; j++) {
				dp[i] = Math.max(dp[i], price[i-j] + dp[j]);
			}
		}

		System.out.println(dp[N]);
	}
}
