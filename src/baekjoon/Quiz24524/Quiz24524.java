package baekjoon.Quiz24524;

import java.io.*;

public class Quiz24524 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String S = br.readLine();
		String T = br.readLine();
		int[] count = new int[T.length()];

		for(int i=0; i<S.length(); i++) {
			String target = String.valueOf(S.charAt(i));
			if(!T.contains(target)) continue;
			int idx = T.indexOf(target);
			if(idx == 0 || count[idx - 1] > count[idx]) count[idx]++;
		}

		System.out.println(count[T.length() - 1]);
	}
}
