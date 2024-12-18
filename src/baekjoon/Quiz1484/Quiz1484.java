package baekjoon.Quiz1484;

import java.io.*;

public class Quiz1484 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		long G = Long.parseLong(br.readLine());
		long s = 1;
		long e = 2;
		boolean isPossible = false;
		StringBuilder ans = new StringBuilder();

		while(e<=100000) {
			long ps = s * s;
			long pe = e * e;
			long diff = pe - ps;

			if(diff == G) {
				ans.append(e).append("\n");
				isPossible = true;
			}
			if(diff > G) s++;
			else e++;
		}

		if(isPossible) System.out.print(ans);
		else System.out.println(-1);
	}
}
