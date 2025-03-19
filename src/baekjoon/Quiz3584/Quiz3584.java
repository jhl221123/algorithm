package baekjoon.Quiz3584;

import java.util.*;
import java.io.*;

/*
Gold4: 가장 가까운 공통 조상 / [lca, sparse table, binary lifting, tree, dfs]
1. 각 노드의 깊이와 2^n번째 부모를 관리한다.
2. 이진 리프팅을 활용해 두 노드의 깊이를 맞추고, lca를 구한다.
*/
public class Quiz3584 {

	private static int N, height;
	private static List<List<Integer>> tree;
	private static int[] depths;
	private static int[][] parents;
	private static boolean[] isNotRoot;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		StringBuilder ans = new StringBuilder();

		while(T-- > 0) {
			N = Integer.parseInt(br.readLine());

			tree = new ArrayList<>();
			for(int i=0; i<=N; i++) {
				tree.add(new ArrayList<>());
			}

			isNotRoot = new boolean[N + 1];
			for(int i=0; i<N-1; i++) {
				String[] nodes = br.readLine().split(" ");
				int p = Integer.parseInt(nodes[0]);
				int c = Integer.parseInt(nodes[1]);

				tree.get(p).add(c);
				isNotRoot[c] = true;
			}

			int root = 0;
			for(int i=1; i<=N; i++) {
				if(!isNotRoot[i]) {
					root = i;
					break;
				}
			}

			height = (int)Math.ceil(Math.log(N) / Math.log(2)) + 1;
			depths = new int[N + 1];
			parents = new int[N + 1][height + 1];
			initializeDepth(root, 0);
			initializeParents();

			String[] nodes = br.readLine().split(" ");
			int from = Integer.parseInt(nodes[0]);
			int to = Integer.parseInt(nodes[1]);

			ans.append(lca(from, to)).append("\n");
		}

		System.out.print(ans);
	}

	private static void initializeDepth(int node, int depth) {
		depths[node] = depth;

		for(int child : tree.get(node)) {
			parents[child][0] = node;
			initializeDepth(child, depth + 1);
		}
	}

	private static void initializeParents() {
		for(int exp=1; exp<=height; exp++) {
			for(int node=1; node<=N; node++) {
				parents[node][exp] = parents[parents[node][exp-1]][exp-1];
			}
		}
	}

	private static int lca(int from, int to) {
		if(depths[from] != depths[to]) {
			if(depths[from] < depths[to]) {
				int tmp = from;
				from = to;
				to = tmp;
			}

			for(int exp=height; exp>=0; exp--) {
				if(depths[from] - depths[to] >= Math.pow(2, exp)) {
					from = parents[from][exp];
				}
			}
		}

		if(from == to) return from;

		for(int exp=height; exp>=0; exp--) {
			if(parents[from][exp] != parents[to][exp]) {
				from = parents[from][exp];
				to = parents[to][exp];
			}
		}

		return parents[from][0];
	}
}
