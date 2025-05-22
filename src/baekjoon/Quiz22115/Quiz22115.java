package baekjoon.Quiz22115;

import java.util.*;
import java.io.*;

/*
Gold5: 창영이와 커피 / [dp]
1. K가 0 이라면 0을 반환한다.
2. 모든 커피를 순회하며 최소로 카페인을 충족할 수 있도록 갱신한다.
*/
public class Quiz22115 {

	private static final int INF = 100_001;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] NK = br.readLine().split(" ");
		int N = Integer.parseInt(NK[0]);
		int K = Integer.parseInt(NK[1]);

		if(K == 0) {
			System.out.println(0);
			return;
		}

		int[] dp = new int[K + 1];
		Arrays.fill(dp, INF);
		dp[0] = 0;
		int[] caffeines = Arrays.stream(br.readLine().split(" "))
			.mapToInt(Integer::parseInt)
			.toArray();
		for(int i=0; i<N; i++) {
			int caffeine = caffeines[i];
			if(caffeine > K) continue;
			dp[caffeine] = 1;
		}

		for(int i=0; i<N; i++) {
			int caffeine = caffeines[i];
			for(int j=K; j>=caffeine; j--) {
				dp[j] = Math.min(dp[j], dp[j - caffeine] + 1);
			}
		}

		System.out.println(dp[K] < INF ? dp[K] : -1);
	}
}
