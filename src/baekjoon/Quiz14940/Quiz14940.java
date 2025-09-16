package baekjoon.Quiz14940;

import java.io.*;
import java.util.*;

/*
Silver1: 쉬운 최단거리 / [bfs]
*/
public class Quiz14940 {

	private static final int[] dy = {-1, 1, 0, 0};
	private static final int[] dx = {0, 0, -1, 1};

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] nm = br.readLine().split(" ");
		int n = Integer.parseInt(nm[0]);
		int m = Integer.parseInt(nm[1]);
		int[][] map = new int[n][m];
		int[][] dist = new int[n][m];
		boolean[][] visited = new boolean[n][m];
		for(int i=0; i<n; i++) {
			Arrays.fill(dist[i], -1);
		}
		int[] start = new int[2];
		for(int i=0; i<n; i++) {
			String[] row = br.readLine().split(" ");
			for(int j=0; j<m; j++) {
				map[i][j] = Integer.parseInt(row[j]);
				if(map[i][j] == 2) {
					start[0] = i;
					start[1] = j;
				}
				if(map[i][j] == 0) {
					dist[i][j] = 0;
				}
			}
		}

		ArrayDeque<int[]> ad = new ArrayDeque<>();
		ad.addLast(new int[] {start[0], start[1], 0});

		while(!ad.isEmpty()) {
			int[] node = ad.removeFirst();
			int r = node[0];
			int c = node[1];
			int count = node[2];
			if(visited[r][c]) {
				continue;
			}
			visited[r][c] = true;
			if(map[r][c] == 0) {
				dist[r][c] = 0;
				continue;
			}
			dist[r][c] = count;

			for(int d=0; d<4; d++) {
				int mr = dy[d] + r;
				int mc = dx[d] + c;
				if(mr<0 || mc<0 || mr>=n || mc>=m) continue;
				if(visited[mr][mc]) continue;
				ad.addLast(new int[] {mr, mc, count + 1});
			}
		}

		StringBuilder sb = new StringBuilder();
		for(int i=0; i<n; i++) {
			for(int j=0; j<m; j++) {
				sb.append(dist[i][j]).append(" ");
			}
			sb.append("\n");
		}

		System.out.print(sb);
	}
}
