package baekjoon.Quiz6198;

import java.util.*;
import java.io.*;

/*
Gold5: 옥상 정원 꾸미기 / [data structure, stack]
1. 스택으로 건물 높이를 관리하며 개수를 도출한다.
2. 현재 건물이 스택 최상단 건물보다 낮다면 스택에 넣는다.
3. 현재 건물이 스택 최산단 건물보다 크거나 같다면, 낮아질 때까지 건물을 제거한다.
3-1. 건물을 제거할 때, 현재 제거하는 건물의 count 는 이전 제거한 건물 count + 1이다.
4. 남은 건물의 count를 계산한다.
5. 모든 건물의 count 합계를 출력한다.
*/
public class Quiz6198 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[] heights = new int[N];
		int[] counts = new int[N];

		for(int i = 0; i < N; i++) {
			heights[i] = Integer.parseInt(br.readLine());
		}

		ArrayDeque<Integer> stack = new ArrayDeque<>();
		for(int i=0; i<N; i++) {
			if(stack.isEmpty()) {
				stack.addLast(i);
				continue;
			}

			int additional = 0;
			while(!stack.isEmpty()) {
				int prior = stack.getLast();

				if(heights[prior] <= heights[i]) {
					counts[prior] += additional;
					additional = counts[prior] + 1;
					stack.removeLast();
				} else {
					counts[prior] += additional;
					break;
				}
			}

			stack.addLast(i);
		}

		int additional = 0;
		while(!stack.isEmpty()) {
			int prior = stack.removeLast();
			counts[prior] += additional;
			additional = counts[prior] + 1;
		}

		long totalCount = 0;
		for(int i=0; i<N; i++) {
			totalCount += counts[i];
		}

		System.out.println(totalCount);
	}
}
