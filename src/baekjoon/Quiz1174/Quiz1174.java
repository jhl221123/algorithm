package baekjoon.Quiz1174;

import java.util.*;
import java.io.*;

/*
Gold5: 줄어드는 수 / [brute-force, backtracking]
1. 9 -> 0 순서대로 깊이 우선 탐색으로 추가한다.
2. 완성된 수 목록을 오름차순으로 정렬한다.
3. 수의 범위가 int 형을 벗어나기 때문에 long을 사용한다.
*/
public class Quiz1174 {

	private static int[] numbers = {9, 8, 7, 6, 5, 4, 3, 2, 1, 0};
	private static List<Long> reducedNumbers = new ArrayList<>();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		initializeReducedNumbers();
		if(reducedNumbers.size() < N) {
			System.out.println(-1);
		} else {
			System.out.println(reducedNumbers.get(N - 1));
		}
	}

	private static void initializeReducedNumbers() {
		dfs(0, 0);
		reducedNumbers.sort(Comparator.comparingLong(o -> o));
	}

	private static void dfs(long number, int idx) {
		if(!reducedNumbers.contains(number)) {
			reducedNumbers.add(number);
		}

		if(idx >= 10) {
			return;
		}

		dfs(number * 10 + numbers[idx], idx + 1);
		dfs(number, idx + 1);
	}
}
