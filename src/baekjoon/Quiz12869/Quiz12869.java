package baekjoon.Quiz12869;

import java.util.*;
import java.io.*;

/*
Gold4: 뮤탈리스크 / [dp]
*/
public class Quiz12869 {

	private static final int[][] hitCase = {
		{-9, -3, -1},
		{-9, -1, -3},
		{-3, -9, -1},
		{-3, -1, -9},
		{-1, -9, -3},
		{-1, -3, -9}
	};
	private static final int MAX = 100_000;

	private static int N;
	private static int[][][] dp;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		int[] hp = Arrays.stream(br.readLine().split(" "))
			.mapToInt(Integer::parseInt)
			.toArray();

		dp = new int[61][61][61];
		for(int i=0; i<=60; i++) {
			for(int j=0; j<=60; j++) {
				Arrays.fill(dp[i][j], MAX);
			}
		}

		int s1 = hp[0];
		int s2 = N > 1 ? hp[1] : 0;
		int s3 = N > 2 ? hp[2] : 0;
		dfs(s1, s2, s3, 0);
		System.out.println(dp[0][0][0]);
	}

	private static void dfs(int s1, int s2, int s3, int count) {
		s1 = Math.max(s1, 0);
		s2 = Math.max(s2, 0);
		s3 = Math.max(s3, 0);

		if(dp[s1][s2][s3] <= count) {
			return;
		}
		dp[s1][s2][s3] = Math.min(dp[s1][s2][s3], count);

		if(s1 == 0 && s2 == 0 && s3 == 0) {
			return;
		}

		for(int i=0; i<6; i++) {
			dfs(s1 + hitCase[i][0], s2 + hitCase[i][1], s3 + hitCase[i][2], count + 1);
		}
	}
}
