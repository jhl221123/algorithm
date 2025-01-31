package baekjoon.Quiz1167;

import java.util.*;
import java.io.*;

/*
1. 임의의 점에서 가장 먼 점을 탐색한다.
2. 해당 점에서 다시 가장 먼 점을 탐색하고, 두 점의 거리를 출력한다.
 */
public class Quiz1167 {
	private static List<List<Node>> tree;
	private static int farNode;
	private static int farDistance;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int V = Integer.parseInt(br.readLine());
		tree = new ArrayList<>();
		for(int i=0; i<=V; i++) {
			tree.add(new ArrayList<>());
		}

		for(int i=1; i<=V; i++) {
			StringTokenizer nodeInfo = new StringTokenizer(br.readLine());
			int parent = Integer.parseInt(nodeInfo.nextToken());
			List<Node> sub = tree.get(parent);

			while(true) {
				int child = Integer.parseInt(nodeInfo.nextToken());
				if(child == -1) break;
				int cost = Integer.parseInt(nodeInfo.nextToken());
				sub.add(new Node(child, cost));
			}
		}

		boolean[] visited = new boolean[V + 1];
		dfs(1, 0, visited);

		Arrays.fill(visited, false);
		farDistance = 0;
		dfs(farNode, 0, visited);

		System.out.println(farDistance);
	}

	private static void dfs(int from, int distance, boolean[] visited) {
		visited[from] = true;

		if(farDistance < distance) {
			farDistance = distance;
			farNode = from;
		}

		for(Node node : tree.get(from)) {
			if(visited[node.to]) continue;
			dfs(node.to, distance + node.v, visited);
		}
	}

	static class Node {
		int to, v;

		public Node(int to, int v) {
			this.to = to;
			this.v = v;
		}
	}
}
