package baekjoon.Quiz6208;

import java.io.*;
import java.util.*;

/*
Gold4: Cow Roller Coaster / [dp]
*/
public class Quiz6208 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] LNB = br.readLine().split(" ");
		int L = Integer.parseInt(LNB[0]);
		int N = Integer.parseInt(LNB[1]);
		int B = Integer.parseInt(LNB[2]);

		List<List<Parts>> partsByStart = new ArrayList<>();
		for (int i = 0; i <= L; i++) {
			partsByStart.add(new ArrayList<>());
		}

		for (int i = 0; i < N; i++) {
			String[] XWFC = br.readLine().split(" ");
			int X = Integer.parseInt(XWFC[0]);
			int W = Integer.parseInt(XWFC[1]);
			int F = Integer.parseInt(XWFC[2]);
			int C = Integer.parseInt(XWFC[3]);

			partsByStart.get(X).add(new Parts(X, W, F, C));
		}

		int[][] dp = new int[L + 1][B + 1];
		for (int i = 0; i <= L; i++) {
			Arrays.fill(dp[i], -1);
		}
		dp[0][0] = 0;

		for (int l = 0; l < L; l++) {
			for (int b = 0; b <= B; b++) {
				if (dp[l][b] == -1) {
					continue;
				}

				for (Parts part : partsByStart.get(l)) {
					int nextLength = l + part.l;
					int nextBudget = b + part.c;

					if (nextLength <= L && nextBudget <= B) {
						dp[nextLength][nextBudget] = Math.max(
							dp[nextLength][nextBudget],
							dp[l][b] + part.f
						);
					}
				}
			}
		}

		int maxFun = -1;
		for (int i = 0; i <= B; i++) {
			maxFun = Math.max(maxFun, dp[L][i]);
		}

		System.out.println(maxFun);
	}

	static class Parts {
		int s, l, f, c;

		public Parts(int s, int l, int f, int c) {
			this.s = s;
			this.l = l;
			this.f = f;
			this.c = c;
		}
	}
}
