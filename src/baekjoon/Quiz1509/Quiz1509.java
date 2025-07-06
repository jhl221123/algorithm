package baekjoon.Quiz1509;

import java.util.*;
import java.io.*;

/*
Gold1: 팰린드롬 분할 / [dp]
*/
public class Quiz1509 {

	private static final int MAX = Integer.MAX_VALUE;
	private static int N;
	private static String str;
	private static boolean[][] isPal;
	private static int[] dp;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		str = br.readLine();
		N = str.length();
		str = "." + str;
		isPal = new boolean[N + 1][N + 1];
		dp = new int[N + 1];

		buildPal();
		buildDp();
		for(int i=1; i<=N; i++) {
			for(int j=1; j<=i; j++) {
				if(isPal[j][i]) dp[i] = Math.min(dp[i], dp[j - 1] + 1);
			}
		}

		System.out.println(dp[N]);
	}

	private static void buildPal() {
		for(int i=1; i<=N; i++) {
			for(int j=1; j<=i; j++) {
				boolean isPallin = true;
				int from = j;
				int to = i;
				while(from <= to) {
					if(str.charAt(from++) != str.charAt(to--)) {
						isPallin = false;
						break;
					}
				}
				if(isPallin) isPal[j][i] = true;
			}
		}
	}

	private static void buildDp() {
		Arrays.fill(dp, MAX);
		dp[0] = 0;
	}
}
