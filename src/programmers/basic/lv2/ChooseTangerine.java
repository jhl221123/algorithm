package programmers.basic.lv2;

import java.util.*;

public class ChooseTangerine {
	public static void main(String[] args) {
		long result = solution(6, new int[] {1, 3, 2, 5, 4, 5, 2, 3}); // 3
		System.out.println(result);
	}
	public static int solution(int k, int[] tangerine) {
		Map<Integer, Integer> map = new HashMap<>();
		for(int w : tangerine) {
			if(map.containsKey(w)) map.put(w, map.get(w)+1);
			else map.put(w, 1);
		}
		PriorityQueue<Item> pq = new PriorityQueue<>((o1, o2) -> o2.v - o1.v);
		for(int key : map.keySet()) {
			pq.offer(new Item(key, map.get(key)));
		}
		int answer = 0;
		while(true) {
			if(k <= 0) break;
			k -= pq.poll().v;
			answer++;
		}

		return answer;
	}
	static class Item {
		int k;
		int v;

		public Item(int k, int v) {
			this.k = k;
			this.v = v;
		}
	}
}
