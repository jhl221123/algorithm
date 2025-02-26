package baekjoon.Quiz2749;

import java.io.*;

/*
Gold2: 피보나치 수 3 / [math, divide and conquer]
1. Fn+2 = Fn+1 + Fn -> Fn+2 = {1, 1} * (Fn+1/Fn)
2. Fn+1 = Fn+1 + Fn*0 -> Fn+1 = {1, 0} * (Fn+1/Fn)
3. 1, 2 과정으로 인해 (Fn+2/Fn+1) = {{1, 1}, {1, 0}} * (Fn+1/Fn) 을 만족한다.
4. (Fn+2/Fn+1) = {{1, 1}, {1, 0}} * (Fn+1/Fn) -> Un+1 = A * Un
5. U1 = A * U0; U2 = A * U1 = A * (A * U0) ... Un = A ^ n * U0
6. (Fn+1/Fn) = {{1, 1}, {1, 0}} ^ n * (F1/F0) = {{1, 1}, {1, 0}} ^ n * (1/0)
7. 따라서 n번째 피보나치 수는 A ^ (n-1) 의 [0, 0]이다.
*/
public class Quiz2749 {

	private static long[][] base = {{1, 1}, {1, 0}};
	private static long MOD = 1_000_000;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		long n = Long.parseLong(br.readLine());

		if(n == 1) System.out.println(1);
		else {
			long[][] A = {{1, 1}, {1, 0}};
			long[][] An = pow(A, n - 1);
			System.out.println(An[0][0]);
		}
	}

	private static long[][] pow(long[][] A, long exp) {
		if(exp == 1) return A;

		long[][] matrix = pow(A, exp / 2);
		long[][] result = multiply(matrix, matrix);

		if(exp % 2 == 1) {
			result = multiply(result, base);
		}

		return result;
	}

	private static long[][] multiply(long[][] A1, long[][] A2) {
		long[][] matrix = new long[2][2];

		for(int k=0; k<2; k++) {
			for(int r=0; r<2; r++) {
				for(int c=0; c<2; c++) {
					matrix[r][c] += A1[r][k] * A2[k][c] % MOD;
					matrix[r][c] %= MOD;
				}
			}
		}

		return matrix;
	}
}
