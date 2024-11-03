package baekjoon.Quiz5557;

import java.io.*;
import java.util.*;

public class Quiz5557 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[] nums = new int[N];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i=0; i<N; i++) {
			nums[i] = Integer.parseInt(st.nextToken());
		}

		long[][] dp = new long[N][21];
		dp[0][nums[0]] = 1;

		for(int i=1; i<N-1; i++) {
			int num = nums[i];
			for(int j=0; j<=20; j++) {
				if(dp[i-1][j] > 0) {
					if(j + num <= 20) dp[i][j + num] += dp[i-1][j];
					if(j - num >= 0) dp[i][j - num] += dp[i-1][j];
				}
			}
		}

		System.out.println(dp[N-2][nums[N-1]]);
	}
}
