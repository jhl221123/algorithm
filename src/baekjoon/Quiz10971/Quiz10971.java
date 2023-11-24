package baekjoon.Quiz10971;

import java.util.Scanner;

// 전체 시간 복잡도: O(N!)
public class Quiz10971 {
	public static int n;
	public static int[][] w;
	public static boolean[] check;
	public static int ans;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		n = sc.nextInt();
		w = new int[n][n];
		check = new boolean[n];
		for(int i=0; i<n; i++) {
			for(int j=0; j<n; j++) {
				w[i][j] = sc.nextInt();
			}
		}
		ans = Integer.MAX_VALUE;
		recur(0, 0, 0, 0);
		System.out.println(ans);
	}
	private static void recur(int cnt, int sum, int start, int node) {
		if(cnt == n && start == node) {
			if(ans > sum) ans = sum;
			return;
		}
		for(int i=0; i<n; i++) {
			if(!check[i] && w[node][i] != 0) {
				check[i] = true;
				recur(cnt+1, sum + w[node][i], start, i);
				check[i] = false;
			}
		}
	}
}
