package baekjoon.Quiz4781;

import java.io.*;

/*
Gold4: 사탕 가게 / [dp]
*/
public class Quiz4781 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		while(true) {
			String[] nm = br.readLine().split(" ");
			int n = Integer.parseInt(nm[0]);
			double dm = Double.parseDouble(nm[1]);
			int m = (int) (dm * 100 + 0.5);

			if(n == 0 && m == 0) break;

			int[] dp = new int[m + 1];
			for(int i=0; i<n; i++) {
				String[] cp = br.readLine().split(" ");
				int c = Integer.parseInt(cp[0]);
				double dop = Double.parseDouble(cp[1]);
				int d = (int) (dop * 100 + 0.5);

				for(int j=d; j<=m; j++) {
					dp[j] = Math.max(dp[j], dp[j - d] + c);
				}
			}

			sb.append(dp[m]).append("\n");
		}

		System.out.print(sb);
	}
}
