package baekjoon.Quiz1309;

import java.util.*;

public class Quiz1309 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int[][] dp = new int[N+1][3];
		dp[1][0] = 1;
		dp[1][1] = 1;
		dp[1][2] = 1;
		for(int i=2; i<=N; i++) {
			for(int j=0; j<3; j++) {
				if(j==0) dp[i][j] = (dp[i-1][0] + dp[i-1][1] + dp[i-1][2]) % 9901;
				if(j==1) dp[i][j] = (dp[i-1][0] + dp[i-1][2]) % 9901;
				if(j==2) dp[i][j] = (dp[i-1][0] + dp[i-1][1]) % 9901;
			}
		}
		int ans = (dp[N][0]+dp[N][1]+dp[N][2]) % 9901;
		System.out.println(ans);
	}
}
