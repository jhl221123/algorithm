package baekjoon.Quiz2073;

import java.io.*;

/*
Gold4: 수도배관공사 / [dp]
*/
public class Quiz2073 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] DP = br.readLine().split(" ");
		int D = Integer.parseInt(DP[0]);
		int P = Integer.parseInt(DP[1]);

		int[] dp = new int[D + 1];
		dp[0] = Integer.MAX_VALUE;

		for (int i = 0; i < P; i++) {
			String[] LC = br.readLine().split(" ");
			int L = Integer.parseInt(LC[0]);
			int C = Integer.parseInt(LC[1]);

			for (int j = D; j >= L; j--) {
				if (dp[j - L] > 0) {
					dp[j] = Math.max(dp[j], Math.min(dp[j - L], C));
				}
			}
		}

		System.out.println(dp[D]);
	}
}
