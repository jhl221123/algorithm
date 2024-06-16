package swea.d4;

import java.util.*;
import java.io.*;

public class Quiz1249 {
	static int N;
	static int[][] map;
	static int[][] dist;
	static PriorityQueue<Edge> q = new PriorityQueue<>((e1, e2) -> e1.c - e2.c);
	static int[] dy = {-1, 1, 0, 0};
	static int[] dx = {0, 0, -1, 1};
	static int ans;
	public static void main(String args[]) throws Exception
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();

		for(int test_case = 1; test_case <= T; test_case++)
		{
			N = Integer.parseInt(br.readLine());
			map = new int[N][N];
			dist = new int[N][N];
			ans = 0;
			for(int i=0; i<N; i++) {
				String str = br.readLine();
				for(int j=0; j<N; j++) {
					map[i][j] = str.charAt(j) - '0';
				}
			}
			for(int i=0; i<N; i++) {
				for(int j=0; j<N; j++) {
					dist[i][j] = Integer.MAX_VALUE;
				}
			}
			dist[0][0] = 0;
			// djac
			q.offer(new Edge(0, 0, 0));
			while(!q.isEmpty()) {
				Edge edge = q.poll();
				int r = edge.y;
				int c = edge.x;
				for(int i=0; i<4; i++) {
					int mr = r + dy[i];
					int mc = c + dx[i];
					if(mr < 0 || mc < 0 || mr >=N || mc >= N) continue;
					int newDist = dist[r][c] + map[mr][mc];
					int originDist = dist[mr][mc];
					if(newDist < originDist) {
						dist[mr][mc] = newDist;
						q.offer(new Edge(mr, mc, dist[mr][mc]));
					}
				}
			}
			sb.append("#").append(test_case).append(" ").append(dist[N-1][N-1]).append("\n");
		}
		System.out.print(sb);
	}
	static class Edge {
		int y, x, c;
		public Edge(int y, int x, int c) {
			this.y = y;
			this.x = x;
			this.c = c;
		}
	}
}
