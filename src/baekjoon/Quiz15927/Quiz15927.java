package baekjoon.Quiz15927;

import java.io.*;

public class Quiz15927 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String target = br.readLine();
		if(!isPallin(target)) System.out.println(target.length());
		else {
			for (int i = 1; i < target.length(); i++) {
				if(target.charAt(i) != target.charAt(0)) {
					System.out.println(target.length() - 1);
					return;
				}
			}
			System.out.println(-1);
		}
	}

	private static boolean isPallin(String target) {
		int s = 0;
		int e = target.length()-1;
		while(s < e) {
			if(target.charAt(s) != target.charAt(e)) return false;
			s += 1;
			e -= 1;
		}
		return true;
	}
}
