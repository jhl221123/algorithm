package baekjoon.Quiz11438;

import java.util.*;
import java.io.*;

/*
Platinum5: LCA2 / [lca, sparse table, tree, dfs]
1. tree, depth, parent를 초기화한다.
1-1. parent는 희소배열(노드별 2^n번째 조상)로 관리한다.
2. 주어지는 두 노드의 lca를 구한다.
2-1. 두 노드를 같은 depth로 만든다.
2-1-1. 두 노드의 depth가 동일해질 때까지, depth 차이 내에서 최대한의 2^n 만큼 이동시킨다.
2-1-2. 100_000개의 노드의 최악의 깊이는 2^17 임으로 17부터 0까지 순회하며 depth 차이보다 작거나 같으면 이동한다.
2-2. 동일한 위치에서 lca를 구한다.
2-2-1. 마찬가지로 17부터 0까지 순회하며 조상이 동일하지 않을때만 이동시킨다.
2-2-2. 2^n 미만은 2^n-1로 모두 표현 가능하기 때문에 순회 탐색하여 도출할 수 있다.
*/
public class Quiz11438 {

	private static final int MAX_EXP = 17;

	private static int N, M;
	private static List<List<Integer>> tree;
	private static int[][] parents;
	private static int[] depths;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		tree = new ArrayList<>();
		for(int i=0; i<=N; i++) {
			tree.add(new ArrayList<>());
		}

		for(int i=0; i<N-1; i++) {
			String[] nodes = br.readLine().split(" ");
			int n1 = Integer.parseInt(nodes[0]);
			int n2 = Integer.parseInt(nodes[1]);
			tree.get(n1).add(n2);
			tree.get(n2).add(n1);
		}

		parents = new int[N + 1][MAX_EXP + 1];
		depths = new int[N + 1];

		dfs(1, 1, 0);
		initializeParents();

		M = Integer.parseInt(br.readLine());
		StringBuilder ans = new StringBuilder();
		for(int i=0; i<M; i++) {
			String[] nodes = br.readLine().split(" ");
			int n1 = Integer.parseInt(nodes[0]);
			int n2 = Integer.parseInt(nodes[1]);
			ans.append(lca(n1, n2)).append("\n");
		}

		System.out.print(ans);
	}

	private static void dfs(int node, int depth, int prior) {
		depths[node] = depth;

		for(int next : tree.get(node)) {
			if(next == prior) continue;
			parents[next][0] = node;
			dfs(next, depth + 1, node);
		}
	}

	private static void initializeParents() {
		for(int exp=1; exp<=MAX_EXP; exp++) {
			for(int node=1; node<=N; node++) {
				parents[node][exp] = parents[parents[node][exp - 1]][exp - 1];
			}
		}
	}

	private static int lca(int n1, int n2) {
		if(depths[n1] != depths[n2]) {
			if(depths[n2] > depths[n1]) {
				int tmp = n2;
				n2 = n1;
				n1 = tmp;
			}

			for(int exp=MAX_EXP; exp>=0; exp--) {
				if(depths[n1] - depths[n2] >= Math.pow(2, exp)) {
					n1 = parents[n1][exp];
				}
			}
		}

		if(n1 == n2) return n1;

		for(int exp=MAX_EXP; exp>=0; exp--) {
			if(parents[n1][exp] != parents[n2][exp]) {
				n1 = parents[n1][exp];
				n2 = parents[n2][exp];
			}
		}

		return parents[n1][0];
	}
}
