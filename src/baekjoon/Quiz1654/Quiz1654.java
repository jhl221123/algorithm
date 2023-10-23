package baekjoon.Quiz1654;

import java.util.Scanner;

// 전체 시간 복잡도: O(K*logL)
public class Quiz1654 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int K = sc.nextInt();
		int N = sc.nextInt();
		int[] arr = new int[K];
		// 랜선 입력, O(K)
		for(int i=0; i<K; i++) {
			arr[i] = sc.nextInt();
		}
		// 각 랜선을 파라미터로 나눈 전체 몫이 N 보다 크거나 같은 최소값을 구한다., O(K*logL) > L: 1~2^31-1
		long a = 1;
		long b = Integer.MAX_VALUE;
		long ans = 0;
		while(a<=b) {
			long m = (a+b)/2;
			int totalCount = getTotalCount(arr, m);
			if(totalCount<N) b = m-1;
			else {
				a = m+1;
				ans = m;
			}
		}
		System.out.println(ans);
	}
	static int getTotalCount(int[] arr, long m) {
		// 각 랜선을 파라미터로 나눠서 전체 몫을 반환한다., O(K)
		int sum = 0;
		for(int length : arr) {
			sum += length/m;
		}
		return sum;
	}
}
