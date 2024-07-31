package programmers.basic.lv2;

import java.util.*;

public class DivideRollCake {
	public int solution(int[] toppings) {
        /*
        1. 토핑 종류별 가짓수를 맵으로 관리
        2. 전체 토핑 순회
          2-1. left=0, right=key.size
          2-2. 타겟 토핑 개수--
          2-3. 타겟 토핑 개수가 0이 되면 right--
        3. left의 visit 배열로 최초 방문 검증
          3-1. 최초 방문이라면 left++
        4. left == right 라면 answer++
        */
		Map<Integer, Integer> map = new HashMap<>();
		for(int topping : toppings) {
			if(map.containsKey(topping)) map.put(topping, map.get(topping) + 1);
			else map.put(topping, 1);
		}
		int left = 0;
		int right = map.keySet().size();
		boolean[] visit = new boolean[10001];
		int answer = 0;
		for(int topping : toppings) {
			map.put(topping, map.get(topping) - 1);
			if(map.get(topping) == 0) right--;
			if(!visit[topping]) {
				visit[topping] = true;
				left++;
			}
			if(left == right) answer++;
		}
		return answer;
	}
}
