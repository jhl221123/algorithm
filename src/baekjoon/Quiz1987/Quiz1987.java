package baekjoon.Quiz1987;

import java.util.Scanner;

// 전체 시간 복잡도 < O(4^(R*C))
public class Quiz1987 {
	static int[][] board;
	static boolean[] check;
	static int R;
	static int C;
	static int[] mr;
	static int[] mc;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		R = sc.nextInt();
		C = sc.nextInt();
		board = new int[R][C];
		for(int i=0; i<R; i++) {
			String line = sc.next();
			for(int j=0; j<C; j++) {
				board[i][j] = line.charAt(j) - 'A';
			}
		}
		check = new boolean[26];
		mr = new int[] {-1, 0, 1, 0};
		mc = new int[] {0, 1, 0, -1};
		check[board[0][0]] = true;
		int ans = recur(0, 0);
		System.out.println(ans);
	}
	private static int recur(int r, int c) {
		int ans = 0;
		for(int i=0; i<4; i++) {
			int nextR = r+mr[i];
			int nextC = c+mc[i];
			if(invalid(nextR, nextC)) continue;
			int target = board[nextR][nextC];
			if(check[target]) continue;
			check[target] = true;
			ans = Math.max(ans, recur(nextR, nextC));
			check[target] = false;
		}
		return ans+1;
	}
	private static boolean invalid(int r, int c) {
		return r < 0 || r >= R || c < 0 || c >= C;
	}
}
