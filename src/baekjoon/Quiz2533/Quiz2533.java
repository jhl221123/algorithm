package baekjoon.Quiz2533;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/*
Gold3: 사회망 서비스(SNS) / [Tree, DP]
1. 깊이 우선 탐색을 통해 현재 노드가 얼리어답터일 경우와 아닐 경우에 따라 얼리어답터의 수를 구한다.
2. 시작 노드의 얼리어답터의 수를 출력한다.
 */
public class Quiz2533 {

	public static void main(String[] args) throws IOException {
		new Quiz2533().solve();
	}

	private void solve() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[][] dp = new int[N + 1][2];
		boolean[] visited = new boolean[N + 1];
		List<Integer>[] tree = new ArrayList[N + 1];
		for(int i=1; i<=N; i++) {
			tree[i] = new ArrayList<>();
		}

		for(int i=0; i<N-1; i++) {
			String[] edge = br.readLine().split(" ");
			int u = Integer.parseInt(edge[0]);
			int v = Integer.parseInt(edge[1]);
			tree[u].add(v);
			tree[v].add(u);
		}


		dfs(1, dp, visited, tree);

		System.out.println(Math.min(dp[1][0], dp[1][1]));
	}

	private void dfs(int node, int[][] dp, boolean[] visited, List<Integer>[] tree) {
		visited[node] = true;
		dp[node][0] = 0;
		dp[node][1] = 1;

		for(int child : tree[node]) {
			if(!visited[child]) {
				dfs(child, dp, visited, tree);
				dp[node][0] += dp[child][1];
				dp[node][1] += Math.min(dp[child][0], dp[child][1]);
			}
		}
	}
}
