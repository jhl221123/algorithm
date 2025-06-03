package baekjoon.Quiz11528;

import java.io.*;

/*
Gold5: Compositions / [dp]
*/
public class Quiz11528 {
	static int[][] dp;
	static boolean[] banned;
	static int n;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int P = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();

		for (int p = 0; p < P; p++) {
			String[] tokens = br.readLine().split(" ");
			int K = Integer.parseInt(tokens[0]);
			n = Integer.parseInt(tokens[1]);
			int m = Integer.parseInt(tokens[2]);
			int k = Integer.parseInt(tokens[3]);

			banned = new boolean[n + 1];
			for (int i = m; i <= n; i += k) {
				banned[i] = true;
			}

			dp = new int[n + 1][n + 1];
			for (int[] row : dp) {
				Arrays.fill(row, -1);
			}

			int result = dfs(0);
			sb.append(K).append(" ").append(result).append("\n");
		}

		System.out.print(sb);
	}

	static int dfs(int sum) {
		if (sum == n) return 1;
		if (sum > n) return 0;
		if (dp[0][sum] != -1) return dp[0][sum];

		int total = 0;
		for (int i = 1; i <= n; i++) {
			if (!banned[i]) {
				total += dfs(sum + i);
			}
		}
		return dp[0][sum] = total;
	}
}
