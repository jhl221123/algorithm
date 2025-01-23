package baekjoon.Quiz2589;

import java.util.*;
import java.io.*;

/*
1. 각 칸을 순회하며 육지를 탐색한다.
2. 모든 육지에서 가장 먼 육지까지 거리를 구한다.
3. 거리의 최대값을 출력한다.
*/

public class Quiz2589 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int R = Integer.parseInt(st.nextToken());
		int C = Integer.parseInt(st.nextToken());
		char[][] map = new char[R][C];
		for(int i=0; i<R; i++) {
			String line = br.readLine();

			for(int j=0; j<C; j++) {
				map[i][j] = line.charAt(j);
			}
		}

		int max = 0;
		for(int i=0; i<R; i++) {
			for(int j=0; j<C; j++) {
				if(map[i][j] == 'L') {
					max = Math.max(max, calculateFarLand(i, j, map));
				}
			}
		}

		System.out.println(max);
	}

	private static int calculateFarLand(int row, int col, char[][] map) {
		int R = map.length;
		int C = map[0].length;
		int[] dy = {-1, 1, 0, 0};
		int[] dx = {0, 0, -1, 1};
		boolean[][] visited = new boolean[R][C];
		ArrayDeque<int[]> ad = new ArrayDeque<>();
		ad.addLast(new int[]{row, col, 0});

		int maxDist = 0;
		while(!ad.isEmpty()) {
			int[] spot = ad.removeFirst();
			int r = spot[0];
			int c = spot[1];
			int dist = spot[2];
			if(visited[r][c]) continue;
			visited[r][c] = true;
			maxDist = Math.max(maxDist, dist);

			for(int d=0; d<4; d++) {
				int mr = r + dy[d];
				int mc = c + dx[d];

				if(mr < 0 || mc < 0 || mr >= R || mc >= C) continue;
				if(visited[mr][mc]) continue;
				if(map[mr][mc] == 'W') continue;
				ad.addLast(new int[]{mr, mc, dist + 1});
			}
		}

		return maxDist;
	}
}
