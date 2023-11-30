package baekjoon.Quiz1987;

import java.util.Scanner;

// 전체 시간 복잡도 < O(4^(R*C))
public class Quiz1987_backtracking {
	static int R;
	static int C;
	static int[][] board;
	static boolean[] check;
	static int[][] visit;
	static int[] mr;
	static int[] mc;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		R = sc.nextInt();
		C = sc.nextInt();
		board = new int[R][C];
		visit = new int[R][C];
		for(int i=0; i<R; i++) {
			String line = sc.next();
			for(int j=0; j<C; j++) {
				board[i][j] = line.charAt(j) - 'A';
			}
		}
		mr = new int[]{-1, 0, 1, 0};
		mc = new int[]{0, 1, 0, -1};
		check = new boolean[26];
		check[board[0][0]] = true;
		visit[0][0] = 1<<board[0][0];
		int ans = recur(0, 0);
		System.out.println(ans);
	}

	private static int recur(int r, int c) {
		int result = 0;
		for(int i=0; i<4; i++) {
			int nextR = r + mr[i];
			int nextC = c + mc[i];
			if(invalid(nextR, nextC)) continue;
			int next = board[nextR][nextC];
			if(check[next]) continue;
			if(visit[nextR][nextC] == (visit[r][c] | 1<<next)) continue;
			visit[nextR][nextC] = visit[r][c] | 1<<next;
			check[next] = true;
			result = Math.max(result, recur(nextR, nextC));
			check[next] = false;
		}
		return result+1;
	}
	private static boolean invalid(int r, int c) {
		return r < 0 || r >= R || c < 0 || c >= C;
	}
}
