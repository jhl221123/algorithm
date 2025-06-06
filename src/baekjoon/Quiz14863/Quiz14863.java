package baekjoon.Quiz14863;

import java.io.*;

/*
Gold4: 서울에서 경산까지 / [dp]
*/
public class Quiz14863 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] NK = br.readLine().split(" ");
		int N = Integer.parseInt(NK[0]);
		int K = Integer.parseInt(NK[1]);

		int[] ws = new int[N];
		int[] wm = new int[N];
		int[] bs = new int[N];
		int[] bm = new int[N];
		for(int i=0; i<N; i++) {
			String[] moveInfo = br.readLine().split(" ");
			ws[i] = Integer.parseInt(moveInfo[0]);
			wm[i] = Integer.parseInt(moveInfo[1]);
			bs[i] = Integer.parseInt(moveInfo[2]);
			bm[i] = Integer.parseInt(moveInfo[3]);
		}

		int[][] dp = new int[N][K + 1];
		dp[0][ws[0]] = wm[0];
		dp[0][bs[0]] = Math.max(dp[0][bs[0]], bm[0]);
		for(int i=1; i<N; i++) {
			for(int j=0; j<=K; j++) {
				if(dp[i - 1][j] == 0) continue;

				int cws = ws[i];
				if(j + cws <= K) {
					dp[i][j + cws] = Math.max(dp[i][j + cws], dp[i - 1][j] + wm[i]);
				}

				int cbs = bs[i];
				if(j + cbs <= K) {
					dp[i][j + cbs] = Math.max(dp[i][j + cbs], dp[i - 1][j] + bm[i]);
				}
			}
		}

		int max = 0;
		for(int i=0; i<=K; i++) {
			max = Math.max(max, dp[N - 1][i]);
		}

		System.out.println(max);
	}
}
