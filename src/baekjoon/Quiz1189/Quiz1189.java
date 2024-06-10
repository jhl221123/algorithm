package baekjoon.Quiz1189;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 1. dfs로 그래프 탐색
// 2. 격자 범위, T를 제외하고 우측 상단까지 탐색
// 3. 도착 시점 거리가 K인 경우만 count
public class Quiz1189 {
	static int R, C, K, count;
	static int[] dy = {-1, 1, 0, 0};
	static int[] dx = {0, 0, -1, 1};
	static char[][] map;
	static boolean[][] visited;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		map = new char[R][C];
		visited = new boolean[R][C];
		for(int i=0; i<R; i++) {
			String str = br.readLine();
			for(int j=0; j<C; j++) {
				map[i][j] = str.charAt(j);
			}
		}
		visited[R-1][0] = true;
		dfs(R-1, 0, 1);
		System.out.println(count);
	}
	private static void dfs(int r, int c, int dist) {
		if(dist == K) {
			if(r == 0 && c == C-1) count++;
			return;
		}
		for(int d=0; d<4; d++) {
			int mr = r + dy[d];
			int mc = c + dx[d];
			if(mr < 0 || mc < 0 || mr >= R || mc >= C || map[mr][mc] == 'T' || visited[mr][mc]) continue;
			visited[mr][mc] = true;
			dfs(mr, mc, dist + 1);
			visited[mr][mc] = false;
		}
	}
}
