package baekjoon.Quiz3483;

import java.io.*;

/*
Gold5: Piggy-Bank / [dp]
*/
public class Quiz3483 {

	private static final int INF = 1_000_000_000;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();

		while(T-- > 0) {
			String[] EF = br.readLine().split(" ");
			int E = Integer.parseInt(EF[0]);
			int F = Integer.parseInt(EF[1]);
			int TW = F - E;
			int N = Integer.parseInt(br.readLine());

			int[] dp = new int[TW + 1];
			for(int i=0; i<=TW; i++) {
				dp[i] = INF;
			}
			dp[0] = 0;
			for(int i=0; i<N; i++) {
				String[] PW = br.readLine().split(" ");
				int P = Integer.parseInt(PW[0]);
				int W = Integer.parseInt(PW[1]);

				for(int j=W; j<=TW; j++) {
					dp[j] = Math.min(dp[j], dp[j - W] + P);
				}
			}

			String s;
			if(dp[TW] == INF) {
				s = "This is impossible.";
			} else {
				s = "The minimum amount of money in the piggy-bank is " + dp[TW] + ".";
			}
			sb.append(s).append("\n");
		}

		System.out.print(sb);
	}
}
