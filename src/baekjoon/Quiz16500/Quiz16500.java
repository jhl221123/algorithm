package baekjoon.Quiz16500;

import java.io.*;

public class Quiz16500 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String S = br.readLine();
		int[] dp = new int[S.length() + 1];
		int N = Integer.parseInt(br.readLine());
		String[] As = new String[N];
		for(int i=0; i<N; i++) {
			As[i] = br.readLine();
		}

		dp[0] = 1;
		for(int i=1; i<=S.length(); i++) {
			for(int j=0; j<As.length; j++) {
				String cur = As[j];
				if(same(S, i-1, i + cur.length() - 1, cur)
					&& dp[i-1] > 0) {
					dp[i -1 + cur.length()] = dp[i-1] + 1;
				}
			}
		}
		System.out.println(dp[S.length()] > 0 ? "1" : "0");
	}

	private static boolean same(String base, int s, int e, String in) {
		int idx = 0;
		if(base.length() < e) return false;
		for(int i=s; i<e; i++) {
			if(base.charAt(i) != in.charAt(idx++)) return false;
		}
		return true;
	}
}
