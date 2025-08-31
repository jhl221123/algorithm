package baekjoon.Quiz11722;

import java.io.*;
import java.util.*;

/*
Silver2: 가장 긴 감소하는 부분 수열 / [dp]
*/
public class Quiz11722 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[] arr = Arrays.stream(br.readLine().split(" "))
			.mapToInt(Integer::parseInt)
			.toArray();
		int[] dp = new int[N];
		Arrays.fill(dp, 1);

		for(int i=0; i<N; i++) {
			for(int j=i-1; j>=0; j--) {
				if(arr[j] > arr[i]) {
					dp[i] = Math.max(dp[i], dp[j] + 1);
				}
			}
		}

		int max = 0;
		for(int i=0; i<N; i++) {
			max = Math.max(max, dp[i]);
		}
		System.out.println(max);
	}
}
