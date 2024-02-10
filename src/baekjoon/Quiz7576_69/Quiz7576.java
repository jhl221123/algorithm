package baekjoon.Quiz7576_69;

import java.util.*;
import java.io.*;

public class Quiz7576 {
	private static int[] dy = {-1, 1, 0, 0};
	private static int[] dx = {0, 0, -1, 1};
	private static int[][] visit;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int M = Integer.parseInt(st.nextToken());
		int N = Integer.parseInt(st.nextToken());
		int[][] box = new int[N][M];
		visit = new int[N][M];
		ArrayDeque<int[]> ad = new ArrayDeque<>();
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<M; j++) {
				box[i][j] = Integer.parseInt(st.nextToken());
				if(box[i][j] == 1) {
					ad.addLast(new int[] {i, j});
					visit[i][j] = 1;
				}
			}
		}
		while(!ad.isEmpty()) {
			int[] point = ad.removeFirst();
			int r = point[0];
			int c = point[1];
			for(int i=0; i<4; i++) {
				int dr = r + dy[i];
				int dc = c + dx[i];
				if(dr < 0 || dr >= N || dc < 0 || dc >= M) continue;
				if(box[dr][dc] == 0 && visit[dr][dc] == 0) {
					visit[dr][dc] = visit[r][c] + 1;
					ad.addLast(new int[] {dr, dc});
				}
			}
		}
		boolean allGrowth = true;
		int max = 0;
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				if(visit[i][j] == 0 && box[i][j] == 0) {
					allGrowth = false;
				}
				max = Math.max(max, visit[i][j]);
			}
		}
		if(allGrowth) System.out.println(max-1);
		else System.out.println(-1);
	}
}
