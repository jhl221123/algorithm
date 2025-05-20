package baekjoon.Quiz29704;

import java.io.*;

/*
Gold5: 벼락치기 / [dp]
1. T일 내 해결할 수 있는 문제 중 벌금이 최대가 되는 경우를 구한다.
2. 총 벌금에서 제거 가능한 최대 벌금을 뺀다.
*/
public class Quiz29704 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] NT = br.readLine().split(" ");
		int N = Integer.parseInt(NT[0]);
		int T = Integer.parseInt(NT[1]);

		int[] dp = new int[T + 1];
		int total = 0;
		for(int i=0; i<N; i++) {
			String[] dm = br.readLine().split(" ");
			int d = Integer.parseInt(dm[0]);
			int m = Integer.parseInt(dm[1]);
			total += m;

			for(int j=T; j>=d; j--) {
				dp[j] = Math.max(dp[j], dp[j - d] + m);
			}
		}

		System.out.println(total - dp[T]);
	}
}
