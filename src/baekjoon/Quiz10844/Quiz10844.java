package baekjoon.Quiz10844;

import java.util.*;

public class Quiz10844 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int[][] dp = new int[N+1][10];
		for(int i=1; i<=9; i++) {
			dp[1][i] = 1;
		}
		for(int i=2; i<=N; i++) {
			for(int j=0; j<=9; j++) {
				int prior = j > 0 ? dp[i-1][j-1] : 0;
				int next = j < 9 ? dp[i-1][j+1] : 0;
				dp[i][j] = (prior + next) % 1000000000;
			}
		}
		int ans = 0;
		for(int i=0; i<=9; i++) {
			ans = (ans + dp[N][i]) % 1000000000;
		}
		System.out.println(ans);
	}
}
