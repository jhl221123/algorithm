package baekjoon.Quiz2573;

import java.util.*;
import java.io.*;

public class Quiz2573 {
	// 1. 빙산 위치 입력
	// 1-1. 빙산 존재 유무 배열 exist 생성 -> 빙산이 존재하면 true
	// 2. 모든 빙산을 순회하면서 빙산 높이 갱신 -> 4방향 탐색 후 0 개수만큼 높이 감소
	// 2-1. 갱신된 빙산 높이가 0이하라면 exist에 반영 -> false로 수정
	// 2-2. 소요 일수 + 1
	// 3. 하나의 지점에서 방문 탐색 수행
	// 3-1. 방문이 끝난 후, exist가 true인 모든 지점을 방문했는지 확인
	// 3-2. 방문하지 않은 빙산이 있는 최초의 시점을 출력

	static int N;
	static int M;
	static int[][] map;
	static int[][] copiedMap;
	static boolean[][] exist;
	static boolean[][] visit;
	static int dayCount;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		exist = new boolean[N][M];
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken()); //1
				if(map[i][j]>0) exist[i][j] = true; //1-1
			}
		}
		visit = new boolean[N][M];
		while(!isDivide()) {
			visit = new boolean[N][M];
			passADay();
			dayCount++;
		}
		System.out.println(dayCount);
	}
	private static void passADay() {
		// 현재 빙산 상태를 복사
		copyMap();
		// 모든 빙산을 순회하면서 빙산 높이 갱신
		for(int i=1; i<N-1; i++) {
			for(int j=1; j<M-1; j++) {
				if(map[i][j]>0) melt(i, j); // 2
			}
		}
		// 현재 빙산 상태를 갱신
		map = copiedMap;
	}
	private static void copyMap() {
		copiedMap = new int[N][M];
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				copiedMap[i][j] = map[i][j];
			}
		}
	}
	static int[] dy = {-1, 1, 0, 0};
	static int[] dx = {0, 0, -1, 1};
	private static void melt(int r, int c) {
		// 4방향 탐색 후 0 개수만큼 높이 감소
		int seaCount = 0;
		for(int i=0; i<4; i++) {
			int mr = r + dy[i];
			int mc = c + dx[i];
			if(map[mr][mc] == 0) seaCount++;
		}
		copiedMap[r][c] = Math.max(map[r][c] - seaCount, 0);
		// 갱신된 빙산 높이가 0이하라면 exist에 반영 -> false로 수정
		if(copiedMap[r][c] == 0) exist[r][c] = false;
	}
	private static boolean isDivide() {
		// 하나의 지점에서 방문 탐색 수행
		ArrayDeque<int[]> ad = new ArrayDeque<>();
		bk: for(int i=1; i<N-1; i++) {
			for(int j=1; j<M-1; j++) {
				if(exist[i][j]) {
					ad.addLast(new int[] {i, j});
					visit[i][j] = true;
					break bk;
				}
			}
		}
		while(!ad.isEmpty()) {
			int[] point = ad.removeFirst();
			int cr = point[0];
			int cc = point[1];
			for(int i=0; i<4; i++) {
				int mr = cr + dy[i];
				int mc = cc + dx[i];
				if(!visit[mr][mc] && exist[mr][mc]) {
					visit[mr][mc] = true;
					ad.addLast(new int[] {mr, mc});
				}
			}
		}
		// exist가 true인 모든 지점이 방문되었는지 확인
		for(int i=1; i<N-1; i++) {
			for(int j=1; j<M-1; j++) {
				if(exist[i][j] && !visit[i][j]) return true;
			}
		}
		// 분리되지 않는 경우
		boolean impossible = true;
		for(int i=1; i<N-1; i++) {
			for(int j=1; j<M-1; j++) {
				if (exist[i][j]) {
					impossible = false;
					break;
				}
			}
		}
		if(impossible) {
			dayCount = 0;
			return true;
		}
		return false;
	}
}
