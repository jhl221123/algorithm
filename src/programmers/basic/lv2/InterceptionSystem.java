package programmers.basic.lv2;

import java.util.*;

public class InterceptionSystem {
	public static void main(String[] args) {
		int result = solution(new int[][] {{4, 5}, {4, 8}, {10, 14}, {11, 13}, {5, 12}, {3, 7}, {1, 4}}); // 3
		System.out.println(result);
	}
	public static int solution(int[][] targets) {
		int answer = 0;
		int offset = -1;

		Arrays.sort(targets, Comparator.comparingInt(o -> o[1]));

		for (int[] target : targets) {
			if(offset >= target[0]) {
				continue;
			} else {
				offset = target[1] - 1;
				answer++;
			}
		}

		return answer;
	}
}
