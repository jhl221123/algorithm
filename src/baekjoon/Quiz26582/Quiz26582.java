package baekjoon.Quiz26582;

import java.io.*;

/*
Gold5: Treasure / [dp]
*/
public class Quiz26582 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();

		while(T-- > 0) {
			String[] nc = br.readLine().split(" ");
			int n = Integer.parseInt(nc[0]);
			int c = Integer.parseInt(nc[1]);
			int[] dp = new int[c + 1];

			for(int i=0; i<n; i++) {
				String[] vw = br.readLine().split(" ");
				int v = Integer.parseInt(vw[0]);
				int w = Integer.parseInt(vw[1]);

				for(int j=c; j>=w; j--) {
					dp[j] = Math.max(dp[j], dp[j - w] + v);
				}
			}

			sb.append(dp[c]).append("\n");
		}

		System.out.println(sb);
	}
}
