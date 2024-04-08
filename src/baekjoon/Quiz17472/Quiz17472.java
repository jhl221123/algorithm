package baekjoon.Quiz17472;

import java.util.*;
import java.io.*;

public class Quiz17472 {
	static int N, M, islandNum, sum;
	static int[][] map;
	static boolean[][] visit;
	static PriorityQueue<Bridge> pq = new PriorityQueue<>((b1, b2) -> b1.dist - b2.dist);
	static int[] parent;
	static int[] dy = {1, 0, -1, 0};
	static int[] dx = {0, 1, 0, -1};
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		visit = new boolean[N][M];
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		// flood fill
		islandNum = 1;
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				if(!visit[i][j] && map[i][j] == 1) {
					fill(i, j);
					islandNum++;
				}
			}
		}
		// 다리 놓기
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				if(map[i][j] > 0) setBridge(i, j);
			}
		}
		// union-find
		parent = new int[islandNum];
		makeSet();
		int visit = 0;
		while(!pq.isEmpty()) {
			Bridge bridge = pq.poll();
			if(union(bridge.from, bridge.to)) {
				sum += bridge.dist;
				//                System.out.println("from, to, dist = " + bridge.from + ", " + bridge.to + ", " + bridge.dist);
				visit++;
			}
			if(visit == islandNum-2) {
				System.out.println(sum);
				return;
			}
		}
		System.out.println(-1);
	}
	private static void makeSet() {
		for(int i=1; i<islandNum; i++) {
			parent[i] = i;
		}
	}
	private static int find(int c) {
		if(parent[c] == c) return c;
		return parent[c] = find(parent[c]);
	}
	private static boolean union(int from, int to) {
		int fp = find(from);
		int tp = find(to);
		if(fp == tp) return false;
		if(from >= to) parent[tp] = fp;
		else parent[fp] = tp;
		//        parent[fp] = tp;
		return true;
	}
	private static void fill(int y, int x) {
		ArrayDeque<int[]> ad = new ArrayDeque<>();
		ad.addLast(new int[] {y, x});
		while(!ad.isEmpty()) {
			int[] point = ad.removeFirst();
			int r = point[0];
			int c = point[1];
			visit[r][c] = true;
			map[r][c] = islandNum;
			for(int d=0; d<4; d++) {
				int mr = r + dy[d];
				int mc = c + dx[d];
				if(!isIn(mr, mc) || map[mr][mc] != 1 || visit[mr][mc]) continue;
				ad.addLast(new int[] {mr, mc});
			}
		}
	}

	private static void setBridge(int y, int x) {
		for(int d=0; d<4; d++) {
			int mr = y + dy[d];
			int mc = x + dx[d];
			int from = map[y][x];
			int dist = 0;
			while(isIn(mr, mc) && map[mr][mc] == 0) {
				dist++;
				mr += dy[d];
				mc += dx[d];
			}
			if(isIn(mr, mc) && dist>=2 && map[mr][mc] != from) {
				pq.offer(new Bridge(from, map[mr][mc], dist));
				//                System.out.println("from, to, dist = " + from + ", " + map[mr][mc] + ", " + dist);
			}
		}
	}

	private static boolean isIn(int y, int x) {
		return (y >= 0 && x >= 0 && y < N && x < M);
	}

	static class Bridge {
		int from, to, dist;
		public Bridge(int from, int to, int dist) {
			this.from = from;
			this.to = to;
			this.dist = dist;
		}
	}
}
