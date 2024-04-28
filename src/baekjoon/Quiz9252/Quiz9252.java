package baekjoon.Quiz9252;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Quiz9252 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String str1 = br.readLine();
		String str2 = br.readLine();
		int l1 = str1.length();
		int l2 = str2.length();
		int[][] dp = new int[l1][l2];
		for(int i=0; i<l1; i++) {
			for(int j=0; j<l2; j++) {
				int c1 = j>0 ? dp[i][j-1] : 0;
				int c2 = i>0 ? dp[i-1][j] : 0;
				int c3 = (j>0 && i>0) ? dp[i-1][j-1] : 0;
				if(str1.charAt(i) == str2.charAt(j)) dp[i][j] = c3 + 1;
				else dp[i][j] = Math.max(c1, c2);
			}
		}
		StringBuilder sb = new StringBuilder();
		int num = dp[l1-1][l2-1];
		int line = l2;
		for(int i=l1-1; i>=0; i--) {
			for(int j=l2-1; j>=0; j--) {
				if(i<0) break;
				if(j>=line) continue;
				if(str1.charAt(i) == str2.charAt(j) && dp[i][j] == num) {
					sb.append(str1.charAt(i));
					num--;
					line=j;
					i--;
				}
			}
		}
		System.out.println(dp[l1-1][l2-1]);
		System.out.println(sb.reverse());
	}
}
