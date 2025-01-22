package baekjoon.Quiz1520;

import java.io.*;
import java.util.*;

/*
1. 시작 좌표부터 사방향 탐색해서 이동 가능한 곳을 우선 순위 큐에 넣는다.
2. 처음 방문한 곳이라면 현재까지 경로 개수를 더하고 다음 지점을 우선 순위 큐에 넣는다.
3. 이미 방문한 곳이라면 현재까지 경로 개수만 더해준다.
4. 모든 경로를 탐색한 후 도착 지점의 개수를 출력한다.
*/

public class Quiz1520 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int M = Integer.parseInt(st.nextToken());
		int N = Integer.parseInt(st.nextToken());
		int[][] hikingTrailHeight = initializeHikingTrail(M, N, br);

		int[][] routeCounts = calculateAllRouts(hikingTrailHeight);
		System.out.println(routeCounts[M-1][N-1]);
	}

	private static int[][] calculateAllRouts(int[][] hikingTrailHeight) {
		int m = hikingTrailHeight.length;
		int n = hikingTrailHeight[0].length;
		int[] dy = {-1, 1, 0, 0};
		int[] dx = {0, 0, -1, 1};

		int[][] routeCounts = new int[m][n];
		routeCounts[0][0]++;

		PriorityQueue<Spot> pq = new PriorityQueue<>(((o1, o2) -> o2.height - o1.height));
		pq.offer(new Spot(0, 0, hikingTrailHeight[0][0]));
		while(!pq.isEmpty()) {
			Spot spot = pq.poll();
			int r = spot.row;
			int c = spot.col;

			for(int d=0; d<4; d++) {
				int mr = r + dy[d];
				int mc = c + dx[d];

				if(mr < 0 || mc < 0 || mr >= m || mc >= n) continue;
				if(hikingTrailHeight[mr][mc] < hikingTrailHeight[r][c]) {
					if(routeCounts[mr][mc] > 0) {
						routeCounts[mr][mc] += routeCounts[r][c];
					} else {
						routeCounts[mr][mc] += routeCounts[r][c];
						pq.offer(new Spot(mr, mc, hikingTrailHeight[mr][mc]));
					}
				}
			}
		}

		return routeCounts;
	}

	private static int[][] initializeHikingTrail(int M, int N, BufferedReader br) throws IOException {
		int[][] hikingTrailHeight = new int[M][N];
		StringTokenizer st;

		for(int i = 0; i< M; i++) {
			st = new StringTokenizer(br.readLine());

			for(int j = 0; j< N; j++) {
				hikingTrailHeight[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		return hikingTrailHeight;
	}

	static class Spot {
		int row, col, height;

		public Spot(int row, int col, int height) {
			this.row = row;
			this.col = col;
			this.height = height;
		}
	}
}
