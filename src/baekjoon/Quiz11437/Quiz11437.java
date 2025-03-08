package baekjoon.Quiz11437;

import java.util.*;
import java.io.*;

/*
Gold3: LCA / [lca, tree, dfs]
1. 트리를 구축한다.
2. 루트부터 dfs 순회하며 부모 및 깊이 배열을 초기화한다.
3. 입력된 두 정점의 깊이가 동일해질 때까지 탐색한다.
4. 깊이가 같다면 두 정점의 부모가 동일해질 때까지 탐색한다.
*/
public class Quiz11437 {

	private static List<List<Integer>> tree;
	private static int[] parents;
	private static int[] depths;
	private static boolean[] visited;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		tree = new ArrayList<>();
		for(int i=0; i<=N; i++) {
			tree.add(new ArrayList<>());
		}

		for(int i=0; i<N-1; i++) {
			String[] nodes = br.readLine().split(" ");
			int v1 = Integer.parseInt(nodes[0]);
			int v2 = Integer.parseInt(nodes[1]);
			tree.get(v1).add(v2);
			tree.get(v2).add(v1);
		}

		parents = new int[N + 1];
		depths = new int[N + 1];
		visited = new boolean[N + 1];

		dfs(1, 0);

		StringBuilder ans = new StringBuilder();
		int M = Integer.parseInt(br.readLine());
		for(int i=0; i<M; i++) {
			String[] nodes = br.readLine().split(" ");
			int v1 = Integer.parseInt(nodes[0]);
			int v2 = Integer.parseInt(nodes[1]);
			ans.append(lca(v1, v2)).append("\n");
		}

		System.out.print(ans);
	}

	private static int lca(int v1, int v2) {
		if(depths[v1] != depths[v2]) {
			if(depths[v1] > depths[v2]) {
				while(true) {
					v1 = parents[v1];
					if(depths[v1] == depths[v2]) break;
				}
			} else {
				while(true) {
					v2 = parents[v2];
					if(depths[v2] == depths[v1]) break;
				}
			}
		}

		if(v1 == v2) return v1;

		if(parents[v1] != parents[v2]) {
			while(true) {
				v1 = parents[v1];
				v2 = parents[v2];

				if(parents[v1] == parents[v2]) break;
			}
		}

		return parents[v1];
	}

	private static void dfs(int parent, int depth) {
		depths[parent] = depth;
		visited[parent] = true;

		if(tree.get(parent).isEmpty()) {
			return;
		}

		for(int i=0; i<tree.get(parent).size(); i++) {
			int child = tree.get(parent).get(i);
			if(visited[child]) continue;
			parents[child] = parent;
			dfs(child, depth + 1);
		}
	}
}
