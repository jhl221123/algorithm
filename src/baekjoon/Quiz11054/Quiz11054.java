package baekjoon.Quiz11054;

import java.io.*;
import java.util.*;

public class Quiz11054 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[] arr = new int[N];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i=0; i<N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		int[][] dp = new int[N][2];
		for(int i=0; i<N; i++) {
			dp[i][0] = 1;
			dp[i][1] = 1;
		}

		for(int i=0; i<N; i++) {
			for(int j=i; j>=0; j--) {
				if(arr[i] > arr[j]) {
					dp[i][0] = Math.max(dp[i][0], dp[j][0] + 1);
				}
			}
		}

		for(int i=0; i<N; i++) {
			for(int j=i; j>=0; j--) {
				if(arr[i] < arr[j]) {
					dp[i][1] = Math.max(dp[i][1], Math.max(dp[j][1] + 1, dp[j][0] + 1));
				}
			}
		}

		int max = 0;
		for(int i=0; i<N; i++) {
			max = Math.max(max, dp[i][1]);
		}

		System.out.println(max);
	}
}
