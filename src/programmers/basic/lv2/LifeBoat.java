package programmers.basic.lv2;

import java.util.*;

public class LifeBoat {
	public static void main(String[] args) {
		int result = solution(new int[] {70, 50, 80, 50}, 100); // 3
		System.out.println(result);
	}
	public static int solution(int[] people, int limit) {
		int s = 0;
		int e = people.length - 1;
		int answer = 0;
		Arrays.sort(people);
		while(s < e) {
			if(people[e] + people[s] > limit) {
				answer++;
				people[e] = 0;
				e--;
			} else {
				answer++;
				people[e] = 0;
				people[s] = 0;
				e--;
				s++;
			}
		}
		if(people[e] != 0 || people[s] != 0) answer++;
		return answer;
	}
}
