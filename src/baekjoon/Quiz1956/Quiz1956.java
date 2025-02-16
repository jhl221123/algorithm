package baekjoon.Quiz1956;

import java.util.*;
import java.io.*;

/*
1. 인접 리스트를 만든다.
2. 입력을 통해 경유지가 없는 이동을 한다.
3. 모든 정점에 경유지를 거쳐 이동할 수 있는 최소 거리를 구한다.
4. 행과 열이 같은 좌표를 탐색하며 가장 작은 값을 출력한다.
*/
public class Quiz1956 {

	private static final int INF = 100_000_000;

	private static int V, E;
	private static int[][] map;

	public static void main(String[] args) throws IOException {
		input();
		calculateMinDist();
		System.out.println(findMinDistOfCycle());
	}

	private static int findMinDistOfCycle() {
		int min = INF;

		for(int village=1; village<=V; village++) {
			min = Math.min(min, map[village][village]);
		}

		return min == INF ? -1 : min;
	}

	private static void calculateMinDist() {
		for(int via=1; via<=V; via++) {
			for(int from=1; from<=V; from++) {
				for(int to=1; to<=V; to++) {
					map[from][to] = Math.min(map[from][to], map[from][via] + map[via][to]);
				}
			}
		}
	}

	private static void input() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		V = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(st.nextToken());
		map = new int[V + 1][V + 1];
		for(int village=0; village<=V; village++){
			Arrays.fill(map[village], INF);
		}

		for(int edge=0; edge<E; edge++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int dist = Integer.parseInt(st.nextToken());
			map[from][to] = dist;
		}
	}
}
