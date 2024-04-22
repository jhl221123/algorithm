package baekjoon.Quiz1261;

import java.util.*;
import java.io.*;

public class Quiz1261 {
	static int N, M;
	static int[][] map;
	static int[][] dist;
	static PriorityQueue<Node> pq = new PriorityQueue<>(Comparator.comparingInt(o -> o.v));
	static int[] dy = {-1, 1, 0, 0};
	static int[] dx = {0, 0, -1, 1};
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		dist = new int[N][M];
		for(int i=0; i<N; i++) {
			String str = br.readLine();
			for(int j=0; j<M; j++) {
				map[i][j] = str.charAt(j) - '0';
			}
		}
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				dist[i][j] = 30000;
			}
		}

		int idx = 1;
		pq.offer(new Node(0, 0, 0));
		while(!pq.isEmpty()) {
			Node node = pq.poll();
			int r = node.r;
			int c = node.c;
			int v = node.v;
			for(int d=0; d<4; d++) {
				int mr = r + dy[d];
				int mc = c + dx[d];
				if(mr < 0 || mc < 0 || mr >= N || mc >= M) continue;
				if(map[mr][mc] == 0) {
					if(dist[mr][mc] > v) {
						dist[mr][mc] = v;
						pq.offer(new Node(mr, mc, v));
					}
				}
				else {
					if(dist[mr][mc] > v + 1) {
						dist[mr][mc] = v + 1;
						pq.offer(new Node(mr, mc, v + 1));
					}
				}
			}
			//if(mr == N-1 && mc == M-1) break;
		}
		System.out.println(dist[N-1][M-1] == 30000 ? 0 : dist[N-1][M-1]);
	}
	static class Node {
		int r, c, v;
		public Node(int r, int c, int v) {
			this.r = r;
			this.c = c;
			this.v = v;
		}
	}
}
