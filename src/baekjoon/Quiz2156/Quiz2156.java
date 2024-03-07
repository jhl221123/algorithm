package baekjoon.Quiz2156;

import java.io.*;

public class Quiz2156 {
	//dp[n][0]: Max(dp[n-1][0]~[2]);
	//dp[n][1]: arr[n] + dp[n-1][0];
	//dp[n][2]: arr[n] + Max(dp[n-1][1],[2]);

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		int[] arr = new int[n];
		for(int i=0; i<n; i++) {
			arr[i] = Integer.parseInt(br.readLine());
		}
		int[][] dp = new int[n][3];
		dp[0][0] = 0;
		dp[0][1] = arr[0];
		dp[0][2] = arr[0];
		for(int i=1; i<n; i++) {
			dp[i][0] = Math.max(Math.max(dp[i-1][0], dp[i-1][1]), dp[i-1][2]);
			dp[i][1] = arr[i] + dp[i-1][0];
			dp[i][2] = arr[i] + dp[i-1][1];
		}
		int ans = Math.max(Math.max(dp[n-1][0], dp[n-1][1]), dp[n-1][2]);
		System.out.println(ans);
	}
}
