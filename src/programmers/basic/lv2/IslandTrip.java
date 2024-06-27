package programmers.basic.lv2;

import java.util.*;

public class IslandTrip {
	static int N, M;
	static char[][] map;
	static boolean[][] visited;
	static List<Integer> food;
	static int[] dy = {-1, 1, 0, 0};
	static int[] dx = {0, 0, -1, 1};

	public static void main(String[] args) {
		String[] maps = {"X591X","X1X5X","X231X", "1XXX1"};
		int[] result = solution(maps); // [1, 1, 27]
		System.out.println(Arrays.toString(result));
	}

	public static int[] solution(String[] maps) {
		input(maps);
		return floodFill();
	}

	private static void input(String[] maps) {
		N = maps.length;
		M = maps[0].length();
		map = new char[N][M];
		for (int r = 0; r < N; r++) {
			String row = maps[r];
			for (int c = 0; c < M; c++) {
				map[r][c] = row.charAt(c);
			}
		}
		visited = new boolean[N][M];
		food = new ArrayList<>();
	}

	private static int[] floodFill() {
		for (int r = 0; r < N; r++) {
			for (int c = 0; c < M; c++) {
				if (map[r][c] == 'X' || visited[r][c])
					continue;
				food.add(bfs(r, c));
			}
		}
		return listToArr();
	}

	private static int bfs(int y, int x) {
		ArrayDeque<int[]> ad = new ArrayDeque<>();
		ad.addLast(new int[] {y, x});
		int value = 0;
		while (!ad.isEmpty()) {
			int[] point = ad.removeFirst();
			int r = point[0];
			int c = point[1];
			if (visited[r][c])
				continue;
			visited[r][c] = true;
			value += (map[r][c] - '0');
			for (int d = 0; d < 4; d++) {
				int mr = r + dy[d];
				int mc = c + dx[d];
				if (!isIn(mr, mc) || map[mr][mc] == 'X' || visited[mr][mc])
					continue;
				ad.addLast(new int[] {mr, mc});
			}
		}
		return value;
	}

	private static boolean isIn(int r, int c) {
		return r >= 0 && c >= 0 && r < N && c < M;
	}

	private static int[] listToArr() {
		if (food.isEmpty()) food.add(-1);
		return Arrays.stream(
				food.stream()
					.mapToInt(Integer::intValue)
					.toArray()
			)
			.sorted()
			.toArray();
	}
}
