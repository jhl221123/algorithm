package baekjoon.Quiz25791;

import java.io.*;
import java.util.*;

/*
Gold5: Lecture Allocation / [dp]
*/
public class Quiz25791 {

	private static final int INF = 1_000_000_007;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] LT = br.readLine().split(" ");
		int L = Integer.parseInt(LT[0]);
		int T = Integer.parseInt(LT[1]);

		int[][] dp = new int[T + 1][L + 1];
		for(int i=0; i<=T; i++) {
			Arrays.fill(dp[i], INF);
		}
		dp[0][0] = 0;

		for(int i=1; i<=T; i++) {
			String[] abc = br.readLine().split(" ");
			int one = Integer.parseInt(abc[0]);
			int two = Integer.parseInt(abc[1]);
			int three = Integer.parseInt(abc[2]);
			int[] costs = new int[] {0, one, two, three};

			for(int j=0; j<=L; j++) {
				dp[i][j] = dp[i - 1][j];

				for(int k=1; k<=3; k++) {
					if(j - k < 0 || dp[i - 1][j - k] >= INF) continue;
					dp[i][j] = Math.min(dp[i][j], dp[i - 1][j - k] + costs[k]);
				}
			}
		}

		System.out.println(dp[T][L]);
	}
}
