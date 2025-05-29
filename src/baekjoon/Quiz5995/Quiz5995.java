package baekjoon.Quiz5995;

import java.io.*;

/*
Gold5: Bessie's Weight Problem / [dp]
*/
public class Quiz5995 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] HN = br.readLine().split(" ");
		int H = Integer.parseInt(HN[0]);
		int N = Integer.parseInt(HN[1]);

		boolean[] dp = new boolean[H + 1];
		dp[0] = true;

		for (int i = 0; i < N; i++) {
			int W = Integer.parseInt(br.readLine());
			for (int j = H; j >= W; j--) {
				if (dp[j - W]) {
					dp[j] = true;
				}
			}
		}

		for (int i = H; i >= 0; i--) {
			if (dp[i]) {
				System.out.println(i);
				break;
			}
		}
	}
}
