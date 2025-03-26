package baekjoon.Quiz2240;

import java.io.*;

/*
Gold5: 자두나무 / [dp]
1. 자두가 떨어지는 나무는 MAX(t-1초 w 나무 / t-1초 w-1 반대 나무) + 1
2. 자두가 떨어지지 않는 나무는 MAX(t-1초 w 나무 / t-1초 w-1 반대 나무)
 */
public class Quiz2240 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] tw = br.readLine().split(" ");
		int T = Integer.parseInt(tw[0]);
		int W = Integer.parseInt(tw[1]);
		int[][][] dp = new int[T + 1][W + 1][3];
		int[] arr = new int[T + 1];
		for(int i=1; i<=T; i++) {
			arr[i] = Integer.parseInt(br.readLine());
		}

		if(arr[1] == 1) {
			dp[1][0][1] = 1;
		} else {
			dp[1][1][2] = 1;
		}

		for(int t=2; t<=T; t++) {
			for(int w=0; w<=W; w++) {
				if(arr[t] == 1) {
					if(w == 0) {
						dp[t][w][1] = dp[t-1][w][1] + 1;
					} else {
						dp[t][w][1] = Math.max(dp[t-1][w-1][2], dp[t-1][w][1]) + 1;
						dp[t][w][2] = Math.max(dp[t-1][w-1][1], dp[t-1][w][2]);
					}
				} else {
					if(w == 0) {
						dp[t][w][1] = dp[t-1][w][1];
					} else {
						dp[t][w][1] = Math.max(dp[t-1][w-1][2], dp[t-1][w][1]);
						dp[t][w][2] = Math.max(dp[t-1][w-1][1], dp[t-1][w][2]) + 1;
					}
				}
			}
		}

		int max = 0;
		for(int i=0; i<=W; i++) {
			max = Math.max(max, Math.max(dp[T][i][1], dp[T][i][2]));
		}

		System.out.println(max);
	}
}
