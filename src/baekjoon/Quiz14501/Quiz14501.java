package baekjoon.Quiz14501;

import java.util.*;
import java.io.*;

public class Quiz14501 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[][] arr = new int[N+1][2];
		int[] dp = new int[N+1];
		for(int i=1; i<=N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			arr[i][0] = Integer.parseInt(st.nextToken());
			arr[i][1] = Integer.parseInt(st.nextToken());
		}
		for(int i=1; i<=N; i++) {
			dp[i] = Math.max(dp[i], dp[i-1]);
			int next = i+arr[i][0]-1;
			if(next > N) continue;
			dp[next] = Math.max(dp[i-1] + arr[i][1], dp[next]);
		}
		System.out.println(dp[N]);
	}
}
