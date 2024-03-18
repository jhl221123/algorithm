package baekjoon.Quiz2096;

import java.util.*;
import java.io.*;

public class Quiz2096 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[][] dp = new int[2][3];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i=0; i<3; i++) {
			int score = Integer.parseInt(st.nextToken());
			dp[0][i] = score;
			dp[1][i] = score;
		}
		for(int i=1; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			int l = Integer.parseInt(st.nextToken());
			int m = Integer.parseInt(st.nextToken());
			int r = Integer.parseInt(st.nextToken());

			int xl = l + Math.max(dp[0][0], dp[0][1]);
			int nl = l + Math.min(dp[1][0], dp[1][1]);
			int xm = m + Math.max(Math.max(dp[0][0], dp[0][1]), dp[0][2]);
			int nm = m + Math.min(Math.min(dp[1][0], dp[1][1]), dp[1][2]);
			int xr = r + Math.max(dp[0][2], dp[0][1]);
			int nr = r + Math.min(dp[1][2], dp[1][1]);
			dp[0][0] = xl;
			dp[1][0] = nl;
			dp[0][1] = xm;
			dp[1][1] = nm;
			dp[0][2] = xr;
			dp[1][2] = nr;
		}
		int max = Math.max(Math.max(dp[0][0], dp[0][1]), dp[0][2]);
		int min = Math.min(Math.min(dp[1][0], dp[1][1]), dp[1][2]);
		System.out.println(max + " " + min);
	}
}
