package baekjoon.Quiz6144;

import java.io.*;

/*
Gold5: Charm Bracelet / [dp]
*/
public class Quiz6144 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] NM = br.readLine().split(" ");
		int N = Integer.parseInt(NM[0]);
		int M = Integer.parseInt(NM[1]);

		int[] dp = new int[M + 1];
		for(int i=0; i<N; i++) {
			String[] WD = br.readLine().split(" ");
			int W = Integer.parseInt(WD[0]);
			int D = Integer.parseInt(WD[1]);

			for(int j=M; j>=W; j--) {
				dp[j] = Math.max(dp[j], dp[j-W] + D);
			}
		}

		System.out.println(dp[M]);
	}
}
