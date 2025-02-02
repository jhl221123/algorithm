package baekjoon.Quiz11049;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
1. 2개의 행렬 곱의 최소값부터 N개의 행렬 곱의 최소값까지 bottom -> top 방식으로 계산한다.
2. 2 ~ N 까지 길이는 chainLength 로 정의하고 모든 구간에 대해 최소값을 구한다.
2-1. 예를 들어 N = 5, chainLength = 2 일 때, [0, 1] / [1, 2] / [2, 3] / [3, 4] 구간에 대해 최소값을 갱신한다.
2-2. 예를 들어 N = 5, chainLength = 3 일 때, [0, 2] / [1, 3] / [2, 4] 구간에 대해 최소값을 갱신한다.
3. chainLength 가 3 이상일 때부터 chain 에 구분점을 두고, 모든 경우의 수를 확인한다.
3-1. 예를 들어 N = 5, chainLength = 4 일 때, [0, 3] / [1, 4] 구간에 대해 최소값을 갱신한다.
3-1-2 이때, [0, 3] 의 최소값은 [0, 0] + [1, 3] / [0, 1] + [2, 3] / [0, 2] + [3, 3] 중 최소이다.
3-1-2 이때, [1, 4] 의 최소값은 [1, 1] + [2, 4] / [1, 2] + [3, 4] / [1, 3] + [4, 4] 중 최소이다.
 */

public class Quiz11049 {

	private static final int INF = Integer.MAX_VALUE;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[] matrices = new int[N + 1];
		StringTokenizer st;
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			matrices[i] = Integer.parseInt(st.nextToken());
			matrices[i + 1] = Integer.parseInt(st.nextToken());
		}

		int[][] dp = new int[N][N];
		for (int chainLength = 2; chainLength < matrices.length; chainLength++) {
			for (int startIdx = 0; startIdx < matrices.length - chainLength; startIdx++) {
				int endIdx = startIdx + chainLength - 1;
				dp[startIdx][endIdx] = INF;

				for (int splitIdx = startIdx; splitIdx < endIdx; splitIdx++) {
					int multipleValue = dp[startIdx][splitIdx] +
						dp[splitIdx + 1][endIdx] +
						(matrices[startIdx] * matrices[splitIdx + 1] * matrices[endIdx + 1]);
					dp[startIdx][endIdx] = Math.min(dp[startIdx][endIdx], multipleValue);
				}
			}
		}

		System.out.println(dp[0][N - 1]);
	}
}
