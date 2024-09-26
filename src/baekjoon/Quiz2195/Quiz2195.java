package baekjoon.Quiz2195;

import java.io.*;

public class Quiz2195 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String S = br.readLine();
		String P = br.readLine();
		int start = 0;
		int ans = 0;

		for(int i=1; i<=P.length(); i++) {
			String substring = P.substring(start, i);
			if(S.contains(substring)) continue;
			ans++;
			start = i - 1;
		}

		System.out.println(ans+1);
	}
}
