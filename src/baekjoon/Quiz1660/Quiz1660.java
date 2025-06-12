package baekjoon.Quiz1660;

import java.io.*;
import java.util.*;

/*
Gold5: 캡틴 이다솜 / [dp]
*/
public class Quiz1660 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[] dp = new int[125];
		dp[0] = 0;
		dp[1] = 1;

		int idx = 1;
		while(dp[idx] <= N) {
			idx++;
			dp[idx] = dp[idx - 1] + (dp[idx - 1] - dp[idx - 2]) + idx;
		}

		int[] cnt = new int[N + 1];
		Arrays.fill(cnt, Integer.MAX_VALUE);
		cnt[0] = 0;
		cnt[1] = 1;
		for (int i = 2 ; i <= N ; i++) {
			for (int j = 1 ; j < idx ; j++) {
				if (dp[j] > i) break;
				cnt[i] = Math.min(cnt[i], cnt[i - dp[j]] + 1);
			}
		}
		System.out.println(cnt[N]);
	}
}
