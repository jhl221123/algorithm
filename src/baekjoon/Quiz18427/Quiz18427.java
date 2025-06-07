package baekjoon.Quiz18427;

import java.io.*;
import java.util.*;

/*
Gold4: 함께 블록 쌓기 / [dp]
*/
public class Quiz18427 {

	private static final int MOD = 10007;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] NMH = br.readLine().split(" ");
		int N = Integer.parseInt(NMH[0]);
		int M = Integer.parseInt(NMH[1]);
		int H = Integer.parseInt(NMH[2]);

		int[][] dp = new int[N + 1][H + 1];
		dp[0][0] = 1;
		for(int i=1; i<=N; i++) {
			int[] blocks = Arrays.stream(br.readLine().split(" "))
				.mapToInt(Integer::parseInt)
				.toArray();

			for(int j=0; j<=H; j++) {
				dp[i][j] = dp[i-1][j];

				for(int k=0; k<blocks.length; k++) {
					int h = blocks[k];
					if(j >= h) {
						dp[i][j] += dp[i-1][j - h];
						dp[i][j] %= MOD;
					}
				}
			}
		}

		System.out.println(dp[N][H]);
	}
}
