package baekjoon.Quiz1208;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

// 전체 시간 복잡도: O((N/2)!)
public class Quiz1208 {
	static int[] nums;
	static Map<Integer, Integer> map;
	static long ans;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int s = sc.nextInt();
		nums = new int[n];
		for(int i=0; i<n; i++) {
			nums[i] = sc.nextInt();
		}
		map = new HashMap<>();
		recur(0, n/2, 0, s, true);
		recur(n/2, n, 0, s, false);
		if(s==0) ans--;
		System.out.println(ans);
	}
	private static void recur(int start, int end, int sum, int s, boolean isLeft) {
		if(start == end) {
			if(isLeft) {
				int pre = map.getOrDefault(sum, 0);
				map.put(sum, pre+1);
			} else {
				ans += map.getOrDefault(s-sum, 0);
			}
			return;
		}
		recur(start + 1, end, sum, s, isLeft);
		recur(start + 1, end, sum + nums[start], s, isLeft);
	}
}
