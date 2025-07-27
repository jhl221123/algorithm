package baekjoon.Quiz9461;

import java.io.*;

/*
Silver3: 파도반 수열 / [dp]
1  1  1  2  2  3
4  5  7  9  12 16
21 28 37 49 65 86
*/
public class Quiz9461 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		long[] dp = new long[101];
		dp[1] = 1;
		dp[2] = 1;
		dp[3] = 1;
		dp[4] = 2;
		dp[5] = 2;
		buildNum(dp);

		StringBuilder sb = new StringBuilder();
		while(T-- > 0) {
			int num = Integer.parseInt(br.readLine());
			sb.append(dp[num]).append("\n");
		}

		System.out.print(sb);
	}

	private static void buildNum(long[] dp) {
		for(int i=6; i<=100; i++) {
			dp[i] = dp[i - 1] + dp[i - 5];
		}
	}
}
