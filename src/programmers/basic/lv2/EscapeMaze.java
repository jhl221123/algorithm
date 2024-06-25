package programmers.basic.lv2;

import java.util.*;

public class EscapeMaze {
	static int N, M;
	static char[][] map;
	static int[][][] visited;
	static ArrayDeque<int[]> ad = new ArrayDeque<>();
	static int[] dy = {-1, 1, 0, 0};
	static int[] dx = {0, 0, -1, 1};

	public static void main(String[] args) {
		String[] maps = {"SOOOL","XXXXO","OOOOO","OXXXX","OOOOE"};
		int result = solution(maps); // 16
		System.out.println(result);
	}

	public static int solution(String[] maps) {
		init(maps);
		return bfs();
	}

	private static void init(String[] maps) {
		N = maps.length;
		M = maps[0].length();
		map = new char[N][M];
		visited = new int[2][N][M];
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				map[i][j] = maps[i].charAt(j);
				if(map[i][j] == 'S') {
					ad.addLast(new int[] {0, i, j});
					visited[0][i][j] = 1;
				}
			}
		}
	}

	private static int bfs() {
		while(!ad.isEmpty()) {
			int[] point = ad.removeFirst();
			int key = point[0];
			int r = point[1];
			int c = point[2];
			if(map[r][c] == 'L') {
				key = 1;
				visited[1][r][c] = visited[0][r][c];
			}
			for(int d=0; d<4; d++) {
				int mr = r + dy[d];
				int mc = c + dx[d];
				if(!isIn(mr, mc) || map[mr][mc] == 'X' || visited[key][mr][mc] != 0) continue;
				visited[key][mr][mc] = visited[key][r][c] + 1;
				if(key == 1 && map[mr][mc] == 'E') return visited[1][mr][mc]-1;
				else ad.addLast(new int[] {key, mr, mc});
			}
		}
		return -1;
	}

	private static boolean isIn(int r, int c) {
		return r >= 0 && c >= 0 && r < N && c < M;
	}
}
