package baekjoon.Quiz3109;

import java.util.*;
import java.io.*;
public class Quiz3109 {
	static int N;
	static int M;
	static char[][] map;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new char[N][M];
		for(int i=0; i<N; i++) {
			String str = br.readLine();
			for(int j=0; j<M; j++) {
				map[i][j] = str.charAt(j);
			}
		}
		int ans = 0;
		for(int i=0; i<N; i++) {
			if(dfs(i, 0)) ans++;
		}
		System.out.println(ans);
	}
	static int[] dy = {-1, 0, 1};
	static int[] dx = {1, 1, 1};
	private static boolean dfs(int r, int c) {
		if(c==M-2) return true;
		for(int i=0; i<3; i++) {
			int mr = r + dy[i];
			int mc = c + dx[i];
			if(mr < 0 || mr >= N || map[mr][mc] == 'x') continue;
			map[mr][mc] = 'x';
			if(dfs(mr, mc)) return true;
		}
		return false;
	}
}
