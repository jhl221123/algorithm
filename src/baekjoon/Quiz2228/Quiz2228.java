package baekjoon.Quiz2228;

import java.util.*;
import java.io.*;

public class Quiz2228 {
	static int N, M;
	static int[] arr;
	static int[] acc;
	static int[][] dp;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		arr = new int[N+1];
		acc = new int[N+1];
		dp = new int[M+1][N+1];
		for(int i=1; i<=N; i++) {
			arr[i] = Integer.parseInt(br.readLine());
			acc[i] = acc[i-1] + arr[i];
		}
		for(int i=0; i<=M; i++) {
			for(int j=0; j<=N; j++) {
				dp[i][j] = -1;
			}
		}
		System.out.println(cal(M, N));
	}
	static int cal(int m, int n) {
		if(m==0) return 0;
		if(n<=0) return -100000000;
		if(dp[m][n] != -1) return dp[m][n];
		dp[m][n] = cal(m, n-1);
		for(int i=1; i<=n; i++) {
			int temp = cal(m-1, i-2) + sum(n, i);
			if(dp[m][n] < temp) dp[m][n] = temp;
		}
		return dp[m][n];
	}
	static int sum(int a, int b) {
		return acc[a] - acc[b-1];
	}
}
