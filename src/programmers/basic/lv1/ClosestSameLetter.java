package programmers.basic.lv1;

import java.util.*;

public class ClosestSameLetter {
	public static void main(String[] args) {
		int[] result = solution("banana"); // [-1, -1, -1, 2, 2, 2]
		System.out.println(Arrays.toString(result));
	}

	public static int[] solution(String s) {
		int[] alphabet = new int[27];
		int[] answer = new int[s.length()];
		Arrays.fill(alphabet, -1);
		Arrays.fill(answer, -1);
		for (int i = 0; i < s.length(); i++) {
			int idx = s.charAt(i) - 'a' + 1;
			if (alphabet[idx] != -1) {
				answer[i] = i - alphabet[idx];
			}
			alphabet[idx] = i;
		}
		return answer;
	}
}
