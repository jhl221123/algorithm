package programmers.basic.lv2;

import java.util.*;

public class DeliveryBox {
	public static void main(String[] args) {
		int result = solution(new int[] {4, 3, 1, 2, 5}); // 2
		System.out.println(result);
	}
	public static int solution(int[] order) {
		int[] belt = new int[order.length+1];
		for(int i=1; i<=order.length; i++) {
			belt[i] = i;
		}
		int beltIdx = 1;
		int orderIdx = 0;
		int answer = 0;
		ArrayDeque<Integer> ad = new ArrayDeque<>();
		while(beltIdx < belt.length) {
			while(beltIdx < belt.length
				&& order[orderIdx] != belt[beltIdx]) {
				ad.addFirst(belt[beltIdx]);
				beltIdx++;
			}
			while(beltIdx < belt.length
				&& orderIdx < order.length
				&& order[orderIdx] == belt[beltIdx]) {
				answer++;
				orderIdx++;
				beltIdx++;
			}
			while(!ad.isEmpty()
				&& orderIdx < order.length
				&& order[orderIdx] == ad.getFirst()) {
				answer++;
				orderIdx++;
				ad.removeFirst();
			}
		}
		return answer;
	}
}
