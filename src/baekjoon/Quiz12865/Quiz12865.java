package baekjoon.Quiz12865;

import java.util.*;
import java.io.*;

public class Quiz12865 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		int[] dp = new int[K+1];
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			int W = Integer.parseInt(st.nextToken());
			int V = Integer.parseInt(st.nextToken());
			for(int j=K; j>=W; j--) {
				dp[j] = Math.max(dp[j], dp[j-W] + V);
			}
		}
		System.out.println(dp[K]);
	}
}
