package baekjoon.Quiz10025;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

// 양동이가 위치한 최소, 최대 좌표값을 초기화해두고 K와 함께 사용
// 양동이 좌표를 key로 관리하는 Map을 사용
// K 범위의 양 끝 지점이 이동할 때, 해당 좌표에 양동이가 있다면 추가 혹은 제거하면서 반복문 수행
// 전체 시간 복잡도: O(N)
public class Quiz10025 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int K = sc.nextInt();
		Map<Integer, Bucket> map = new HashMap<>();
		int max = -1;
		int min = 1000001;
		for(int i=0; i<N; i++) {
			int currentG = sc.nextInt();
			int currentX = sc.nextInt();
			max = Math.max(max, currentX);
			min = Math.min(min, currentX);
			map.put(currentX, new Bucket(currentX, currentG));
		}
		int sum = 0;
		for(int i=min; i<=min+(K*2); i++) {
			if(map.containsKey(i)) sum += map.get(i).getG();
		}
		int ans = sum;
		for(int i=min+1, j=min+(K*2)+1; j<=max; i++, j++) {
			if(map.containsKey(i-1)) sum -= map.get(i-1).getG();
			if(map.containsKey(j)) sum += map.get(j).getG();
			ans = Math.max(ans, sum);
		}
		System.out.println(ans);
	}
}

class Bucket {
	private int x;
	private int g;

	public Bucket(int x, int g) {
		this.x = x;
		this.g = g;
	}

	public int getX() {
		return this.x;
	}
	public int getG() {
		return this.g;
	}
}
