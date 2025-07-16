package baekjoon.Quiz11062;

import java.io.*;

/*
Gold3: 카드 게임 / [dp]
*/
public class Quiz11062 {

	private static int[] arr;
	private static int[] subsum;
	private static int[][] dp;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();

		while(T-- > 0) {
			int N = Integer.parseInt(br.readLine());
			String[] str = br.readLine().split(" ");
			arr = new int[N+1];
			for(int i=1; i<=N; i++) {
				arr[i] = Integer.parseInt(str[i-1]);
			}
			subsum = new int[N+1];
			for(int i=1; i<=N; i++) {
				subsum[i] = subsum[i-1] + arr[i];
			}
			dp = new int[N+1][N+1];
			dfs(1, N);
			sb.append(dp[1][N]).append("\n");
		}
		System.out.print(sb);
	}

	private static int dfs(int l, int r) {
		if(dp[l][r] > 0) return dp[l][r];
		if(r == l) {
			return dp[l][r] = arr[r];
		}
		int left = (subsum[r] - subsum[l]) - dfs(l+1, r) + arr[l];
		int right = (subsum[r-1] - subsum[l-1]) - dfs(l, r-1) + arr[r];
		return dp[l][r] = Math.max(left, right);
	}
}
