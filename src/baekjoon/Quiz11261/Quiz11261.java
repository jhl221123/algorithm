package baekjoon.Quiz11261;

import java.io.*;
import java.util.*;

/*
Gold5: Hunger Games / [dp]
*/
public class Quiz11261 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();

		while(T-- > 0) {
			int N = Integer.parseInt(br.readLine());
			int W = Integer.parseInt(br.readLine());
			int[] ns = Arrays.stream(br.readLine().split(" "))
				.mapToInt(Integer::parseInt)
				.toArray();
			int[] ws = Arrays.stream(br.readLine().split(" "))
				.mapToInt(Integer::parseInt)
				.toArray();
			int[] dp = new int[W + 1];
			for(int i=0; i<N; i++) {
				int cn = ns[i];
				int cw = ws[i];
				for(int j=W; j>=cn; j--) {
					dp[j] = Math.max(dp[j], dp[j - cn] + cw);
				}
			}

			sb.append(dp[W]).append("\n");
		}

		System.out.print(sb);
	}
}
