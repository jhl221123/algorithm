package baekjoon.Quiz4963;

import java.io.*;
import java.util.*;

/*
Silver2: 섬의 개수 / [bfs]
*/
public class Quiz4963 {

	private static int[] dy = {-1, 1, 0, 0, -1, -1, 1, 1};
	private static int[] dx = {0, 0, -1, 1, -1, 1, -1, 1};

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringBuilder sb = new StringBuilder();
		while(true) {
			String[] wh = br.readLine().split(" ");
			int w = Integer.parseInt(wh[0]);
			int h = Integer.parseInt(wh[1]);
			if(w == 0 && h == 0) {
				break;
			}

			int[][] map = new int[h][w];
			for(int i=0; i<h; i++) {
				String[] row = br.readLine().split(" ");
				for(int j=0; j<w; j++) {
					map[i][j] = Integer.parseInt(row[j]);
				}
			}

			boolean[][] visited = new boolean[h][w];
			int count = 0;
			for(int i=0; i<h; i++) {
				for(int j=0; j<w; j++) {
					if(map[i][j] > 0 && !visited[i][j]) {
						count++;
						ArrayDeque<int[]> ad = new ArrayDeque<>();
						ad.addLast(new int[] {i, j});

						while(!ad.isEmpty()) {
							int[] node = ad.removeFirst();
							int r = node[0];
							int c = node[1];
							if(visited[r][c]) continue;
							visited[r][c] = true;

							for(int d=0; d<8; d++) {
								int mr = r + dy[d];
								int mc = c + dx[d];
								if(mr<0 || mr>=h || mc<0 || mc>=w) continue;
								if(map[mr][mc] == 0 || visited[mr][mc]) continue;
								ad.addLast(new int[] {mr, mc});
							}
						}
					}
				}
			}

			sb.append(count).append("\n");
		}

		System.out.print(sb);
	}
}
