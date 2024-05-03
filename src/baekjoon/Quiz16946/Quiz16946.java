package baekjoon.Quiz16946;

import java.util.*;
import java.io.*;

public class Quiz16946 {
	static int N, M;
	static int[][] map;
	static int[][][] cntMap; // 3차원 0: cnt, 3차원 1: regionIdx
	static int regionIdx = 1;
	static Map<Integer, Integer> sizeByRegion = new HashMap<>(); // 구역별 개수 관리
	static boolean[][] visit;
	static int[] dy = {-1, 1, 0, 0};
	static int[] dx = {0, 0, -1, 1};
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		cntMap = new int[N][M][2];
		visit = new boolean[N][M];
		// 입력
		for(int i=0; i<N; i++) {
			String str = br.readLine();
			for(int j=0; j<M; j++) {
				map[i][j] = str.charAt(j) - '0';
			}
		}
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				if(!visit[i][j] && map[i][j] == 0) {
					int size = floodFill(i, j);
					sizeByRegion.put(regionIdx, size);
					regionIdx++;
				}
			}
		}
		fillCnt();
		cntBfs();
		StringBuilder sb = new StringBuilder();
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				if(map[i][j] == 0) sb.append(0);
				else {
					int target = cntMap[i][j][0] % 10;
					sb.append(target);
				}
			}
			sb.append("\n");
		}
		System.out.print(sb);
	}
	private static int floodFill(int x, int y) {
		int size = 0;
		ArrayDeque<int[]> ad = new ArrayDeque<>();
		ad.addLast(new int[] {x, y});
		while(!ad.isEmpty()) {
			int[] point = ad.poll();
			int r = point[0];
			int c = point[1];
			if(visit[r][c]) continue;
			visit[r][c] = true;
			cntMap[r][c][1] = regionIdx;
			size+=1;
			for(int d=0; d<4; d++) {
				int mr = r + dy[d];
				int mc = c + dx[d];
				if(!isIn(mr, mc) || map[mr][mc] == 1 || visit[mr][mc]) continue;
				ad.addLast(new int[] {mr, mc});
			}
		}
		return size;
	}
	private static void fillCnt() {
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				if(map[i][j] == 0) {
					int regionIdx = cntMap[i][j][1];
					int size = sizeByRegion.get(regionIdx);
					cntMap[i][j][0] = size;
				}
			}
		}
	}
	private static void cntBfs() {
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				if(map[i][j] ==1) {
					int sum = 0;
					Set<Integer> regions = new HashSet<>();
					for(int d=0; d<4; d++) {
						int mr = i + dy[d];
						int mc = j + dx[d];
						if(isIn(mr, mc) && map[mr][mc] == 0) {
							if(regions.contains(cntMap[mr][mc][1])) continue;
							regions.add(cntMap[mr][mc][1]);
							sum += cntMap[mr][mc][0];
						}
					}
					cntMap[i][j][0] = sum + 1;
				}
			}
		}
	}
	private static boolean isIn(int r, int c) {
		return r >= 0 && c >= 0 && r < N && c < M;
	}
}
