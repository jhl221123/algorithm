package baekjoon.Quiz1194;

import java.util.*;
import java.io.*;

public class Quiz1194 {
	static int N, M;
	static char[][] map;
	static int[][][] distance;
	static int[] dy = {-1, 1, 0, 0};
	static int[] dx = {0, 0, -1, 1};
	static final int WALL = 1;
	static final int NONE = 2;
	static final int KEY = 3;
	static final int DOOR = 4;
	static final int FINISH = 5;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new char[N][M];
		distance = new int[N][M][1<<6];
		ArrayDeque<int[]> ad = new ArrayDeque<>();
		for(int i=0; i<N; i++) {
			String str = br.readLine();
			for(int j=0; j<M; j++) {
				map[i][j] = str.charAt(j);
				if(map[i][j] == '0') {
					ad.addLast(new int[] {i, j, 0});
					distance[i][j][0] = 1;
				}
			}
		}
		int result = 0;
		boolean isComplete = false;
		bk: while(!ad.isEmpty()) {
			int[] point = ad.removeFirst();
			int r = point[0];
			int c = point[1];
			int key = point[2];
			for(int i=0; i<4; i++) {
				int mr = r + dy[i];
				int mc = c + dx[i];
				if(mr < 0 || mr >=N || mc < 0 || mc >= M) continue;
				int type = getType(mr, mc);
				switch(type) {
					case WALL: break;
					case NONE: {
						if(distance[mr][mc][key] == 0) {
							distance[mr][mc][key] = distance[r][c][key] + 1;
							ad.addLast(new int[] {mr, mc, key});
						}
					} break;
					case KEY: {
						int newKey = key | (1 << (map[mr][mc] - 'a'));
						if(distance[mr][mc][newKey] == 0) {
							distance[mr][mc][newKey] = distance[r][c][key] + 1;
							ad.addLast(new int[] {mr, mc, newKey});
						}
					} break;
					case DOOR: {
						int door = 1 << (map[mr][mc] - 'A');
						if(distance[mr][mc][key] == 0 && ((key & door) != 0)) {
							distance[mr][mc][key] = distance[r][c][key] + 1;
							ad.addLast(new int[] {mr, mc, key});
						}
					} break;
					case FINISH: {
						result = distance[r][c][key] + 1;
						isComplete = true;
						break bk;
					}
				}
			}
		}
		if(isComplete) System.out.println(result-1);
		else System.out.println(-1);
	}
	private static int getType(int r, int c) {
		char target = map[r][c];
		if(target == '#') return WALL;
		else if(target == '.' || target == '0') return NONE;
		else if(target >= 'a' && target <= 'z') return KEY;
		else if(target >= 'A' && target <= 'Z') return DOOR;
		else if(target == '1') return FINISH;
		else return -1;
	}
}
