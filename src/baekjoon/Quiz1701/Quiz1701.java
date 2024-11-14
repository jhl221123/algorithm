package baekjoon.Quiz1701;

import java.io.*;

public class Quiz1701 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String str = br.readLine();

		int max = 0;
		for(int i=0; i<str.length(); i++) {
			String target = str.substring(i);
			int[] kmp = new int[target.length()];
			int l = 0;
			for(int j=1; j<target.length(); j++) {
				while(l > 0 && target.charAt(l) != target.charAt(j)) {
					l = kmp[l-1];
				}

				if(target.charAt(l) == target.charAt(j)) {
					kmp[j] = ++l;
					max = Math.max(max, l);
				}
			}
		}

		System.out.println(max);
	}
}
