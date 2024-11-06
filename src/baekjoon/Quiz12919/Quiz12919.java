package baekjoon.Quiz12919;

import java.io.*;

public class Quiz12919 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String S = br.readLine();
		String T = br.readLine();

		if(dfs(T, S)) System.out.println(1);
		else System.out.println(0);
	}

	private static boolean dfs(String str, String S) {
		if(str.length() == S.length()) {
			return str.equals(S);
		}

		if(str.length() < S.length()) return false;

		if(str.charAt(0) == 'B') {
			StringBuilder sb = new StringBuilder();
			for(int i=str.length()-1; i>=1; i--) {
				sb.append(str.charAt(i));
			}
			if(dfs(sb.toString(), S)) return true;
		}
		if(str.charAt(str.length()-1) == 'A') {
			StringBuilder sb = new StringBuilder();
			for(int i=0; i<str.length()-1; i++) {
				sb.append(str.charAt(i));
			}
			return dfs(sb.toString(), S);
		}

		return false;
	}
}
