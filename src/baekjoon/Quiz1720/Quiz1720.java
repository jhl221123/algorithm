package baekjoon.Quiz1720;

import java.io.*;

/*
Gold4: 타일 코드 / [dp]
*/
public class Quiz1720 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[] dp = new int[N + 2];
		dp[0] = 1;
		dp[1] = 1;

		for (int i = 2; i <= N; i++) {
			dp[i] = dp[i - 1] + 2 * dp[i - 2];
		}

		int total = dp[N];
		int duplicated;
		if (N % 2 == 0) {
			duplicated = dp[N / 2] + 2 * dp[N / 2 - 1];
		} else {
			duplicated = dp[N / 2];
		}

		System.out.println((total + duplicated) / 2);
	}
}
