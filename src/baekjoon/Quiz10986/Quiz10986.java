package baekjoon.Quiz10986;

import java.util.Scanner;

// i+1부터 j 까지의 구간합이 M으로 나누어 떨어지려면 (sum[j] - sum[i]) % M == 0을 만족해야 한다.
// 결과적으로 sum[j] % M == sum[i] % M을 만족하는 구간의 총 합이 답이 된다.
// 구간합 배열 acc를 활용해서 나머지가 같은 구간의 개수를 구하면 된다.
// 전체 시간 복잡도: O(N)
public class Quiz10986 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int M = sc.nextInt();
		int[] arr = new int[N+1];
		for(int i=1; i<=N; i++) {
			arr[i] = sc.nextInt();
		}
		int[] accRemainCount = new int[M];
		int currentRemain = 0;
		accRemainCount[currentRemain]++;
		long ans = 0L;
		for(int i=1; i<=N; i++) {
			currentRemain = (currentRemain + arr[i]) % M;
			ans += accRemainCount[currentRemain];
			accRemainCount[currentRemain]++;
		}
		System.out.println(ans);
	}
}
