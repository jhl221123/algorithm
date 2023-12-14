package baekjoon.Quiz2343;

import java.util.Scanner;

// start, end : 1, 10억
// 블루레이에 담는 것이 가능하다면(==포함) 답 초기화, end = m-1
// 아니라면 start = m+1
// 전체 시간 복잡도: O(NlogS), S: 필요한 블루레이 최대 크기
public class Quiz2343 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int M = sc.nextInt();
		int[] times = new int[N];
		for(int i=0; i<N; i++) {
			times[i] = sc.nextInt();
		}
		int start = 1;
		int end = N * 10000;
		int mid;
		int ans = 0;
		while(start<=end) {
			mid = (start + end) / 2;
			if(isPossible(times, mid, M)) {
				end = mid - 1;
				ans = mid;
			} else start = mid + 1;
		}
		System.out.println(ans);
	}
	private static boolean isPossible(int[] times, int mid, int M) {
		int bluelaySize = mid;
		for(int i=0; i<times.length; i++) {
			if(times[i]>mid) return false;
			if(bluelaySize-times[i]<0) {
				M--;
				if(M==0) return false;
				bluelaySize = mid;
			}
			bluelaySize -= times[i];
		}
		return true;
	}
}
