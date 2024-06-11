package baekjoon.Quiz16401;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 1. 입력받은 과자를 순회 -> 과자 길이의 중간값부터 이분탐색으로 최대값을 탐색
// 2. 이분 탐색으로 정해진 예상 길이의 가능 여부를 확인
// 2-1. 가능하다면 right로 이동
// 2-2. 불가능하다면 left로 이동
// 시간 복잡도: Nlog(L), L: 과자 길이(1 ~ 1,000,000,000)
public class Quiz16401 {
	static int M, N;
	static int left, mid = 0;
	static int right = 1000000000;
	static int[] snack;
	static int result;
	public static void main(String[] args) throws IOException {
		input();
		ParametricSearch();
		System.out.println(result);
	}

	private static void ParametricSearch() {
		while(left <= right) {
			mid = (left + right) / 2;
			if(isPossible(mid)) {
				left = mid + 1;
				result = mid;
			} else {
				right = mid - 1;
			}
		}
	}

	private static boolean isPossible(int length) {
		if(length == 0) return true;
		int childrenNum = M;
		for(int i=0; i<N; i++) {
			childrenNum -= snack[i]/length;
			if(childrenNum <= 0) return true;
		}
		return false;
	}

	private static void input() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		snack = new int[N];
		st = new StringTokenizer(br.readLine());

		for(int i=0; i<N; i++) {
			snack[i] = Integer.parseInt(st.nextToken());
			right = Math.max(right, snack[i]);
		}
	}
}
