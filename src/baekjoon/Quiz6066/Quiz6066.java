package baekjoon.Quiz6066;

import java.util.*;
import java.io.*;

/*
Gold4: Buying Hay / [dp]
*/
public class Quiz6066 {
	private static final int INF = Integer.MAX_VALUE;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] NH = br.readLine().split(" ");
		int N = Integer.parseInt(NH[0]);
		int H = Integer.parseInt(NH[1]);

		int maxHay = H * 2;
		int[] dp = new int[maxHay + 1];

		Arrays.fill(dp, INF);
		dp[0] = 0;

		for (int i = 0; i < N; i++) {
			String[] PC = br.readLine().split(" ");
			int P = Integer.parseInt(PC[0]);
			int C = Integer.parseInt(PC[1]);

			for (int j = P; j <= maxHay; j++) {
				if (dp[j - P] != INF) {
					dp[j] = Math.min(dp[j], dp[j - P] + C);
				}
			}
		}

		int minCost = INF;
		for (int i = H; i <= maxHay; i++) {
			minCost = Math.min(minCost, dp[i]);
		}

		System.out.println(minCost);
	}
}
