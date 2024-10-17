package baekjoon.Quiz1082;

import java.io.*;
import java.math.BigInteger;
import java.util.StringTokenizer;

public class Quiz1082 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[] prices = new int[N];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			prices[i] = Integer.parseInt(st.nextToken());
		}
		int M = Integer.parseInt(br.readLine());
		BigInteger[][] dp = new BigInteger[M+1][2];
		for(int i=0; i<=M; i++) {
			dp[i][0] = BigInteger.ZERO;
			dp[i][1] = BigInteger.ONE;
		}

		for(int i=1; i<=M; i++) {
			for(int j=0; j<N; j++) {
				if(prices[j] <= i) {
					BigInteger next1 = dp[i - prices[j]][0].add(BigInteger.valueOf(j).multiply(dp[i - prices[j]][1]));
					BigInteger next2 = dp[i - prices[j]][0].multiply(BigInteger.TEN).add(BigInteger.valueOf(j));
					BigInteger next;
					if(next1.compareTo(next2) >= 0) next = next1;
					else next = next2;
					if(dp[i][0].compareTo(next) < 0){
						dp[i][0] = next;
						dp[i][1] = dp[i-prices[j]][1].multiply(BigInteger.TEN);
					}
				}
			}
		}

		System.out.println(dp[M][0]);
	}
}
