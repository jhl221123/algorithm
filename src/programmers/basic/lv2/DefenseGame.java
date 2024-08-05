package programmers.basic.lv2;

import java.util.*;

public class DefenseGame {
	public static void main(String[] args) {
		long result = solution(6, 3, new int[] {4, 2, 4, 5, 3, 3, 1}); // 5
		System.out.println(result);
	}
	public static int solution(int n, int k, int[] enemy) {
		PriorityQueue<Integer> pq = new PriorityQueue<>();
		for(int i=0; i<enemy.length; i++) {
			int ce = enemy[i];
			if(pq.size() < k) pq.offer(ce);
			else {
				if(pq.peek() >= ce) n -= ce;
				else {
					n -= pq.poll();
					pq.offer(ce);
				}
			}
			if(n < 0) return i;
		}
		return enemy.length;
	}
}
