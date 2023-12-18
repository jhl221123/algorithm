package baekjoon.Quiz14465;

import java.util.Scanner;

// 투포인터로 반복문 수행 -> 연속되는 신호등(합계)이 K개 일때마다 답(수리개수)을 초기화
// r: 정상 신호등이라면 합계++, 망가진 신호등이라면 합계++, 수리개수++
// l: 정상 신호등이라면 합계--, 망가진 신호등이라면 합계--, 수리개수--
// 전체 시간 복잡도: O(N)
public class Quiz14465 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int K = sc.nextInt();
		int B = sc.nextInt();
		boolean[] broken = new boolean[N+1];
		for(int i=1; i<=B; i++) {
			broken[sc.nextInt()] = true;
		}
		int l = 1;
		int r = 1;
		int sum = 0;
		int repair = 0;
		int ans = N;
		while(r<=N) {
			if(sum < K) {
				if(broken[r]) repair++;
				sum++;
				r++;
			}
			if(sum == K) {
				ans = Math.min(ans, repair);
				if(broken[l]) repair--;
				sum--;
				l++;
			}
		}
		System.out.println(ans);
	}
}
