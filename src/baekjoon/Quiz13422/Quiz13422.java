package baekjoon.Quiz13422;

import java.util.Scanner;

// T: 테스트 케이스 만큼 반복, 출력
// 1. 처음 M 만큼 먼저 계산, 이후 l,r 이동하면서 금액 계산
// 2. K 미만이라면 개수 측정
// 전체 시간 복잡도: O(N)
public class Quiz13422 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		StringBuilder sb = new StringBuilder();
		while(T-- > 0) {
			int N = sc.nextInt();
			int M = sc.nextInt();
			int K = sc.nextInt();

			int[] arr = new int[N];
			for(int i=0; i<N; i++) {
				arr[i] = sc.nextInt();
			}
			int sum = 0;
			for(int i=0; i<M; i++) {
				sum += arr[i];
			}
			int cnt = (sum < K ? 1:0);
			// %를 사용해서 원형 탐색
			if (N != M) {
				for(int l=1, r=M; l<N; l++, r++) {
					sum -= arr[l-1];
					sum += arr[r%N];
					if(sum < K) cnt++;
				}
			}
			sb.append(cnt).append("\n");
		}
		System.out.println(sb);
	}
}
