package baekjoon.Quiz2792;

import java.util.Scanner;

// 탐색 범위는 1~10^9
// 전체 시간 복잡도: O(Mlog10^9)
public class Quiz2792 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int M = sc.nextInt();
		int[] jewels = new int[M];
		for(int i=0; i<M; i++) {
			jewels[i] = sc.nextInt();
		}
		int l = 1;
		int r = 1000000000;
		int mid;
		int ans = 0;
		while(l<=r) {
			mid = (l+r)/2;
			if(isPossible(jewels, mid, N)) {
				r = mid - 1;
				ans = mid;
			} else l = mid + 1;
		}
		System.out.println(ans);
	}
	private static boolean isPossible(int[] jewels, int mid, int N) {
		int children = 0;
		for(int i=0; i<jewels.length; i++) {
			if(jewels[i] <= mid) children++;
			else if(jewels[i]%mid==0) children += jewels[i]/mid;
			else children += jewels[i]/mid + 1;
			if(children > N) return false;
		}
		return true;
	}
}
