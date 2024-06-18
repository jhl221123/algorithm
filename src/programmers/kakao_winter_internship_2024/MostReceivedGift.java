package programmers.kakao_winter_internship_2024;

import java.util.*;

public class MostReceivedGift {
	static Map<String, Integer> map = new HashMap<>();
	static int[][] before;
	static int[] after;
	static int friendIdx;

	public static void main(String[] args) {
		// // Test case 1, answer: 2
		// String[] friends = {"muzi", "ryan", "frodo", "neo"};
		// String[] gifts = {"muzi frodo", "muzi frodo", "ryan muzi", "ryan muzi", "ryan muzi", "frodo muzi", "frodo ryan", "neo muzi"};

		// // Test case 2, answer: 4
		// String[] friends = {"joy", "brad", "alessandro", "conan", "david"};
		// String[] gifts = {"alessandro brad", "alessandro joy", "alessandro conan", "david alessandro", "alessandro david"};

		// Test case 3, answer: 0
		String[] friends = {"a", "b", "c"};
		String[] gifts = {"a b", "b a", "c a", "a c", "a c", "c a"};

		System.out.println(solution(friends, gifts));
	}

	public static int solution(String[] friends, String[] gifts) {
		input(friends, gifts);
		givePresentEachOther();
		int answer = findMax();
		return answer;
	}
	private static int findMax() {
		int max = 0;
		for(int i=0; i<friendIdx; i++) {
			max = Math.max(max, after[i]);
		}
		return max;
	}
	private static void givePresentEachOther() {
		for(int i=0; i<friendIdx; i++) {
			for(int j=i; j<friendIdx; j++) {
				if(before[i][j] > before[j][i]) after[i]++;
				else if(before[i][j] < before[j][i]) after[j]++;
				else {
					int scoreI = sumGiftScore(i);
					int scoreJ = sumGiftScore(j);
					if(scoreI > scoreJ) after[i]++;
					else if(scoreI < scoreJ) after[j]++;
				}
			}
		}
	}
	private static int sumGiftScore(int sender) {
		int sum = 0;
		for(int i=0; i<friendIdx; i++) {
			sum += before[sender][i];
			sum -= before[i][sender];
		}
		return sum;
	}
	private static void input(String[] friends, String[] gifts) {
		for(String friend : friends) {
			map.put(friend, friendIdx++);
		}
		before = new int[friendIdx][friendIdx];
		after = new int[friendIdx];
		for (String gift : gifts) {
			StringTokenizer st = new StringTokenizer(gift);
			int sender = map.get(st.nextToken());
			int receiver = map.get(st.nextToken());
			before[sender][receiver]++;
		}
	}
}
