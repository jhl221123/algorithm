package baekjoon.Quiz15486;

import java.util.*;
import java.io.*;

public class Quiz15486 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[][] arr = new int[N+1][2];
		int[] dp = new int[N+2];
		for(int i=1; i<=N; i++){
			StringTokenizer st = new StringTokenizer(br.readLine());
			int T = Integer.parseInt(st.nextToken());
			int P = Integer.parseInt(st.nextToken());
			arr[i][0] = T;
			arr[i][1] = P;
		}
		for(int i=1; i<=N; i++){
			int T = arr[i][0];
			int P = arr[i][1];
			dp[i+1] = Math.max(dp[i+1], dp[i]);
			if(i+T>N+1) continue;
			dp[i+T] = Math.max(dp[i+T], dp[i]+P);
		}
		System.out.println(dp[N+1]);
	}
}
