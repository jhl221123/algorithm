package programmers.basic.lv1;

import java.util.Arrays;

public class AddMissingNumbers {
	public static void main(String[] args) {
		int result = solution(new int[]{1,2,3,4,6,7,8,0}); // 14
		System.out.println(result);
	}
	public static int solution(int[] numbers) {
		int idx = 0;
		int answer = 0;
		Arrays.sort(numbers);
		for(int i=0; i<10; i++) {
			if(idx < numbers.length && numbers[idx] == i) {
				idx++;
				continue;
			}
			answer += i;
		}

		return answer;
	}
}
