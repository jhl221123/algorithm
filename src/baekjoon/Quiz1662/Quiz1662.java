package baekjoon.Quiz1662;

import java.io.*;

public class Quiz1662 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String str = br.readLine();
		int originLength = dfs(0, str.length()-1, str);
		System.out.println(originLength);
	}

	private static int dfs(int s, int e, String str) {
		int cnt = 0;
		int prior = 0;
		for(int i=s; i<=e; i++) {
			if(str.charAt(i) == '(') {
				int ne = getEnd(i, str);
				cnt--;
				cnt += prior * dfs(i+1, ne-1, str);
				i = ne;
			} else {
				cnt++;
				prior = str.charAt(i) - '0';
			}
		}
		return cnt;
	}

	private static int getEnd(int s, String str) {
		int l = 0;
		int r = 0;
		for(int i=s; i<str.length(); i++) {
			if(str.charAt(i) == '(') l++;
			else if(str.charAt(i) == ')') r++;
			if(l == r) return i;
		}
		return -1;
	}
}
