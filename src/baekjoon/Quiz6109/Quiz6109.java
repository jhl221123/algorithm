package baekjoon.Quiz6109;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/*
Gold5: Dairy Queen / [dp]
*/
public class Quiz6109 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] NC = br.readLine().split(" ");
		int N = Integer.parseInt(NC[0]);
		int C = Integer.parseInt(NC[1]);
		int[] dp = new int[N + 1];
		dp[0] = 1;
		for(int i=0; i<C; i++) {
			int m = Integer.parseInt(br.readLine());

			for(int j=m; j<=N; j++) {
				dp[j] += dp[j - m];
			}
		}

		System.out.println(dp[N]);
	}
}
