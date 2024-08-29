package baekjoon.Quiz12904;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Quiz12904 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String S = br.readLine();
		String T = br.readLine();

		int s = 0;
		int e = T.length() - 1;
		while(true) {
			if(T.charAt(e) == 'A') {
				if(e > s) e--;
				else e++;
			}
			else if(T.charAt(e) == 'B') {
				if(e > s) e--;
				else e++;
				int temp = s;
				s = e;
				e = temp;
			}
			if(Math.abs(s - e) + 1 == S.length()) break;
		}

		StringBuilder sb = new StringBuilder();
		if(e > s) {
			for(int i=s; i<=e; i++) {
				sb.append(T.charAt(i));
			}
		} else {
			for(int i=s; i>=e; i--) {
				sb.append(T.charAt(i));
			}
		}

		System.out.println(S.contentEquals(sb) ? 1 : 0);
	}
}
