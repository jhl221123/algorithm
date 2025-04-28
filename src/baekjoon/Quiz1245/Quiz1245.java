package baekjoon.Quiz1245;

import java.util.*;
import java.io.*;

/*
Gold5: 농장 관리 / [bfs, flood-fill]
1. flood fill로 영역을 구분한다.
2. 모든 좌표를 순회하며 주변에 자신보다 높은 곳이 있다면 해당 좌표가 포함된 영역은 언덕에서 제외시킨다.
3. 언덕 개수를 출력한다.
*/
public class Quiz1245 {

	private static final int[] dy = {-1, -1, -1, 0, 1, 1, 1, 0};
	private static final int[] dx = {-1, 0, 1, 1, 1, 0, -1, -1};

	private static int N, M;
	private static int[][] map;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] nm = br.readLine().split(" ");
		N = Integer.parseInt(nm[0]);
		M = Integer.parseInt(nm[1]);
		map = new int[N][M];
		for(int row=0; row<N; row++) {
			String[] line = br.readLine().split(" ");

			for(int col=0; col<M; col++) {
				map[row][col] = Integer.parseInt(line[col]);
			}
		}

		int[][] regions = new int[N][M];
		int totalRegionCount = markRegion(regions);

		boolean[] isHill = new boolean[totalRegionCount];
		Arrays.fill(isHill, true);
		excludeUnsatisfiedHillCondition(regions, isHill);

		int totalHillCount = 0;
		for(int i=0; i<totalRegionCount; i++) {
			if(isHill[i]) {
				totalHillCount++;
			}
		}

		System.out.println(totalHillCount);
	}

	private static void excludeUnsatisfiedHillCondition(int[][] regions, boolean[] isHill) {
		for(int row=0; row<N; row++) {
			for(int col=0; col<M; col++) {
				for(int d=0; d<8; d++) {
					int mr = row + dy[d];
					int mc = col + dx[d];
					int regionNumber = regions[row][col];

					if(mr<0 || mc<0 || mr>=N || mc>=M) continue;
					if(!isHill[regionNumber]) continue;
					if(regions[row][col] != regions[mr][mc] && map[row][col] < map[mr][mc]) {
						isHill[regionNumber] = false;
						break;
					}
				}
			}
		}
	}

	private static int markRegion(int[][] regions) {
		boolean[][] visited = new boolean[N][M];
		int regionNumber = 0;

		for(int row=0; row<N; row++) {
			for(int col=0; col<M; col++) {
				if(visited[row][col]) continue;
				floodFill(row, col, regionNumber, visited, regions);
				regionNumber++;
			}
		}

		return regionNumber;
	}

	private static void floodFill(int row, int col, int regionNumber, boolean[][] visited, int[][] regions) {
		ArrayDeque<int[]> ad = new ArrayDeque<>();
		ad.addLast(new int[] {row, col});

		while(!ad.isEmpty()) {
			int[] point = ad.removeFirst();
			int r = point[0];
			int c = point[1];

			if(visited[r][c]) continue;
			visited[r][c] = true;
			regions[r][c] = regionNumber;

			for(int d=0; d<8; d++) {
				int mr = r + dy[d];
				int mc = c + dx[d];

				if(mr<0 || mc<0 || mr>=N || mc>=M || visited[mr][mc]) continue;
				if(map[mr][mc] == map[row][col]) {
					ad.addLast(new int[] {mr, mc});
				}
			}
		}
	}
}
