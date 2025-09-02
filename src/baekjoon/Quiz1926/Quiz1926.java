package baekjoon.Quiz1926;

import java.io.*;
import java.util.*;

/*
Silver1: 그림 / [bfs]
*/
public class Quiz1926 {

	private static final int[] dy = {-1, 1, 0, 0};
	private static final int[] dx = {0, 0, -1, 1};

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] nm = br.readLine().split(" ");
		int n = Integer.parseInt(nm[0]);
		int m = Integer.parseInt(nm[1]);
		int[][] map = new int[n][m];
		for(int i=0; i<n; i++) {
			String[] row = br.readLine().split(" ");
			for(int j=0; j<m; j++) {
				map[i][j] = row[j].charAt(0) - '0';
			}
		}

		boolean[][] visited = new boolean[n][m];
		int count = 0;
		int max = 0;
		for(int i=0; i<n; i++) {
			for(int j=0; j<m; j++) {
				if(map[i][j] == 1 && !visited[i][j]) {
					ArrayDeque<int[]> ad = new ArrayDeque<>();
					ad.addLast(new int[] {i, j});

					int size = 0;
					while(!ad.isEmpty()) {
						int[] node = ad.removeFirst();
						int r = node[0];
						int c = node[1];
						if(visited[r][c]) continue;
						visited[r][c] = true;
						size++;
						for(int d=0; d<4; d++) {
							int mr = r + dy[d];
							int mc = c + dx[d];
							if(mr<0 || mr>=n || mc<0 || mc>=m) continue;
							if(map[mr][mc] == 0 || visited[mr][mc]) continue;
							ad.addLast(new int[] {mr, mc});
						}
					}
					count++;
					max = Math.max(max, size);
				}
			}
		}
		System.out.println(count);
		System.out.println(max);
	}
}
