package baekjoon.Quiz1463;

import java.io.*;
import java.util.*;

/*
Silver3: 1로 만들기 / [dp]
*/
public class Quiz1463 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());

		if(N == 1) {
			System.out.println(0);
			return;
		}
		if(N == 2 || N == 3) {
			System.out.println(1);
			return;
		}
		int[] dp = new int[N + 1];
		Arrays.fill(dp, 1000001);
		dp[1] = 0;
		dp[2] = 1;
		dp[3] = 1;
		for(int i=4; i<=N; i++) {
			if(i % 3 == 0) {
				dp[i] = Math.min(dp[i], dp[i / 3] + 1);
			}
			if(i % 2 == 0) {
				dp[i] = Math.min(dp[i], dp[i / 2] + 1);
			}
			dp[i] = Math.min(dp[i], dp[i - 1] + 1);
		}

		System.out.println(dp[N]);
	}
}
