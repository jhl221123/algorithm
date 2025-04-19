package baekjoon.Quiz2666;

import java.util.*;
import java.io.*;

/*
Gold5: 벽장문의 이동 / [dfs]
1. 타겟과 두 개의 문 사이 거리를 각각 구한 후, 최소값을 반환한다.
2. 모든 타겟을 탐색한 후 결과를 출력한다.
*/
public class Quiz2666 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[] doors = Arrays.stream(br.readLine().split(" "))
			.mapToInt(Integer::parseInt)
			.toArray();
		int M = Integer.parseInt(br.readLine());
		int[] target = new int[M];
		for(int i=0; i<M; i++) {
			target[i] = Integer.parseInt(br.readLine());
		}

		System.out.println(dfs(0, target, doors[0], doors[1]));
	}

	private static int dfs(int targetIdx, int[] target, int doorA, int doorB) {
		if(targetIdx >= target.length) {
			return 0;
		}

		int gapA = Math.abs(doorA - target[targetIdx]);
		int gapB = Math.abs(doorB - target[targetIdx]);

		return Math.min(
			gapA + dfs(targetIdx + 1, target, target[targetIdx], doorB),
			gapB + dfs(targetIdx + 1, target, doorA, target[targetIdx])
		);
	}
}
