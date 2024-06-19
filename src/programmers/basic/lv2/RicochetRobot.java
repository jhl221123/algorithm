package programmers.basic.lv2;

import java.util.*;

public class RicochetRobot {
	static int R, C;
	static char[][] map;
	static boolean[][] visited;
	static int[] start;
	static int[] dy = {-1, 1, 0, 0};
	static int[] dx = {0, 0, -1, 1};
	public static void main(String[] args) {
		String[] arr = {"...D..R", ".D.G...", "....D.D", "D....D.", "..D...."}; // 7
		// String[] arr = {".D.R", "....", ".G..", "...D"}; // -1
		int result = solution(arr);
		System.out.println(result);
	}
	public static int solution(String[] board) {
		input(board);
		int answer = bfs();
		return answer;
	}
	private static void input(String[] board) {
		R = board.length;
		C = board[0].length();
		map = new char[R][C];
		for(int i=0; i<R; i++) {
			String row = board[i];
			for(int j=0; j<C; j++) {
				map[i][j] = row.charAt(j);
				if(map[i][j] == 'R') start = new int[] {i, j};
			}
		}
		visited = new boolean[R][C];
	}
	private static int bfs() {
		ArrayDeque<Point> ad = new ArrayDeque<>();
		ad.addLast(new Point(start[0], start[1], 0));
		while(!ad.isEmpty()) {
			Point point = ad.removeFirst();
			int r = point.y;
			int c = point.x;
			if(map[r][c] == 'G') return point.depth;
			if(visited[r][c]) continue;
			visited[r][c] = true;
			for(int d=0; d<4; d++) {
				int mr = r + dy[d];
				int mc = c + dx[d];
				if(!isIn(mr, mc) || map[mr][mc] == 'D' || visited[mr][mc]) continue;
				Point movedPoint = moveToEnd(mr, mc, d, point.depth);
				ad.addLast(movedPoint);
			}
		}
		return -1;
	}
	private static Point moveToEnd(int r, int c, int d, int depth) {
		int mr = r + dy[d];
		int mc = c + dx[d];
		while(isIn(mr, mc) && map[mr][mc] != 'D') {
			r = mr;
			c = mc;
			mr = r + dy[d];
			mc = c + dx[d];
		}
		return new Point(r, c, depth + 1);
	}
	private static boolean isIn(int r, int c) {
		return r >= 0 && c >= 0 && r < R && c < C;
	}
	static class Point {
		int y, x, depth;

		public Point(int y, int x, int depth) {
			this.y = y;
			this.x = x;
			this.depth = depth;
		}
	}
}
