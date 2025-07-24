package baekjoon.Quiz1003;

import java.io.*;

/*
Silver3: 피보나치 함수 / [dp]
*/
public class Quiz1003 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();

		int[][] dp = new int[41][2];
		dp[0][0] = 1;
		dp[1][1] = 1;
		while(T-- > 0) {
			int N = Integer.parseInt(br.readLine());
			dfs(dp, N);
			sb.append(dp[N][0]).append(" ").append(dp[N][1]).append("\n");
		}

		System.out.print(sb);
	}

	private static void dfs(int[][] dp, int num) {
		if(dp[num][0] > 0 || dp[num][1] > 0) {
			return;
		}
		dfs(dp, num - 1);
		dfs(dp, num - 2);
		dp[num][0] += (dp[num - 1][0] + dp[num - 2][0]);
		dp[num][1] += (dp[num - 1][1] + dp[num - 2][1]);
	}
}
