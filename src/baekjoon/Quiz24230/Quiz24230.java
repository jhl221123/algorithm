package baekjoon.Quiz24230;

import java.util.*;
import java.io.*;

/*
Gold5: 트리 색칠하기 / [Tree]
*/
public class Quiz24230 {
	private static List<List<Integer>> tree;
	private static int[] colors;
	private static boolean[] visited;
	private static int count;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		String[] colorInfo = br.readLine().split(" ");
		colors = new int[N + 1];
		for(int i=1; i<=N; i++) {
			colors[i] = Integer.parseInt(colorInfo[i-1]);
		}
		tree = new ArrayList<>();
		for(int i=0; i<=N; i++) {
			tree.add(new ArrayList<>());
		}
		for(int i=0; i<N-1; i++) {
			String[] edge = br.readLine().split(" ");
			int from = Integer.parseInt(edge[0]);
			int to = Integer.parseInt(edge[1]);
			tree.get(from).add(to);
			tree.get(to).add(from);
		}

		visited = new boolean[N + 1];
		dfs(1, -1);
		System.out.println(count);
	}

	private static void dfs(int node, int prevColor) {
		if(visited[node]) {
			return;
		}
		visited[node] = true;

		int currentColor = colors[node];
		if(currentColor != prevColor) {
			if(prevColor == -1 && currentColor > 0) count++;
			else if(prevColor != -1) count++;
		}

		for(Integer to : tree.get(node)) {
			if(visited[to]) continue;
			dfs(to, currentColor);
		}
	}
}
