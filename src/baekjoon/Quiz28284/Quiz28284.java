package baekjoon.Quiz28284;

import java.util.*;
import java.io.*;

public class Quiz28284 {
	static int N, M;
	static Integer[] A;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		A = new Integer[N];
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<N; i++) {
			A[i] = Integer.parseInt(st.nextToken());
		}

		// 요금 내림차순 구간합
		Arrays.sort(A, ((o1, o2) -> o2 - o1));
		long[] descSumA = new long[N+1];
		for(int i=1; i<=Math.max(N, M); i++) {
			descSumA[i] = descSumA[i-1] + A[i-1];
		}

		// 요금 오름차순 구간합
		Arrays.sort(A);
		long[] ascSumA = new long[N+1];
		for(int i=1; i<=Math.max(N, M); i++) {
			ascSumA[i] = ascSumA[i-1] + A[i-1];
		}

		// 사용기록 정렬
		Point[] points = new Point[M * 2];
		int pointIdx = 0;
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			int in = Integer.parseInt(st.nextToken());
			int out = Integer.parseInt(st.nextToken());
			points[pointIdx++] = new Point(in, true);
			points[pointIdx++] = new Point(out+1, false);
		}
		Arrays.sort(points, Comparator.comparingInt(o -> o.pos));

		// 사용량 계산
		int pos = 0;
		int cnt = 0;
		long maxResult = 0;
		long minResult = 0;
		for(int i=0; i<M*2; i++) {
			maxResult += (long)(points[i].pos - pos) * descSumA[cnt];
			minResult += (long)(points[i].pos - pos) * ascSumA[cnt];
			pos = points[i].pos;
			if(points[i].isIn) cnt++;
			else cnt--;
		}

		System.out.println(minResult + " " + maxResult);
	}
	static class Point {
		int pos;
		boolean isIn;

		public Point(int pos, boolean isIn) {
			this.pos = pos;
			this.isIn = isIn;
		}
	}
}
