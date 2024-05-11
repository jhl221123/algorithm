package baekjoon.Quiz1012;

import java.util.*;
import java.io.*;

public class Quiz1012 {
	static int N, M, K;
	static int[][] map;
	static boolean[][] visit;
	static int idx;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		while(T-- > 0) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			M = Integer.parseInt(st.nextToken());
			N = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			map = new int[N][M];
			visit = new boolean[N][M];
			idx = 1;
			for(int i=0; i<K; i++) {
				st = new StringTokenizer(br.readLine());
				int x = Integer.parseInt(st.nextToken());
				int y = Integer.parseInt(st.nextToken());
				map[y][x] = 1;
			}
			for(int i=0; i<N; i++) {
				for(int j=0; j<M; j++) {
					if(!visit[i][j] && map[i][j] == 1) {
						bfs(i, j, idx+1);
						idx++;
					}
				}
			}
			sb.append(idx-1).append("\n");
		}
		System.out.print(sb);
	}
	static int[] dy = {-1, 1, 0, 0};
	static int[] dx = {0, 0, -1, 1};
	static void bfs(int sr, int sc, int v) {
		ArrayDeque<int[]> ad = new ArrayDeque<>();
		ad.addLast(new int[]{sr, sc});
		while(!ad.isEmpty()) {
			int[] point = ad.removeFirst();
			int r = point[0];
			int c = point[1];
			if(visit[r][c]) continue;
			visit[r][c] = true;
			map[r][c] = v;
			for(int d=0; d<4; d++) {
				int mr = r + dy[d];
				int mc = c + dx[d];
				if(mr < 0 || mc < 0 || mr >= N || mc >= M || map[mr][mc] != 1 || visit[mr][mc]) continue;
				ad.addLast(new int[]{mr, mc});
			}
		}
	}
}
