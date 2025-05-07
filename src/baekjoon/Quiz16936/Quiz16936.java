package baekjoon.Quiz16936;

import java.io.*;
import java.util.*;

/*
Gold5: 나3곱2 / [math, dfs]
1. 수열에서 다음 수가 이전 수 / 3 혹은 이전 수 * 2를 만족시키도록 정렬한다.
*/
public class Quiz16936 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		long[] B = Arrays.stream(br.readLine().split(" "))
			.mapToLong(Long::parseLong)
			.toArray();
		dfs(0, N, B, new long[N], new boolean[N]);
	}

	private static boolean dfs(int d, int e, long[] B, long[] A, boolean[] v) {
		if(d == e) {
			StringBuilder sb = new StringBuilder();
			for(int i=0; i<e; i++) {
				sb.append(A[i]).append(" ");
			}
			System.out.println(sb);
			return true;
		}

		for(int i=0; i<e; i++) {
			if(v[i]) continue;
			v[i] = true;
			A[d] = B[i];
			if(d == 0 || A[d - 1] == A[d] * 3 || A[d - 1] * 2 == A[d]) {
				if(dfs(d + 1, e, B, A, v)) return true;
			}
			v[i] = false;
		}

		return false;
	}
}
