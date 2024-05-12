package baekjoon.Quiz2667;

import java.util.*;
import java.io.*;

public class Quiz2667 {
	static int N;
	static int[][] map;
	static boolean[][] visit;
	static int idx = 1;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		map = new int[N][N];
		visit = new boolean[N][N];
		List<Integer> list = new ArrayList<>();
		for(int i=0; i<N; i++) {
			String str = br.readLine();
			for(int j=0; j<N; j++) {
				map[i][j] = str.charAt(j) - '0';
			}
		}
		int unionCnt = 0;
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				if(!visit[i][j] && map[i][j] == 1) {
					int cnt = bfs(i, j, idx+1);
					list.add(cnt);
					idx++;
					unionCnt++;
				}
			}
		}
		Collections.sort(list);
		StringBuilder sb = new StringBuilder();
		sb.append(unionCnt).append("\n");
		for(int num : list) {
			sb.append(num).append("\n");
		}
		System.out.print(sb);
	}
	static int[] dy = {-1, 1, 0, 0};
	static int[] dx = {0, 0, -1, 1};
	static int bfs(int sr, int sc, int v) {
		int cnt = 0;
		ArrayDeque<int[]> ad = new ArrayDeque<>();
		ad.addLast(new int[] {sr, sc});
		while(!ad.isEmpty()) {
			int[] point = ad.removeFirst();
			int r = point[0];
			int c = point[1];
			if(visit[r][c]) continue;
			visit[r][c] = true;
			map[r][c] = v;
			cnt++;
			for(int d=0; d<4; d++) {
				int mr = r + dy[d];
				int mc = c + dx[d];
				if(mr < 0 || mc < 0 || mr >= N || mc >= N || map[mr][mc] != 1 || visit[mr][mc]) continue;
				ad.addLast(new int[]{mr, mc});
			}
		}
		return cnt;
	}
}
