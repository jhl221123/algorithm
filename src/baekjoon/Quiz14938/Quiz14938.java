package baekjoon.Quiz14938;

import java.io.*;
import java.util.*;

public class Quiz14938 {

	private static final int INF = 100_000;

	private static int N, M, R;
	private static int[] items;
	private static int[][] graph;

	public static void main(String[] args) throws IOException {
		input();
		calculateDistanceBetweenVilages();
		int maxItemCount = calculateMaxItems();

		System.out.println(maxItemCount);
	}

	private static int calculateMaxItems() {
		int maxItemCount = 0;

		for(int from=1; from<=N; from++) {
			int itemCount = 0;

			for(int to=1; to<=N; to++) {
				if(graph[from][to] <= M) {
					itemCount += items[to];
				}
			}

			maxItemCount = Math.max(maxItemCount, itemCount);
		}

		return maxItemCount;
	}

	private static void calculateDistanceBetweenVilages() {
		for(int via=1; via<=N; via++) {
			for(int from=1; from<=N; from++) {
				for(int to=1; to<=N; to++) {
					graph[from][to] = Math.min(graph[from][to], graph[from][via] + graph[via][to]);
				}
			}
		}
	}

	private static void input() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());

		st = new StringTokenizer(br.readLine());
		items = new int[N + 1];
		for(int vilage=1; vilage<=N; vilage++) {
			items[vilage] = Integer.parseInt(st.nextToken());
		}

		graph = new int[N+1][N+1];
		for(int from=1; from<=N; from++) {
			for(int to=1; to<=N; to++) {
				if(from == to) {
					graph[from][to] = 0;
					continue;
				}

				graph[from][to] = INF;
			}
		}

		int edgeCount = R;
		while(edgeCount-- > 0) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int dist = Integer.parseInt(st.nextToken());
			graph[from][to] = dist;
			graph[to][from] = dist;
		}
	}
}
