package baekjoon.Quiz17218;

import java.io.*;

public class Quiz17218 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String s1 = br.readLine();
		String s2 = br.readLine();

		int[][] dp = new int[s1.length() + 1][s2.length() + 1];
		for(int i=1; i<=s1.length(); i++) {
			for(int j=1; j<=s2.length(); j++) {
				if(s1.charAt(i-1) == s2.charAt(j-1)) dp[i][j] = dp[i-1][j-1] + 1;
				else dp[i][j] = Math.max(dp[i][j-1], dp[i-1][j]);
			}
		}

		int cnt = dp[s1.length()][s2.length()];
		StringBuilder sb = new StringBuilder();
		for(int i=s1.length(); i>0; i--) {
			for(int j=s2.length(); j>0; j--) {
				if(dp[i][j] == cnt && dp[i][j-1] != cnt && dp[i-1][j] != cnt) {
					sb.append(s1.charAt(i-1));
					i--;
					cnt--;
					if(cnt == 0) {
						System.out.println(sb.reverse());
						return;
					}
				}
			}
		}
	}
}
