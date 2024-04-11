package baekjoon.Quiz7579;

import java.util.*;
import java.io.*;

public class Quiz7579 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int[] dp = new int[M+1];
		Arrays.fill(dp, 10000001);

		StringTokenizer mSt = new StringTokenizer(br.readLine());
		StringTokenizer cSt = new StringTokenizer(br.readLine());

		for(int i=0; i<N; i++) {
			int cm = Integer.parseInt(mSt.nextToken());
			int cc = Integer.parseInt(cSt.nextToken());
			if(cm > M) cm = M;
			for(int j=M; j>cm; j--) {
				dp[j] = Math.min(dp[j], dp[j-cm] + cc);
			}
			for(int j=1; j<=cm; j++) {
				dp[j] = Math.min(dp[j], cc);
			}
			//            System.out.println(Arrays.toString(dp));
		}
		System.out.println(dp[M]);
	}
}
