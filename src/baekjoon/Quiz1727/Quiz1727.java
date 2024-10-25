package baekjoon.Quiz1727;

import java.io.*;
import java.util.*;

public class Quiz1727 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());

		int[][] dp = new int[N + 1][M + 1];
		int[] man = new int[N];
		int[] woman = new int[M];

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			man[i] = Integer.parseInt(st.nextToken());
		}

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < M; i++) {
			woman[i] = Integer.parseInt(st.nextToken());
		}

		Arrays.sort(man);
		Arrays.sort(woman);

		for (int i = 1; i <= man.length; i++) {
			for (int j = 1; j <= woman.length; j++) {
				// 남여 수가 같다면, 정렬 순서대로 '모두' 쌍을 짓는다.
				dp[i][j] = dp[i - 1][j - 1] + Math.abs(man[i - 1] - woman[j - 1]);
				// 남여 수가 다르다면, '적은 성별과 같은 수'로 쌍이 생긴다.
				// 따라서 남여 수가 같아질 때까지 '더 적은 성별과 같은 수의 쌍'으로 최소값을 유지한다.
				if (i > j) dp[i][j] = Math.min(dp[i][j], dp[i - 1][j]);
				if (i < j) dp[i][j] = Math.min(dp[i][j], dp[i][j - 1]);
			}
		}

		System.out.println(dp[N][M]);
	}
}
