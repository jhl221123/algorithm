package baekjoon.Quiz11404;

import java.util.*;
import java.io.*;

/*
1. 각 도시 간 0, 1 번 이동했을 때 결과를 초기화한다.
2. 모든 도시를 경유지로 사용했을 때, 나올 수 있는 모든 이동 거리를 탐색하며 최솟값을 갱신한다.
*/

public class Quiz11404 {

	public static final int INF = 100_000_001;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int cities = Integer.parseInt(br.readLine());
		int edges = Integer.parseInt(br.readLine());
		int[][] minimumDistanceBetweenCities = initializeDistanceBetweenCities(cities, edges, br);

		calculateMinimumDistanceBetweenCities(minimumDistanceBetweenCities);
		printMinimumDistanceBetweenCities(minimumDistanceBetweenCities);
	}

	private static int[][] initializeDistanceBetweenCities(int cities, int edges, BufferedReader br) throws IOException {
		int[][] minimumDistanceBetweenCities = new int[cities + 1][cities + 1];

		for (int from = 1; from <= cities; from++) {
			for (int to = 1; to <= cities; to++) {
				if (from == to) {
					minimumDistanceBetweenCities[from][to] = 0;
					continue;
				}

				minimumDistanceBetweenCities[from][to] = INF;
			}
		}

		for (int edge = 0; edge < edges; edge++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int cost = Integer.parseInt(st.nextToken());

			minimumDistanceBetweenCities[from][to] = Math.min(minimumDistanceBetweenCities[from][to], cost);
		}

		return minimumDistanceBetweenCities;
	}

	private static void calculateMinimumDistanceBetweenCities(int[][] minimumDistanceBetweenCities) {
		int cities = minimumDistanceBetweenCities.length - 1;

		for (int stopover = 1; stopover <= cities; stopover++) {
			for (int from = 1; from <= cities; from++) {
				for (int to = 1; to <= cities; to++) {
					updateMinimumDistance(minimumDistanceBetweenCities, from, to, stopover);
				}
			}
		}
	}

	private static void updateMinimumDistance(int[][] minimumDistanceBetweenCities, int from, int to, int stopover) {
		minimumDistanceBetweenCities[from][to] = Math.min(
			minimumDistanceBetweenCities[from][to],
			minimumDistanceBetweenCities[from][stopover] + minimumDistanceBetweenCities[stopover][to]
		);
	}

	private static void printMinimumDistanceBetweenCities(int[][] minimumDistanceBetweenCities) {
		int cities = minimumDistanceBetweenCities.length - 1;

		StringBuilder sb = new StringBuilder();
		for (int from = 1; from <= cities; from++) {
			for (int to = 1; to <= cities; to++) {
				int minimumDistance = getMinimumDistance(minimumDistanceBetweenCities, from, to);
				sb.append(minimumDistance).append(" ");
			}

			sb.append("\n");
		}

		System.out.print(sb);
	}

	private static int getMinimumDistance(int[][] minimumDistanceBetweenCities, int from, int to) {
		return minimumDistanceBetweenCities[from][to] == INF ? 0 : minimumDistanceBetweenCities[from][to];
	}
}
