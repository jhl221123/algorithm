package baekjoon.Quiz7570;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Quiz7570 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[] dp = new int[N+1];
		int max = 0;
		StringTokenizer st = new StringTokenizer(br.readLine());

		// 연속된 최장 수열의 길이를 구한다.
		for(int i=0; i<N; i++) {
			int num = Integer.parseInt(st.nextToken());
			dp[num] = dp[num-1] + 1;
			max = Math.max(max, dp[num]);
		}

		// 전체 길이에서 연속된 최장 수열의 길이 만큼 빼면 최소값이 된다.
		System.out.println(N - max);
	}
}
