package baekjoon.Quiz16132;

import java.util.*;
import java.io.*;

/*
Gold4: 그룹 나누기 (Subset) / [dp]
*/
public class Quiz16132 {

	private static int N;
	private static int subsetSum;
	private static long[][] dp;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		int sum = N * (N + 1) / 2;
		if(sum % 2 == 1) {
			System.out.println(0);
			return;
		}
		subsetSum = sum / 2;
		dp = new long[N + 1][subsetSum + 1];
		for(int i=0; i<=N; i++) {
			Arrays.fill(dp[i], -1);
		}
		System.out.println(dfs(1, 0));
	}

	private static long dfs(int c, int s) {
		if(c > N || s > subsetSum) return 0;
		if(s == subsetSum) return 1;
		if(dp[c][s] != -1) return dp[c][s];
		long r1 = dfs(c + 1, s);
		long r2 = dfs(c + 1, s + c);
		return dp[c][s] = r1 + r2;
	}
}
