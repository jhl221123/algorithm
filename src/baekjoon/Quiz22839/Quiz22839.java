package baekjoon.Quiz22839;

import java.io.*;

/*
Gold5: Square Coins / [dp]
*/
public class Quiz22839 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int[] coins = new int[18];
		for(int i=1; i<18; i++) {
			coins[i] = i * i;
		}

		while(true) {
			int n = Integer.parseInt(br.readLine());
			if(n == 0) break;

			int[] dp = new int[n + 1];
			dp[0] = 1;
			for(int i=1; i<18; i++) {
				int coin = coins[i];

				for(int j=coin; j<=n; j++) {
					dp[j] += dp[j - coin];
				}
			}

			sb.append(dp[n]).append("\n");
		}

		System.out.print(sb);
	}
}
