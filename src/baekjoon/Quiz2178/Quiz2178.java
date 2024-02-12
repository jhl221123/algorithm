package baekjoon.Quiz2178;

import java.util.*;
import java.io.*;
public class Quiz2178 {
	static int[] dy = {-1, 1, 0, 0};
	static int[] dx = {0, 0, -1, 1};
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		char[][] map = new char[N][M];
		int[][] visit = new int[N][M];
		for(int i=0; i<N; i++) {
			String str = br.readLine();
			for(int j=0; j<M; j++) {
				map[i][j] = str.charAt(j);
			}
		}
		visit[0][0] = 1;
		ArrayDeque<int[]> ad = new ArrayDeque<>();
		ad.addLast(new int[] {0, 0});
		while(!ad.isEmpty()) {
			int[] point = ad.removeFirst();
			int r = point[0];
			int c = point[1];
			for(int i=0; i<4; i++) {
				int mr = r + dy[i];
				int mc = c + dx[i];
				if(mr < 0 || mr >= N || mc < 0 || mc >= M) continue;
				if(map[r][c] == '1' && visit[mr][mc] == 0) {
					visit[mr][mc] = visit[r][c] + 1;
					ad.addLast(new int[] {mr, mc});
					if(mr == N-1 && mc == M-1) {
						System.out.println(visit[mr][mc]);
						return;
					}
				}
			}
		}
	}
}
