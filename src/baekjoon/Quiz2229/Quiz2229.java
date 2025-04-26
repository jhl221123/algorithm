package baekjoon.Quiz2229;

import java.io.*;

/*
Gold5: 조 짜기 / [dp]
1. 원소를 순회하며 선택한 원소 기준으로 모든 점수를 탐색한다.
2. 현재 원소보다 작은 원소와 차이를 구하고, 비교 원소 이전의 dp 값을 더해 최댓값을 갱신한다.
*/
public class Quiz2229 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		String[] numbers = br.readLine().split(" ");

		int[] scores = new int[N + 1];
		int[] dp = new int[N + 1];
		int max = 0;
		for(int i = 1; i <= N; i++){
			scores[i] = Integer.parseInt(numbers[i - 1]);

			for(int j = i - 1; j > 0; j--){
				max = Math.max(max, Math.abs(scores[i] - scores[j]) + dp[j - 1]);
			}

			dp[i] = max;
		}

		System.out.println(dp[N]);
	}
}
