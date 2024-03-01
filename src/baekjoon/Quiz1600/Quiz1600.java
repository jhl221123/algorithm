package baekjoon.Quiz1600;

import java.util.*;
import java.io.*;

public class Quiz1600 {
	static int K, W, H;
	static int[][] map;
	static boolean[][][] visit;
	static ArrayDeque<Node> ad = new ArrayDeque<>();
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		K = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());
		W = Integer.parseInt(st.nextToken());
		H = Integer.parseInt(st.nextToken());
		map = new int[H][W];
		for(int i=0; i<H; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<W; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		visit = new boolean[H][W][K+1];
		bfs();
	}
	static int[] dy = {-1, 1, 0, 0};
	static int[] dx = {0, 0, -1, 1};
	static int[] hdy = {-1, -2, -2, -1, 1, 2, 2, 1};
	static int[] hdx = {-2, -1, 1, 2, 2, 1, -1, -2};
	static void bfs() {
		ad.addLast(new Node(0, 0, K, 0));
		visit[0][0][K] = true;
		while(!ad.isEmpty()) {
			Node node = ad.removeFirst();
			int r = node.r;
			int c = node.c;
			if(r==H-1 && c==W-1) {
				System.out.println(node.d);
				return;
			}
			for(int i=0; i<4; i++) {
				int mr = r + dy[i];
				int mc = c + dx[i];
				if(mr<0 || mc<0 || mr>=H || mc>=W || map[mr][mc] == 1 || visit[mr][mc][node.k]) continue;
				visit[mr][mc][node.k] = true;
				ad.addLast(new Node(mr, mc, node.k, node.d+1));
			}
			if(node.k == 0) continue;
			for(int i=0; i<8; i++) {
				int mr = r + hdy[i];
				int mc = c + hdx[i];
				if(mr<0 || mc<0 || mr>=H || mc>=W || map[mr][mc] == 1 || visit[mr][mc][node.k-1]) continue;
				visit[mr][mc][node.k-1] = true;
				ad.addLast(new Node(mr, mc, node.k-1, node.d+1));
			}
		}
		System.out.println(-1);
	}
	static class Node {
		int r, c, k, d;
		public Node(int r, int c, int k, int d) {
			this.r = r;
			this.c = c;
			this.k = k;
			this.d = d;
		}
	}
}
