package programmers.basic.lv1;

import java.util.Arrays;

public class CardDummy {
	public static void main(String[] args) {
		String[] cards1 = {"i", "drink", "water"};
		String[] cards2 = {"want", "to"};
		String[] goal = {"i", "want", "to", "drink", "water"};
		String result = solution(cards1, cards2, goal); // "Yes"
		System.out.println(result);
	}
	public static String solution(String[] cards1, String[] cards2, String[] goal) {
		int cards1Idx = 0;
		int cards2Idx = 0;
		int goalIdx = 0;
		int priorCards1Idx = -1;
		int priorCards2Idx = -1;
		while(cards1Idx != priorCards1Idx || cards2Idx != priorCards2Idx) {
			priorCards1Idx = cards1Idx;
			priorCards2Idx = cards2Idx;
			while(goalIdx < goal.length &&
				cards1Idx < cards1.length &&
				cards1[cards1Idx].equals(goal[goalIdx])) {
				goalIdx++;
				cards1Idx++;
			}
			while(goalIdx < goal.length &&
				cards2Idx < cards2.length &&
				cards2[cards2Idx].equals(goal[goalIdx])) {
				goalIdx++;
				cards2Idx++;
			}
			if(goalIdx == goal.length) return "Yes";
		}
		return "No";
	}
}
