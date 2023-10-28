package baekjoon.Quiz2118;

import java.util.*;

// 전체 시간 복잡도: O(N)
public class Quiz2118TwoPointer {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int[] arr = new int[N];
		int sum = 0;
		// 좌표 입력 및 총 길이 계산, O(N)
		for(int i=0; i<N; i++) {
			arr[i] = sc.nextInt();
			sum+=arr[i];
		}
		int pairValue = 0;
		int forward = 0;
		int reverse = sum;
		int ans = 0;
		// O(N)
		for(int i=0; i<N; i++) {
			while(forward<reverse) {
				forward+=arr[pairValue];
				reverse-=arr[pairValue];
				pairValue = (pairValue+1)%N;
			}
			ans = Math.max(ans, reverse);
			forward-=arr[i];
			reverse+=arr[i];
		}
		System.out.println(ans);
	}
}
