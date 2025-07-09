package baekjoon.Quiz2342;

import java.io.*;

/*
Gold3: Dance Dance Revolution / [dp]
*/
public class Quiz2342 {

	private static final int[][] cost = {
		{1, 2, 2, 2, 2},
		{0, 1, 3, 4, 3},
		{0, 3, 1, 3, 4},
		{0, 4, 3, 1, 3},
		{0, 3, 4, 3, 1}
	};

	private static int L;
	private static int[] steps;
	private static int[][][] dp;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] arr = br.readLine().split(" ");
		steps = new int[arr.length - 1];
		for(int i=0; i<arr.length - 1; i++) {
			steps[i] = Integer.parseInt(arr[i]);
		}

		L = steps.length;
		dp = new int[L][5][5];
		System.out.println(dfs(0, 0, 0));
	}

	private static int dfs(int s, int l, int r) {
		if(s == L) return 0;
		if(dp[s][l][r] > 0) return dp[s][l][r];

		int next = steps[s];
		return dp[s][l][r] = Math.min(dfs(s + 1, next, r) + cost[l][next], dfs(s + 1, l, next) + cost[r][next]);
	}
}
