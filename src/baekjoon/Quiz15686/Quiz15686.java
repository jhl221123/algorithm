package baekjoon.Quiz15686;

import java.util.*;
import java.io.*;

public class Quiz15686 {
	static int N;
	static int M;
	static int[][] arr;
	static int[][][] visit;
	static List<int[]> chickenHouse;
	static List<int[]> house;
	static int[] dy = {-1, 1, 0, 0};
	static int[] dx = {0, 0, -1, 1};
	static boolean[] tgt;
	static int min = Integer.MAX_VALUE;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		arr = new int[N][N];
		chickenHouse = new ArrayList<>();
		house = new ArrayList<>();
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<N; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
				if(arr[i][j] == 2) chickenHouse.add(new int[] {i, j});
				if(arr[i][j] == 1) house.add(new int[] {i, j});
			}
		}
		visit = new int[chickenHouse.size()][N][N];
		tgt = new boolean[chickenHouse.size()];
		// 치킨집 별 치킨 거리
		int no = 0;
		for(int[] spot : chickenHouse) {
			bfs(no++, spot[0], spot[1]);
		}
		comb(0, 0);
		System.out.println(min);
	}
	private static int excute() {
		// 각 집을 돌면서 tgt에 있는 치킨집 중 최소 거리를 더한다.
		int result = 0;
		for(int[] housePoint : house) {
			int min = Integer.MAX_VALUE;
			for(int i=0; i<tgt.length; i++) {
				if(!tgt[i]) continue;
				min = Math.min(min, visit[i][housePoint[0]][housePoint[1]]-1);
			}
			result += min;
		}
		return result;
	}
	private static void comb(int chickenHouseIdx, int tgtIdx) {
		if(tgtIdx == M) {
			// 연산
			min = Math.min(min, excute());
			return;
		}
		if(chickenHouseIdx == chickenHouse.size()) return;
		tgt[chickenHouseIdx] = true;
		comb(chickenHouseIdx + 1, tgtIdx + 1);
		tgt[chickenHouseIdx] = false;
		comb(chickenHouseIdx + 1, tgtIdx);
	}

	private static void bfs(int chickenHouseNo, int r, int c) {
		ArrayDeque<int[]> ad = new ArrayDeque<>();
		ad.addLast(new int[] {chickenHouseNo, r, c});
		visit[chickenHouseNo][r][c] = 1;
		while(!ad.isEmpty()) {
			int[] point = ad.removeFirst();
			int nr = point[1];
			int nc = point[2];
			for(int i=0; i<4; i++) {
				int mr = nr + dy[i];
				int mc = nc + dx[i];
				if(mr < 0 || mr >= N || mc < 0 || mc >= N) continue;
				if(visit[chickenHouseNo][mr][mc] == 0) {
					visit[chickenHouseNo][mr][mc] = visit[chickenHouseNo][nr][nc] + 1;
					ad.addLast(new int[] {chickenHouseNo, mr, mc});
				}
			}
		}
	}
}
