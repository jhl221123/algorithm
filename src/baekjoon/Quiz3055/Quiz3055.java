package baekjoon.Quiz3055;

import java.util.*;
import java.io.*;

public class Quiz3055 {
	static int R, C;
	static int[] S, D;
	static char[][] map;
	static boolean[][] water;
	static boolean[][] stone;
	static int[][] visit;
	static boolean[][] wVisit;
	static final int WATER = -1;
	static final int DOCI = -2;
	static int[] dy = {-1, 1, 0, 0};
	static int[] dx = {0, 0, -1, 1};
	public static void main(String[] args) throws IOException {
		// 물먼저 이동후 도치 이동 -> 4방향중 물과 돌이 있는 곳은 이동 x
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		map = new char[R][C];
		water = new boolean[R][C];
		stone = new boolean[R][C];
		visit = new int[R][C];
		wVisit = new boolean[R][C];
		for(int i=0; i<R; i++) {
			String str = br.readLine();
			for(int j=0; j<C; j++) {
				map[i][j] = str.charAt(j);
				if(map[i][j] == '*') water[i][j] = true;
				if(map[i][j] == 'X') stone[i][j] = true;
				if(map[i][j] == 'S') S = new int[] {i, j};
				if(map[i][j] == 'D') D = new int[] {i, j};
			}
		}
		//bfs
		ArrayDeque<Point> q = new ArrayDeque<>();
		for(int i=0; i<R; i++) {
			for(int j=0; j<C; j++) {
				if(water[i][j]) q.addLast(new Point(i, j, WATER));
			}
		}
		q.addLast(new Point(S[0], S[1], DOCI));
		visit[S[0]][S[1]] = 1;
		while(!q.isEmpty()) {
			Point target = q.removeFirst();
			int r = target.y;
			int c = target.x;
			int t = target.type;
			for(int i=0; i<4; i++) {
				int mr = r + dy[i];
				int mc = c + dx[i];
				if(mr < 0 || mc < 0 || mr >= R || mc >= C) continue;
				if(t == WATER) {// 비버굴 또는 이미 물이거나 돌이면 패스
					if(wVisit[mr][mc] || stone[mr][mc] || (mr == D[0] && mc == D[1])) continue;
					wVisit[mr][mc] = true;
					q.addLast(new Point(mr, mc, WATER));
				} else if(t == DOCI) {// 이미 방문했거나 물, 돌이면 패스
					if(visit[mr][mc] > 0 || wVisit[mr][mc] || stone[mr][mc]) continue;
					visit[mr][mc] = visit[r][c] + 1;
					q.addLast(new Point(mr, mc, DOCI));
				}
			}
		}
		System.out.println(visit[D[0]][D[1]] > 0 ? visit[D[0]][D[1]]-1 : "KAKTUS");
	}

	static class Point {
		int y, x, type;
		public Point(int y, int x, int type) {
			this.y = y;
			this.x = x;
			this.type = type;
		}
	}
}
