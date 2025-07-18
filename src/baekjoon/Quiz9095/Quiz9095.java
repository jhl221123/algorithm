package baekjoon.Quiz9095;

import java.io.*;

/*
Silver3: 1, 2, 3 더하기 / [dp]
*/
public class Quiz9095 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());

		StringBuilder sb = new StringBuilder();
		while(T-- > 0) {
			int n = Integer.parseInt(br.readLine());
			int[] dp = new int[n + 1];
			dp[0] = 1;
			dp[1] = 1;
			if(n == 1) {
				sb.append(dp[n]).append("\n");
				continue;
			}
			dp[2] = 2;
			if(n == 2) {
				sb.append(dp[n]).append("\n");
				continue;
			}

			for(int i=3; i<=n; i++) {
				dp[i] = dp[i - 1] + dp[i - 2] + dp[i - 3];
			}
			sb.append(dp[n]).append("\n");
		}

		System.out.print(sb);
	}
}
