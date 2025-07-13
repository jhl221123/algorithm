package baekjoon.Quiz1256;

import java.io.*;

/*
Gold2: 사전 / [dp]
1. 현재 K가 위치하는 조합 범위를 구한다.
2. 범위를 구했다면 남은 a와 하나의 z를 추가한다.
3. N, M, K를 조정한다.
*/
public class Quiz1256 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] NMK = br.readLine().split(" ");
		int N = Integer.parseInt(NMK[0]);
		int M = Integer.parseInt(NMK[1]);
		int K = Integer.parseInt(NMK[2]);

		StringBuilder ans = new StringBuilder();
		while(true) {
			if(K == 1) {
				for(int i=0; i<N; i++) {
					ans.append("a");
				}
				for(int i=0; i<M; i++) {
					ans.append("z");
				}
				break;
			}

			long prev = 1;
			boolean isPossible = false;
			for(int i=1; i<=N; i++) {
				long c = calC(M + i, i);
				if(c >= K) {
					for(int j=0; j< (N - i); j++) {
						ans.append("a");
					}
					ans.append("z");

					N = i;
					M--;
					K -= prev;
					isPossible = true;
					break;
				}
				prev = c;
			}

			if(!isPossible) {
				ans = new StringBuilder().append(-1);
				break;
			}
		}

		System.out.println(ans);
	}

	private static long calC(int a, int b) {
		long c = 1;
		long t = Math.max(a - b, b);
		for(int i=a; i>t; i--) {
			c *= i;
		}

		long d = 1;
		long t2 = Math.min(a - b, b);
		for(int i=1; i<=t2; i++) {
			d *= i;
		}

		return c / d;
	}
}
