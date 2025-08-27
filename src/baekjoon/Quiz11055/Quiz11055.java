package baekjoon.Quiz11055;

import java.io.*;

/*
Silver2: 가장 큰 증가하는 부분 수열 / [dp]
*/
public class Quiz11055 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		String[] info = br.readLine().split(" ");
		int[] numbers = new int[N + 1];
		for(int i=1; i<=N; i++) {
			numbers[i] = Integer.parseInt(info[i-1]);
		}
		int[] dp = new int[N + 1];
		for(int i=1; i<=N; i++) {
			for(int j=i-1; j>=0; j--) {
				if(numbers[j] >= numbers[i]) {
					continue;
				}
				dp[i] = Math.max(dp[i], dp[j] + numbers[i]);
			}
		}

		int max = 0;
		for(int i=1; i<=N; i++) {
			max = Math.max(max, dp[i]);
		}
		System.out.println(max);
	}
}
