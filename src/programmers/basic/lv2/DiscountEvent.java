package programmers.basic.lv2;

import java.util.*;

public class DiscountEvent {
	public static void main(String[] args) {
		long result = solution(
			new String[] {"banana", "apple", "rice", "pork", "pot"},
			new int[] {3, 2, 2, 2, 1},
			new String[] {"chicken", "apple", "apple", "banana", "rice", "apple", "pork", "banana", "pork", "rice", "pot", "banana", "apple", "banana"}
		); // 3
		System.out.println(result);
	}
	public static int solution(String[] want, int[] number, String[] discount) {
		Map<String, Integer> window = new HashMap<>();
		int answer = 0;

		for(int i=0; i<10; i++) {
			String current = discount[i];
			insert(current, window);
		}
		if(isMeet(want, number, window)) answer++;

		for(int i=10; i<discount.length; i++) {
			String next = discount[i];
			String prior = discount[i-10];
			insert(next, window);
			delete(prior, window);
			if(isMeet(want, number, window)) answer++;
		}

		return answer;
	}
	private static boolean isMeet(String[] want, int[] number, Map<String, Integer> window) {
		for(int i=0; i<want.length; i++) {
			String target = want[i];
			if(!window.containsKey(target) || window.get(target) < number[i]) return false;
		}
		return true;
	}
	private static void insert(String target, Map<String, Integer> window) {
		if(window.containsKey(target)) window.put(target, window.get(target) + 1);
		else window.put(target, 1);
	}
	private static void delete(String target, Map<String, Integer> window) {
		if(window.get(target) == 1) window.remove(target);
		else window.put(target, window.get(target) - 1);
	}
}
