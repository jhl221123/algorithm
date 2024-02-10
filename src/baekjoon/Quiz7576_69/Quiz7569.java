package baekjoon.Quiz7576_69;

import java.util.*;
import java.io.*;

public class Quiz7569 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int M = Integer.parseInt(st.nextToken());
		int N = Integer.parseInt(st.nextToken());
		int H = Integer.parseInt(st.nextToken());
		int[][][] box = new int[H][N][M];
		int[][][] visit = new int[H][N][M];
		int[] dx = {0, 0, -1, 1, 0, 0};
		int[] dy = {-1, 1, 0, 0, 0, 0};
		int[] dz = {0, 0, 0, 0, 1, -1};
		ArrayDeque<int[]> ad = new ArrayDeque<>();
		for(int i=0; i<H; i++) {
			for(int j=0; j<N; j++) {
				st = new StringTokenizer(br.readLine());
				for(int k=0; k<M; k++) {
					box[i][j][k] = Integer.parseInt(st.nextToken());
					if(box[i][j][k] == 1) {
						ad.addLast(new int[] {i, j, k});
						visit[i][j][k] = 1;
					}
				}
			}
		}
		while(!ad.isEmpty()) {
			int[] point = ad.removeFirst();
			int h = point[0];
			int r = point[1];
			int c = point[2];
			for(int i=0; i<6; i++) {
				int dh = h + dz[i];
				int dr = r + dy[i];
				int dc = c + dx[i];
				if(dh < 0 || dh >= H || dr < 0 || dr >= N || dc < 0 || dc >= M) continue;
				if(box[dh][dr][dc] == 0 && visit[dh][dr][dc] == 0) {
					visit[dh][dr][dc] = visit[h][r][c] + 1;
					ad.addLast(new int[] {dh, dr, dc});
				}
			}
		}
		boolean allGrowth = true;
		int max = 0;
		for(int i=0; i<H; i++) {
			for(int j=0; j<N; j++) {
				for(int k=0; k<M; k++) {
					if(visit[i][j][k] == 0 && box[i][j][k] == 0) {
						allGrowth = false;
					}
					max = Math.max(max, visit[i][j][k]);
				}
			}
		}
		if(allGrowth) System.out.println(max - 1);
		else System.out.println(-1);
	}
}
