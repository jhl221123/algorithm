package baekjoon.Quiz11444;

import java.io.*;

/*
1. Fn+2 = Fn+1 + Fn -> Fn+2 = {1, 1} * (Fn+1/Fn)
2. Fn+1 = Fn+1 + Fn*0 -> Fn+1 = {1, 0} * (Fn+1/Fn)
3. 1, 2 과정으로 인해 (Fn+2/Fn+1) = {{1, 1}, {1, 0}} * (Fn+1/Fn) 을 만족한다.
4. (Fn+2/Fn+1) = {{1, 1}, {1, 0}} * (Fn+1/Fn) -> Un+1 = A * Un -> Un = A ^ n * U0
5. (Fn+1/Fn) = {{1, 1}, {1, 0}} ^ n * (F1/F0) = {{1, 1}, {1, 0}} ^ n * (1/0)
6. 따라서 n번째 피보나치 수는 A ^ (n-1) 의 [1, 1]이다.
 */
public class Quiz11444 {
	private static final long[][] initial = {{1, 1}, {1, 0}};
	private static final long MOD = 1_000_000_007;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		long N = Long.parseLong(br.readLine());

		long[][] A = {{1, 1}, {1, 0}};
		long[][] An = pow(A, N - 1);
		System.out.println(An[0][0]);
	}

	private static long[][] pow(long[][] A, long exp) {
		if(exp == 0 || exp == 1) {
			return A;
		}

		long[][] ret = pow(A, exp / 2);
		ret = multiply(ret, ret);

		if(exp % 2 == 1L) {
			ret = multiply(ret, initial);
		}

		return ret;
	}

	private static long[][] multiply(long[][] o1, long[][] o2) {
		long[][] ret = new long[2][2];

		for(int k=0; k<2; k++) {
			for(int i=0; i<2; i++) {
				for(int j=0; j<2; j++) {
					ret[i][j] += o1[i][k] * o2[k][j];
					ret[i][j] %= MOD;
				}
			}
		}

		return ret;
	}
}
