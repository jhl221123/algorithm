package baekjoon.Quiz2146;

import java.util.*;
import java.io.*;

/*
1. 섬 번호를 계산한다.
2. contry의 모든 칸을 순회하며 다음 작업을 수행한다.
2-1. 현재 칸이 육지라면 네 방향으로 탐색을 시작한다.
2-1-1. contry 범위를 벗어나면 탐색을 종료한다.
2-1-2. 같은 번호의 섬이라면 탐색을 종료한다.
2-1-3. 이미 방문한 곳이라면 탐색을 종료한다.
2-1-4. 바다라면 이동거리 + 1 하고 탐색을 이어나간다.
2-1-5. 다른 번호의 섬이라면 탐색을 종료하고 이동한 거리 - 1 을 최소값과 비교한다.
2-2. 현재 칸이 바다라면 다음 칸으로 이동한다.
3. 최소값을 출력한다.
*/

public class Quiz2146 {

	private static final int[] dy = {-1, 1, 0, 0};
	private static final int[] dx = {0, 0, -1, 1};

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[][] country = initializeCountry(N, br);
		int[][] islandNumbers = defineIslandNumber(country);

		System.out.println(calculateMinimumBridgeDistance(country, islandNumbers));
	}

	private static int[][] initializeCountry(int N, BufferedReader br) throws IOException {
		int[][] country = new int[N][N];

		for(int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());

			for(int j = 0; j < N; j++) {
				country[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		return country;
	}

	private static int[][] defineIslandNumber(int[][] country) {
		int n = country.length;
		int islandNumber = 1;
		int[][] islandNumbers = new int[n][n];

		for(int row=0; row<n; row++) {
			for(int col=0; col<n; col++) {
				if(islandNumbers[row][col] > 0) continue;
				if(country[row][col] == 1) {
					boolean[][] visited = new boolean[n][n];
					ArrayDeque<int[]> ad = new ArrayDeque<>();
					ad.addLast(new int[]{row, col});

					while(!ad.isEmpty()) {
						int[] land = ad.removeFirst();
						int r = land[0];
						int c = land[1];

						if(visited[r][c]) continue;
						visited[r][c] = true;
						if(country[r][c] == 1) islandNumbers[r][c] = islandNumber;

						for(int d=0; d<4; d++) {
							int mr = r + dy[d];
							int mc = c + dx[d];

							if(mr < 0 || mc < 0 || mr >= n || mc >= n) continue;
							if(visited[mr][mc]) continue;
							if(country[mr][mc] == 0) continue;
							ad.addLast(new int[]{mr, mc});
						}
					}

					islandNumber++;
				}
			}
		}

		return islandNumbers;
	}

	private static int calculateMinimumBridgeDistance(int[][] country, int[][] islandNumbers) {
		int n = country.length;
		int minimumDistance = 100001;

		for(int row=0; row<n; row++) {
			for(int col=0; col<n; col++) {
				if(country[row][col] == 1) {
					int currentIslandNumber = islandNumbers[row][col];
					boolean[][] visited = new boolean[n][n];
					ArrayDeque<int[]> ad = new ArrayDeque<>();
					ad.addLast(new int[]{row, col, 0});

					while(!ad.isEmpty()) {
						int[] spot = ad.removeFirst();
						int r = spot[0];
						int c = spot[1];
						int distance = spot[2];

						if(visited[r][c]) continue;
						visited[r][c] = true;
						if(islandNumbers[r][c] > 0 && islandNumbers[r][c] != currentIslandNumber) {
							minimumDistance = Math.min(minimumDistance, distance - 1);
							break;
						}

						for(int d=0; d<4; d++) {
							int mr = r + dy[d];
							int mc = c + dx[d];

							if(mr < 0 || mc < 0 || mr >= n || mc >= n) continue;
							if(visited[mr][mc]) continue;
							if(islandNumbers[mr][mc] == currentIslandNumber) continue;
							ad.addLast(new int[]{mr, mc, distance + 1});
						}
					}
				}
			}
		}

		return minimumDistance;
	}
}
