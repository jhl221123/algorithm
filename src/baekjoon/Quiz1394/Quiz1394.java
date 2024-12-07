package baekjoon.Quiz1394;

import java.io.*;

public class Quiz1394 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String candidates = br.readLine();
		String code = br.readLine();
		int n = candidates.length();
		int m = code.length();
		int MOD = 900528;

		long powMod = 1;
		long s = 1;
		for (int i = 1; i < m; i++) {
			powMod = (powMod * n) % MOD;
			s = (s + powMod) % MOD;
		}

		powMod = 1;
		for (int i = m - 1; i >= 0; i--) {
			char target = code.charAt(i);
			int idx = candidates.indexOf(target);
			s = (s + (idx * powMod) % MOD) % MOD;
			powMod = (powMod * n) % MOD;
		}

		System.out.println(s);
	}
}
