package baekjoon.Quiz32347;

import java.io.*;
import java.util.*;

/*
Gold5: 시간을 돌리고 싶어 / [binary search, parametric search]
1. 매개 변수 탐색을 활용해 이동 구간을 정한다.
2. 이동 구간이 조건을 충족하는지 확인한다.
2-1. 구간 내 전원이 모두 꺼져있다면, 미충족
2-2. 구간 내 전원이 켜져 있다면, 시작 지점에서 가장 먼 곳을 다음 출발지로 지정
3. 구간 길이가 최소가 될 때까지 반복한다.
*/
public class Quiz32347 {

	private static int N, K;
	private static int[] powers;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] nk = br.readLine().split(" ");
		N = Integer.parseInt(nk[0]);
		K = Integer.parseInt(nk[1]);
		powers = Arrays.stream(br.readLine().split(" "))
			.mapToInt(Integer::parseInt)
			.toArray();

		if(powers[N - 1] == 0) {
			System.out.println(-1);
			return;
		}

		int l = 1;
		int r = N;
		int min = 0;
		while(l <= r) {
			int m = (l + r) / 2;
			if(isPossible(m)) {
				r = m - 1;
				min = m;
			} else {
				l = m + 1;
			}
		}

		System.out.println(min);
	}

	private static boolean isPossible(int T) {
		int pivot = N - 1;
		int reachPoint = -1;

		for(int i=0; i<K; i++) {
			boolean flag = false;

			for(int j = 0, k = pivot - 1; j < T; j++, k--) {
				if(k >= 0 && powers[k] == 1) {
					flag = true;
					pivot = k;
				}
				reachPoint = k;
			}

			if(reachPoint <= 0) return true;
			if(!flag) return false;
		}

		return false;
	}
}
