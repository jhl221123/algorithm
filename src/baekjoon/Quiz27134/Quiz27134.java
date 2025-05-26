package baekjoon.Quiz27134;

import java.io.*;

/*
Gold5: Subset Sums / [dp]
*/
public class Quiz27134 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int totalSum = N * (N + 1) / 2;
		if(totalSum % 2 == 1) {
			System.out.println(0);
			return;
		}

		int half = totalSum / 2;
		long[] dp = new long[half + 1];
		dp[0] = 1;
		for(int i=1; i<=N; i++) {
			for(int j=half; j>=i; j--) {
				dp[j] += dp[j - i];
			}
		}

		System.out.println(dp[half] / 2);
	}
}
