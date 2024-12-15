package baekjoon.Quiz10830;

import java.io.*;
import java.util.*;

public class Quiz10830 {
	private static int N;
	private static int[][] A;
	private static int MOD = 1000;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		long B = Long.parseLong(st.nextToken());
		A = new int[N][N];

		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<N; j++) {
				A[i][j] = Integer.parseInt(st.nextToken()) % MOD;
			}
		}

		int[][] ans = powMatrix(A, B);
		StringBuilder sb = new StringBuilder();
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				sb.append(ans[i][j]).append(" ");
			}
			sb.append("\n");
		}

		System.out.print(sb);
	}

	private static int[][] powMatrix(int[][] matrix, long exp) {
		if(exp == 1L) {
			return matrix;
		}

		int[][] result = powMatrix(matrix, exp / 2);
		result = multiplyMatrix(result, result);

		// 현재 지수가 홀수라면 원래 행렬을 한번 더 곱한다.
		if(exp % 2 == 1) {
			result = multiplyMatrix(result, A);
		}

		return result;
	}

	private static int[][] multiplyMatrix(int[][] left, int[][] right) {
		int[][] result = new int[N][N];

		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				for(int k=0; k<N; k++) {
					result[i][j] += ((left[i][k] * right[k][j]) % MOD);
					result[i][j] %= MOD;
				}
			}
		}

		return result;
	}
}
