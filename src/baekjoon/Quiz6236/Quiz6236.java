package baekjoon.Quiz6236;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 전체 시간 복잡도: O(N*log(Max_K)) > Max_K: 10^9
public class Quiz6236 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int[] budget = new int[N];
		// 예산 입력, O(N)
		for(int i=0; i<N; i++) {
			budget[i] = Integer.parseInt(br.readLine());
		}
		// 1. 인출 횟수가 M보다 작거나 같은 K의 최소값을 구한다., O(N*log(Max_K))
		int a = 1;
		int b = N * 10000;
		int K = 0;
		while(a<=b) {
			int m = (a+b)/2;
			int withdrawCount = getWithdrawCount(budget, m);
			if(withdrawCount>M) a = m + 1;
			else {
				K = m;
				b = m - 1;
			}
		}
		// 결과 출력
		System.out.println(K);
	}

	static int getWithdrawCount(int[] arr, int m) {
		// 2. K일때 인출 횟수를 구한다.
		int count = 1;
		int limit = m;
		// 인출한 금액이 부족하면 다시 인출하고 인출 횟수를 증가시킨다., O(N)
		for(int money : arr) {
			// 하루 예산이 인출할 수 있는 금액보다 크다면 바로 종료, 해당 로직이 없다면 커버할 수 없는 예산도 해결한 것으로 간주하게 된다.
			if(money>m) return 1000000;
			if(limit<money) {
				count++;
				limit = m;
			}
			limit -= money;
		}
		return count;
	}
}
