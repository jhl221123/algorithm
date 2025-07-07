package baekjoon.Quiz2302;

import java.io.*;

/*
Gold5: 극장 좌석/ [dp]
*/
public class Quiz2302 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int M = Integer.parseInt(br.readLine());
		int[] dp = new int[N + 1];
		dp[1] = 1;
		dp[2] = 2;
		for(int i=3; i<=N; i++) {
			dp[i] = dp[i - 1] + dp[i - 2];
		}

		int count = 1;
		int prev = 0;
		for(int i=0; i<M; i++) {
			int fixed = Integer.parseInt(br.readLine());
			count *= dp[fixed - prev - 1];
			prev = fixed;
		}
		count *= dp[N - prev];
		System.out.println(count);
	}
}
