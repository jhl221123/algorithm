package baekjoon.Quiz2631;

import java.util.*;
import java.io.*;

/*
Gold4: 줄세우기 / [DP, LIS]
1. 입력된 줄에서 최장 증가 수열의 길이를 구한다.
2. 사람 수에서 최장 증가 수열의 길이를 뺀 값이 최소 이동 횟수가 된다.
 */
public class Quiz2631 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[] sequenceNumbers = new int[N];
		for(int i=0; i<N; i++) {
			sequenceNumbers[i] = Integer.parseInt(br.readLine());
		}

		List<Integer> lis = new ArrayList<>();
		lis.add(sequenceNumbers[0]);
		for(int i=1; i<N; i++) {
			int next = sequenceNumbers[i];

			if(next > lis.get(lis.size() - 1)) {
				lis.add(next);
			} else {
				lis.set(searchIdx(next, lis), next);
			}
		}

		System.out.println(N - lis.size());
	}

	private static int searchIdx(int number, List<Integer> lis) {
		int idx = 0;

		int l = 0;
		int r = lis.size() - 1;
		while(l <= r) {
			int m = (l + r) / 2;

			if(number <= lis.get(m)) {
				idx = m;
				r = m - 1;
			} else {
				l = m + 1;
			}
		}

		return idx;
	}
}
