package baekjoon.Quiz2512;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 이분 탐색으로 전체 합이 M 이하가 되는 시점을 출력
// 전체 시간 복잡도: O(NlogN)
public class Quiz2512 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[] arr = new int[N];
		int maxBudget = 100000;
		int minBudget = 1;
		int sum = 0;
		int largeBudget = 0;
		StringTokenizer st = new StringTokenizer(br.readLine());
		// O(N)
		for(int i=0; i<N; i++) {
			int budget = Integer.parseInt(st.nextToken());
			sum += budget;
			largeBudget = Math.max(largeBudget, budget);
			arr[i] = budget;
		}
		int M = Integer.parseInt(br.readLine());
		// 예산이 충분해서 상한선을 둘 필요가 없는 경우, 지역 예산의 최대값 출력
		if(sum<=M) {
			System.out.println(largeBudget);
			return;
		}
		// 예산이 충분하지 않아 상한선이 필요한 경우, 이분 탐색:O(NlogN)
		int limit = 0;
		int ans = 0;
		while(maxBudget>=minBudget) {
			limit = (maxBudget + minBudget)/2;
			sum = getSum(arr, limit);
			if(sum<=M) {
				minBudget = limit+1;
				ans = limit;
			}
			else maxBudget = limit-1;
		}
		System.out.println(ans);
	}
	private static int getSum(int[] arr, int limit) {
		int sum = 0;
		for (int budget : arr) {
			sum += Math.min(budget, limit);
		}
		return sum;
	}
}
