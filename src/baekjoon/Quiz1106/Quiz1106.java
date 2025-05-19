package baekjoon.Quiz1106;

import java.io.*;
import java.util.*;

/*
Gold4: 호텔 / [dp]
1. 가능한 고객수 범위를 순회하며 기존보다 적은 비용이 든다면 갱신한다.
2. 비용과 고객수는 배수로 사용가능하기 때문에 순회는 고객수 오름차순으로 진행한다.
*/
public class Quiz1106 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] CN = br.readLine().split(" ");
		int C = Integer.parseInt(CN[0]);
		int N = Integer.parseInt(CN[1]);
		int[] dp = new int[C + 101];
		Arrays.fill(dp, 1_000_000);
		dp[0] = 0;
		for(int i=0; i<N; i++) {
			String[] cc = br.readLine().split(" ");
			int cost = Integer.parseInt(cc[0]);
			int customer = Integer.parseInt(cc[1]);

			for(int j=customer; j<C+101; j++) {
				dp[j] = Math.min(dp[j], dp[j - customer] + cost);
			}
		}

		int min = Integer.MAX_VALUE;
		for(int i=C; i<C+101; i++) {
			min = Math.min(min, dp[i]);
		}
		System.out.println(min);
	}
}
