package baekjoon.Quiz27105;

import java.io.*;

/*
Gold5: Equal Summed Subsets / [dp]
*/
public class Quiz27105 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());

		int total = N * (N + 1) / 2;
		if (total % 2 != 0) {
			System.out.println(0);
			return;
		}

		int target = total / 2;
		long[] dp = new long[target + 1];
		dp[0] = 1;

		for (int num = 1; num <= N; num++) {
			for (int i = target; i >= num; i--) {
				dp[i] += dp[i - num];
			}
		}

		System.out.println(dp[target] / 2);
	}
}
