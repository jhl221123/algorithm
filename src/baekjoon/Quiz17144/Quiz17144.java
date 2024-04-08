package baekjoon.Quiz17144;

import java.util.*;
import java.io.*;

public class Quiz17144 {
	static int R, C, T;
	static int[] machine = new int[2];
	static int[][] map;
	static int[][] tempMap;
	static int[] dy = {-1, 1, 0, 0};
	static int[] dx = {0, 0, -1, 1};
	static int ans;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		T = Integer.parseInt(st.nextToken());
		map = new int[R+1][C+1];
		tempMap = new int[R+1][C+1];
		int machineIdx = 0;
		for(int i=1; i<=R; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=1; j<=C; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if(map[i][j] == -1) {
					machine[machineIdx++] = i;
					map[i][j] = 0;
				}
			}
		}
		while(T-- > 0) {
			dustBfs();
			addDust();
			tempMap = new int[R+1][C+1];
			moveDust();
		}
		for(int i=1; i<=R; i++) {
			for(int j=0; j<=C; j++) {
				ans += map[i][j];
			}
		}
		System.out.println(ans);
	}
	private static void dustBfs() {
		for(int r=1; r<=R; r++) {
			for(int c=1; c<=C; c++) {
				int sum = 0;
				for(int d=0; d<4; d++) {
					int mr = r + dy[d];
					int mc = c + dx[d];
					if(mr < 1 || mc < 1 || mr > R || mc > C) continue;
					if(mc == 1 && (mr == machine[0] || mr == machine[1])) continue; // 공기 청정기
					tempMap[mr][mc] += (map[r][c] / 5);
					sum += (map[r][c] / 5);
				}
				tempMap[r][c] -= sum;
			}
		}
	}
	private static void addDust() {
		for(int r=1; r<=R; r++) {
			for(int c=1; c<=C; c++) {
				map[r][c] += tempMap[r][c];
			}
		}
	}
	private static void moveDust() {
		int cc = 1;
		int cr = machine[0];
		// 위쪽 공기청정기 왼쪽
		while(cr != 1) {
			map[cr][cc] = map[cr-1][cc];
			cr--;
		}
		map[machine[0]][1] = 0; // 공기청정기로 들어간 먼지 제거
		// 위쪽 공기청정기 위쪽
		while(cc != C) {
			map[cr][cc] = map[cr][cc+1];
			cc++;
		}
		// 위쪽 공기청정기 오른쪽
		while(cr != machine[0]) {
			map[cr][cc] = map[cr+1][cc];
			cr++;
		}
		// 위쪽 공기청정기 아래쪽
		while(cc != 1) {
			map[cr][cc] = map[cr][cc-1];
			cc--;
		}

		cc = 1;
		cr = machine[1];
		// 아래쪽 공기청정기 왼쪽
		while(cr != R) {
			map[cr][cc] = map[cr+1][cc];
			cr++;
		}
		map[machine[1]][1] = 0; // 공기청정기로 들어간 먼지 제거
		// 아래쪽 공기청정기 아래쪽
		while(cc != C) {
			map[cr][cc] = map[cr][cc+1];
			cc++;
		}
		// 아래쪽 공기청정기 오른쪽
		while(cr != machine[1]) {
			map[cr][cc] = map[cr-1][cc];
			cr--;
		}
		// 아래쪽 공기청정기 위쪽
		while(cc != 1) {
			map[cr][cc] = map[cr][cc-1];
			cc--;
		}
	}
}
