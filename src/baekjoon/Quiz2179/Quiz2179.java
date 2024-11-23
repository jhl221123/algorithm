package baekjoon.Quiz2179;

import java.io.*;

public class Quiz2179 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		String[] strs = new String[N];
		for(int i=0; i<N; i++) {
			strs[i] = br.readLine();
		}

		int s = 0;
		int t = 0;
		int cnt = 0;
		for(int i=0; i<N; i++) {
			String S = strs[i];
			for(int j=i+1; j<N; j++) {
				String T = strs[j];
				int tmp = 0;
				if(S.length() < T.length()) tmp = check(S, T);
				else tmp = check(T, S);
				if(tmp > cnt) {
					cnt = tmp;
					s = i;
					t = j;
				}
			}
		}

		System.out.println(strs[s]);
		System.out.println(strs[t]);
	}

	private static int check(String l, String r) {
		int cnt = 0;
		for(int i=0; i<l.length(); i++) {
			if(l.charAt(i) != r.charAt(i)) break;
			cnt++;
		}
		return cnt;
	}
}
