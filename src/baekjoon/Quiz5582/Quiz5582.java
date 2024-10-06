package baekjoon.Quiz5582;

import java.io.*;

public class Quiz5582 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String s1 = br.readLine();
		String s2 = br.readLine();
		int[][] dp = new int[s1.length() + 1][s2.length() + 1];

		int max = 0;
		for(int i = 1; i <= s1.length(); i++) {
			for(int j = 1; j <= s2.length(); j++) {
				if(s1.charAt(i - 1) == s2.charAt(j - 1)) { // 현재 양쪽 idx의 문자가 동일하다면
					dp[i][j] = dp[i - 1][j - 1] + 1; // 각각 바로 직전 idx까지 연속된 길이 + 1
					max = Math.max(max, dp[i][j]);
				}
			}
		}
		System.out.println(max);
	}
}
