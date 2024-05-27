package baekjoon.Quiz2468;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

// 10000 * 100
// 물 : 0 ~ 100
// 1. 입력
// 2. 물 크기만큼 반복
// 3. 현재 물 크기 보다 작은 지역 체크
// 4. 잠기지 않은 첫번째 위치 탐색 -> && 방문하지 않은 곳
// 5. 해당 지점을 기준으로 bfs -> 방문한 곳은 체크
// 6. 지역을 채울때마다 count 증가
// 7. count와 max를 비교
// 8. max를 출력
// 시간 복잡도: O(N * N * waterHeight) -> 100 * 100 * 100
public class Quiz2468 {
	static int N, count, max;
	static int[][] map;
	static boolean[][] isOver;
	static boolean[][] visit;
	private static int[] dy = {-1, 1, 0, 0};
	private static int[] dx = {0, 0, -1, 1};
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		map = new int[N][N];
		for(int i=0; i<N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int j=0; j<N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		for(int waterHeight=0; waterHeight<=100; waterHeight++) {
			isOver = new boolean[N][N];
			visit = new boolean[N][N];
			count = 0;
			checkHeight(waterHeight);
			floodFill();
			max = Math.max(max, count);
		}
		System.out.println(max);
	}
	private static void checkHeight(int waterHeight) {
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				if(map[i][j]<=waterHeight) isOver[i][j] = true;
			}
		}
	}
	private static void floodFill() {
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				if(!isOver[i][j] && !visit[i][j]) {
					bfs(i, j);
					count++;
				}
			}
		}
	}
	private static void bfs(int y, int x) {
		ArrayDeque<int[]> ad = new ArrayDeque<>();
		ad.addLast(new int[]{y, x});
		while(!ad.isEmpty()) {
			int[] point = ad.removeFirst();
			int r = point[0];
			int c = point[1];
			if(visit[r][c]) continue;
			visit[r][c] = true;
			for(int d=0; d<4; d++) {
				int mr = r + dy[d];
				int mc = c + dx[d];
				if(mr<0 || mc<0 || mr>=N || mc>=N || visit[mr][mc] || isOver[mr][mc]) continue;
				ad.addLast(new int[]{mr, mc});
			}
		}
	}
}
