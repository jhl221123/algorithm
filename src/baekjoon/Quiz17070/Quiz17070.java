package baekjoon.Quiz17070;

import java.util.*;
import java.io.*;

public class Quiz17070 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		// init
		int[][][] dp = new int[N+1][N+1][3];
		dp[1][2][0] = 1;
		//wall check
		boolean[][] wall = new boolean[N+1][N+1];
		for(int i=1; i<=N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int j=1; j<=N; j++) {
				int target = Integer.parseInt(st.nextToken());
				if(target==1) wall[i][j] = true;
			}
		}
		for(int i=1; i<=N; i++) {
			for(int j=3; j<=N; j++) {
				if(wall[i][j]) continue;
				if(!wall[i][j-1]) dp[i][j][0] = dp[i][j-1][0] + dp[i][j-1][1];
				if(!wall[i][j-1] && !wall[i-1][j] && !wall[i-1][j-1]) dp[i][j][1] = dp[i-1][j-1][0] + dp[i-1][j-1][1] + dp[i-1][j-1][2];
				if(!wall[i-1][j]) dp[i][j][2] = dp[i-1][j][1] + dp[i-1][j][2];
			}
		}
		int ans = 0;
		for(int i=0; i<3; i++) {
			ans += dp[N][N][i];
		}
		System.out.println(ans);
	}
}
