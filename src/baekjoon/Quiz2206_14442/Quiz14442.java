package baekjoon.Quiz2206_14442;

import java.util.*;
import java.io.*;

public class Quiz14442 {
	// 방문배열과 벽 부술 수 있는 횟수 같이 활용
	static int N;
	static int M;
	static int K;
	static int[][] arr;
	static int[][][] visit;
	static int[] dy = {-1, 1, 0, 0};
	static int[] dx = {0, 0, -1, 1};
	static int min = Integer.MAX_VALUE;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		arr = new int[N][M];
		visit = new int[N][M][K+1];
		for(int i=0; i<N; i++) {
			String str = br.readLine();
			for(int j=0; j<M; j++) {
				arr[i][j] = str.charAt(j) - '0';
			}
		}
		bfs();
		int min = Integer.MAX_VALUE;
		for(int i=0; i<=K; i++) {
			int target = visit[N - 1][M - 1][i];
			if(target != 0) min = Math.min(target, min);
		}
		if(min==Integer.MAX_VALUE) min = -1;
		System.out.println(min);
	}
	private static void bfs() {
		ArrayDeque<Status> ad = new ArrayDeque<>();
		ad.addLast(new Status(new int[] {0, 0}, 0));
		visit[0][0][0] = 1;
		while(!ad.isEmpty()) {
			Status cs = ad.removeFirst();
			int[] point = cs.point;
			int breakCount = cs.breakCount;
			int r = point[0];
			int c = point[1];
			for(int i=0; i<4; i++) {
				int newBreakCount = breakCount;
				int mr = r + dy[i];
				int mc = c + dx[i];
				if(mr < 0 || mr >= N || mc < 0 || mc >= M) continue;
				if(arr[mr][mc] == 1 && breakCount==K) continue;
				if(arr[mr][mc] == 1 && breakCount<K) newBreakCount++;
				if(visit[mr][mc][newBreakCount] == 0) {
					visit[mr][mc][newBreakCount] = visit[r][c][breakCount] + 1;
					ad.addLast(new Status(new int[] {mr, mc}, newBreakCount));
				}
			}
		}
	}
	static class Status {
		int[] point;
		int breakCount;
		public Status(int[] point, int breakCount) {
			this.point = point;
			this.breakCount = breakCount;
		}
	}
}
