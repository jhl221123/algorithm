package baekjoon.Quiz2003;

import java.util.Scanner;

// 전체 시간 복잡도: O(N)
public class Quiz2003 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int M = sc.nextInt();
		int[] A = new int[N];
		// 수열 입력, O(N)
		for(int i=0; i<N; i++) {
			A[i] = sc.nextInt();
		}
		// 1.범위의 합이 M보다 크거나 같을 때까지 while, O(N)
		// 1-1.범위의 합이 M보다 작다면 계속해서 j를 한칸 앞으로 옮기고 합에서 arr[j] 증가
		// 1-2.범위의 합이 M보다 크거나 같다면 i를 한칸 앞으로 옮긴다. > 합에서 arr[i] 감소
		int cnt=0;
		int sum = A[0];
		int j=0;
		for(int i=0; i<N; i++) {
			while(sum<M && j<N-1) {
				sum += A[++j];
			}
			if(sum==M) cnt++;
			sum -= A[i];
		}
		System.out.println(cnt);
	}
}
