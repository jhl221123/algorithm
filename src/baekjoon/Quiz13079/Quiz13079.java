package baekjoon.Quiz13079;

import java.io.*;

/*
Gold4: Partitioning a Queue / [dp]
*/
public class Quiz13079 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int t = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();

		while(t-- > 0) {
			String[] nmk = br.readLine().split(" ");
			int n = Integer.parseInt(nmk[0]);
			int m = Integer.parseInt(nmk[1]);
			int k = Integer.parseInt(nmk[2]);

			boolean[] prohibit = new boolean[n + 1];
			int s = m == 0 ? k : m;
			for(int i=s; i<=n; i+=k) {
				prohibit[i] = true;
			}

			int[] dp = new int[n + 1];
			dp[0] = 1;
			for(int i=1; i<=n; i++) {
				for(int j=1; j<=i; j++) {
					if(!prohibit[j]) {
						dp[i] += dp[i - j];
					}
				}
			}

			sb.append(dp[n]).append("\n");
		}

		System.out.println(sb);

	}
}
