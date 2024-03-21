package baekjoon.Quiz1932;

import java.util.*;
import java.io.*;

public class Quiz1932 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[][] dp = new int[2][N+1];
		dp[0][1] = Integer.parseInt(br.readLine());
		for(int i=2; i<=N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int j=1; j<=i; j++) {
				dp[1][j] = Math.max(dp[0][j], dp[0][j-1]) + Integer.parseInt(st.nextToken());
			}
			for(int j=1; j<=i; j++) {
				dp[0][j] = dp[1][j];
			}
		}
		int ans = 0;
		for(int i=1; i<=N; i++) {
			ans = Math.max(dp[0][i], ans);
		}
		System.out.println(ans);
	}
}
