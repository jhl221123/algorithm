package programmers.basic.lv1;

import java.util.*;

public class HallOfFame {
	public static void main(String[] args) {
		int k = 3;
		int[] score = {10, 100, 20, 150, 1, 100, 200};
		int[] result = solution(k, score); // [10, 10, 10, 20, 20, 100, 100]
		System.out.println(Arrays.toString(result));
	}
	public static int[] solution(int k, int[] score) {
		PriorityQueue<Integer> q = new PriorityQueue<>(k, (o1, o2) -> o1 - o2);
		int[] answer = new int[score.length];
		for(int i=0; i<score.length; i++) {
			if(!q.isEmpty() && q.size() == k && q.peek() < score[i]) q.poll();
			if(q.size() < k) q.offer(score[i]);
			answer[i] = q.peek();
		}
		return answer;
	}
}
