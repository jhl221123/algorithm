package baekjoon.Quiz1967;

import java.util.*;
import java.io.*;

/*
1. 루트 노드에서 가장 먼 노드를 탐색한다.
2. 해당 노드에서 다시 가장 먼 노드를 탐색했을 때, 두 노드 간 거리가 트리의 지름이다.
 */

public class Quiz1967 {
	static int maxDistance = 0;
	static int farthestNode = 0;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());

		List<List<int[]>> tree = new ArrayList<>();
		for (int i = 0; i <= N; i++) {
			tree.add(new ArrayList<>());
		}

		for (int i = 0; i < N - 1; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int parent = Integer.parseInt(st.nextToken());
			int child = Integer.parseInt(st.nextToken());
			int cost = Integer.parseInt(st.nextToken());
			tree.get(parent).add(new int[] {child, cost});
			tree.get(child).add(new int[] {parent, cost});
		}

		boolean[] visited = new boolean[N + 1];
		dfs(1, 0, tree, visited);

		Arrays.fill(visited, false);
		maxDistance = 0;
		dfs(farthestNode, 0, tree, visited);

		System.out.println(maxDistance);
	}

	private static void dfs(int node, int distance, List<List<int[]>> tree, boolean[] visited) {
		visited[node] = true;

		if (distance > maxDistance) {
			maxDistance = distance;
			farthestNode = node;
		}

		for (int[] edge : tree.get(node)) {
			int nextNode = edge[0];
			int cost = edge[1];
			if (!visited[nextNode]) {
				dfs(nextNode, distance + cost, tree, visited);
			}
		}
	}
}
