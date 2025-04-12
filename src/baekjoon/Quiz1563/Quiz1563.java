package baekjoon.Quiz1563;

import java.io.*;
import java.util.*;

/*
Gold4: 개근상 / [dp]
*/
public class Quiz1563 {

	private static final int INIT = -1;
	private static final int MOD = 1_000_000;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[][][] dp = new int[N + 1][3][4];
		for(int i=0; i<=N; i++) {
			for(int j=0; j<3; j++) {
				Arrays.fill(dp[i][j], INIT);
			}
		}

		System.out.println(dfs(dp, 0, 0, 0));
	}

	private static int dfs(int[][][] dp, int days, int tardy, int absent) {
		if(dp[days][tardy][absent] != INIT) {
			return dp[days][tardy][absent];
		}

		if(tardy >= 2 || absent >= 3) {
			return 0;
		}

		if(days >= dp.length - 1) {
			return 1;
		}

		dp[days][tardy][absent] = dfs(dp, days + 1, tardy, 0)
			+ dfs(dp, days + 1, tardy + 1, 0)
			+ dfs(dp, days + 1, tardy, absent + 1);

		return dp[days][tardy][absent] % MOD;
	}
}
