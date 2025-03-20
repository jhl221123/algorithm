package baekjoon.Quiz2665;

import java.io.*;
import java.util.*;

/*
Gold4: 미로만들기 / [0-1 bfs, graph]
1. 이동 가능한 위치는 덱 앞쪽, 벽이 있는 곳은 가중치를 추가한 후 덱 뒤쪽에 추가한다.
2. 도착 지점에 도달하면 가중치의 최소값을 갱신한다.
*/
public class Quiz2665 {

	private static int[] dy = {-1, 1, 0, 0};
	private static int[] dx = {0, 0, -1, 1};

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		boolean[][] visited = new boolean[N][N];
		int[][] map = new int[N][N];
		for(int i=0; i<N; i++) {
			String line = br.readLine();

			for(int j=0; j<N; j++) {
				map[i][j] = line.charAt(j) - '0';
			}
		}

		ArrayDeque<int[]> ad = new ArrayDeque<>();
		ad.addLast(new int[] {0, 0, 0});
		int min = 3000;
		while(!ad.isEmpty()) {
			int[] cur = ad.removeFirst();
			int r = cur[0];
			int c = cur[1];
			int b = cur[2];

			if(r == N-1 && c == N-1) {
				min = Math.min(min, b);
				continue;
			}

			if(visited[r][c]) {
				continue;
			}
			visited[r][c] = true;

			for(int d=0; d<4; d++) {
				int mr = r + dy[d];
				int mc = c + dx[d];

				if(mr<0 || mc<0 || mr>=N || mc>=N || visited[mr][mc]) {
					continue;
				}

				if(map[mr][mc] == 0) {
					ad.addLast(new int[] {mr, mc, b + 1});
				} else {
					ad.addFirst(new int[] {mr, mc, b});
				}
			}
		}

		System.out.println(min == 3000 ? 0 : min);
	}
}
