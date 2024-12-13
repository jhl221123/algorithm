package baekjoon.Quiz4811;

import java.io.*;

public class Quiz4811 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		long[] dp = new long[31];
		dp[0] = 1;
		dp[1] = 1;
		dp[2] = 2;

		for(int i=3; i<=30; i++) {
			long count = 0;

			for(int j=0; j<i; j++) {
				count += (dp[j] * dp[i-1-j]);
			}

			dp[i] = count;
		}

		StringBuilder ans = new StringBuilder();
		while(true) {
			int n = Integer.parseInt(br.readLine());
			if(n == 0) break;
			ans.append(dp[n]).append("\n");
		}

		System.out.print(ans);
	}
}
