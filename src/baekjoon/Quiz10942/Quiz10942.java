package baekjoon.Quiz10942;

import java.util.*;
import java.io.*;

public class Quiz10942 {
	static int N, M;
	static int[] arr;
	static int[][] dp;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());
		arr = new int[N+1];
		dp = new int[N+1][N+1];
		for(int i=1; i<=N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
			dp[i][i] = 1;
		}
		M = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			int l = Integer.parseInt(st.nextToken());
			int r = Integer.parseInt(st.nextToken());
			int result = cal(l, r) ? 1 : 0;
			sb.append(result).append("\n");
		}
		System.out.print(sb);
	}
	private static boolean cal(int l, int r) {
		while(l<=r) {
			if(dp[l][r] == 1) return true;
			if(dp[l][r] == -1) return false;
			if(cal(l+1, r-1)) {
				if(arr[l] == arr[r]) {
					dp[l][r] = 1;
					return true;
				} else {
					dp[l][r] = -1;
					return false;
				}
			} else {
				dp[l][r] = -1;
				return false;
			}
		}
		if(arr[l] == arr[r]) {
			dp[l][r] = 1;
			return true;
		}
		return false;
	}
}
