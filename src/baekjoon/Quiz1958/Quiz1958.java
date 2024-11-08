package baekjoon.Quiz1958;

import java.io.*;

public class Quiz1958 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String str1 = br.readLine();
		String str2 = br.readLine();
		String str3 = br.readLine();

		int[][][] dp = new int[str1.length()][str2.length()][str3.length()];
		for(int i=0; i<str1.length(); i++) {
			for(int j=0; j<str2.length(); j++) {
				for(int k=0; k<str3.length(); k++) {
					int c1 = i>0 ? dp[i-1][j][k] : 0;
					int c2 = j>0 ? dp[i][j-1][k] : 0;
					int c3 = k>0 ? dp[i][j][k-1] : 0;
					int c4 = i>0 && j>0 && k>0 ? dp[i-1][j-1][k-1] : 0;

					if(str1.charAt(i) == str2.charAt(j) && str2.charAt(j) == str3.charAt(k)) {
						dp[i][j][k] = c4 + 1;
					} else {
						dp[i][j][k] = Math.max(c1, Math.max(c2, c3));
					}
				}
			}
		}

		System.out.println(dp[str1.length()-1][str2.length()-1][str3.length()-1]);
	}
}
