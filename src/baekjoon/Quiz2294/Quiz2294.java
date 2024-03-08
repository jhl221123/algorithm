package baekjoon.Quiz2294;

import java.util.*;
import java.io.*;

public class Quiz2294 {
	static final int INF = 20000;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int k = Integer.parseInt(st.nextToken());
		int[] coin = new int[n];
		int[] dp = new int[k+1];
		Arrays.fill(dp, INF);
		dp[0] = 0;
		for(int i=0; i<n; i++) {
			coin[i] = Integer.parseInt(br.readLine());
			if(coin[i]<=k) dp[coin[i]] = 1;
		}
		Arrays.sort(coin);
		for(int i=1; i<=k; i++) {
			int min = INF;
			for(int j=0; j<n; j++) {
				if(coin[j] >= i) break;
				min = Math.min(min, dp[i-coin[j]]);
				dp[i] = Math.min(dp[i], min + 1);
			}
		}
		//        for(int i=0; i<=k; i++) {
		//            System.out.print(dp[i]);
		//        }
		System.out.print(dp[k] == INF ? -1 : dp[k]);
	}
}
