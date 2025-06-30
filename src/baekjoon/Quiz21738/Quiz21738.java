package baekjoon.Quiz21738;

import java.util.*;
import java.io.*;

/*
Gold5: 얼음깨기 펭귄 / [tree, bfs]
*/
public class Quiz21738 {
	private static int N, S, P;
	private static List<List<Integer>> tree;
	private static boolean[] visited;
	private static int count;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] NSP = br.readLine().split(" ");
		N = Integer.parseInt(NSP[0]);
		S = Integer.parseInt(NSP[1]);
		P = Integer.parseInt(NSP[2]);
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
		bfs();
		System.out.println(N - count - 1);
	}

	private static void bfs() {
		ArrayDeque<int[]> ad = new ArrayDeque<>();
		ad.add(new int[] {P, 0});
		int letch = 2;

		while(!ad.isEmpty()) {
			int[] node = ad.removeFirst();
			if(visited[node[0]]) continue;
			visited[node[0]] = true;
			if(node[0] <= S) {
				count += node[1];
				letch--;
				if(letch == 0) break;
				continue;
			}
			for(Integer next : tree.get(node[0])) {
				if(visited[next]) continue;
				ad.addLast(new int[] {next, node[1] + 1});
			}
		}
	}
}
