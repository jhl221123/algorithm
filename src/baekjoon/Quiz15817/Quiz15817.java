package baekjoon.Quiz15817;

import java.io.*;

/*
Gold4: 배수 공사 / [dp]
*/
public class Quiz15817 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] Nx = br.readLine().split(" ");
		int N = Integer.parseInt(Nx[0]);
		int x = Integer.parseInt(Nx[1]);
		int[] dp = new int[x + 1];
		dp[0] = 1;

		for(int i=0; i<N; i++) {
			String[] LC = br.readLine().split(" ");
			int L = Integer.parseInt(LC[0]);
			int C = Integer.parseInt(LC[1]);

			int[] temp = Arrays.copyOf(dp, x + 1);
			for(int j=0; j<=x; j++) {
				if(dp[j] == 0) continue;

				for(int k=1; k<=C; k++) {
					int next = j + L * k;
					if(next > x) break;
					temp[next] += dp[j];
				}
			}

			dp = temp;
		}

		System.out.println(dp[x]);
	}
}
