package programmers.basic.lv2;

import java.util.*;

public class NextBigNumber {
	public static void main(String[] args) {
		int[] numbers = {2, 3, 3, 5};
		int[] result = solution(numbers); // [3, 5, 5, -1]
		System.out.println(Arrays.toString(result));
	}
	public static int[] solution(int[] numbers) {
		int[] answer = new int[numbers.length];
		ArrayDeque<Integer> ad = new ArrayDeque<>();
		for(int i=0; i<numbers.length; i++) {
			while(!ad.isEmpty() && numbers[ad.getFirst()] < numbers[i]) {
				answer[ad.removeFirst()] = numbers[i];
			}
			ad.addFirst(i);
		}
		while(!ad.isEmpty()) {
			answer[ad.removeFirst()] = -1;
		}
		return answer;
	}
}
