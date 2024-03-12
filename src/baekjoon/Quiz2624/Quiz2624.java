package baekjoon.Quiz2624;

import java.util.*;
import java.io.*;

public class Quiz2624 {
	// 동전별 타겟 카운트는 ni만큼만 반복
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		int k = Integer.parseInt(br.readLine());
		int[][] dp = new int[k+1][T+1];
		dp[0][0] = 1;
		for(int i=1; i<=k; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int p = Integer.parseInt(st.nextToken());
			int n = Integer.parseInt(st.nextToken());
			for(int j=0; j<=T; j++) {
				for(int c=0; c<=n; c++) {
					int ne = j + p * c;
					if(ne > T) break;
					dp[i][ne] += dp[i-1][j];
				}
			}
		}
		System.out.println(dp[k][T]);
	}
}
