package baekjoon.Quiz9251;

import java.io.*;

public class Quiz9251 {
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
		System.out.println(dp[l1-1][l2-1]);
	}
}
