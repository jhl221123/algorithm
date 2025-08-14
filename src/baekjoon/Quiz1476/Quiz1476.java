package baekjoon.Quiz1476;

import java.io.*;

/*
Silver5: 날짜 계산 / [brute-force]
*/
public class Quiz1476 {
	public static void main(String[] arsg) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] ESM = br.readLine().split(" ");
		int E = Integer.parseInt(ESM[0]);
		int S = Integer.parseInt(ESM[1]);
		int M = Integer.parseInt(ESM[2]);

		while(true) {
			if(E == S && S == M) {
				break;
			}

			int min = Math.min(Math.min(E, S), M);
			if(min == E) E += 15;
			else if(min == S) S += 28;
			else M += 19;
		}

		System.out.println(E);
	}
}
