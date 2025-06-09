package baekjoon.Quiz17208;

import java.io.*;

/*
Gold4: 카우버거 알바생 / [dp]
*/
public class Quiz17208 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] NMK = br.readLine().split(" ");
		int N = Integer.parseInt(NMK[0]);
		int M = Integer.parseInt(NMK[1]);
		int K = Integer.parseInt(NMK[2]);

		int[][] dp = new int[M + 1][K + 1];
		for(int i=0; i<N; i++) {
			String[] xy = br.readLine().split(" ");
			int x = Integer.parseInt(xy[0]);
			int y = Integer.parseInt(xy[1]);
			for(int j=M; j>=x; j--) {
				for(int k=K; k>=y; k--) {
					dp[j][k] = Math.max(dp[j][k], dp[j - x][k - y] + 1);
				}
			}
		}

		System.out.println(dp[M][K]);
	}
}
