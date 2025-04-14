package baekjoon.Quiz16938;

import java.io.*;

/*
Gold5: 캠프 준비 / [dfs, bitmask]
1. 비트 마스킹을 활용해 모든 문제의 부분 집합을 구한다.
2. 선택한 문제들이 주어진 조건을 충족하는 경우에만 개수를 증가시킨다.
*/
public class Quiz16938 {

	private static final int MIN_VALUE = 1_000_001;
	private static final int MAX_VALUE = 0;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] nlrx = br.readLine().split(" ");
		int N = Integer.parseInt(nlrx[0]);
		int L = Integer.parseInt(nlrx[1]);
		int R = Integer.parseInt(nlrx[2]);
		int X = Integer.parseInt(nlrx[3]);

		int[] ranks = Arrays.stream(br.readLine().split(" "))
			.mapToInt(Integer::parseInt)
			.toArray();

		int possiblePairCount = 0;
		for(int mask = 0; mask < (1 << N); mask++) {
			int min = MIN_VALUE;
			int max = MAX_VALUE;
			int count = 0;
			int sum = 0;

			for(int shift = 0; shift < N; shift++) {
				int rank = ranks[shift];

				if((mask & (1 << shift)) != 0) {
					min = Math.min(min, rank);
					max = Math.max(max, rank);
					count++;
					sum += rank;
				}
			}

			if(sum >= L && sum <= R && (max - min) >= X && count >= 2) {
				possiblePairCount++;
			}
		}

		System.out.println(possiblePairCount);
	}
}
