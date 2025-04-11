package baekjoon.Quiz13398;

import java.io.*;
import java.util.*;

/*
Gold5: 연속합 2 / [dp]
*/
public class Quiz13398 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[] sequence = Arrays.stream(br.readLine().split(" "))
			.mapToInt(Integer::parseInt)
			.toArray();
		int[][] dp = new int[N][3];
		dp[0][0] = sequence[0];
		dp[0][1] = sequence[0];
		dp[0][2] = 0;

		for(int i=1; i<N; i++) {
			int number = sequence[i];
			dp[i][0] = Math.max(dp[i-1][0] + number, number);
			dp[i][1] = Math.max(Math.max(dp[i-1][1] + number, dp[i-1][2] + number), number);
			dp[i][2] = dp[i-1][0];
		}

		int max = -1_000_000_001;
		for(int i=0; i<N; i++) {
			max = Math.max(max, Math.max(dp[i][0], dp[i][1]));
		}

		System.out.println(max);
	}
}
