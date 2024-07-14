package swea.d4;

import java.util.*;
import java.io.*;

public class Quiz1238 {
	static List<List<Integer>> list;
	static Node[] nodes;
	static boolean[] visit;
	static int result;
	static StringBuilder sb = new StringBuilder();
	static int cnt;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = 10;

		for(int test_case = 1; test_case <= T; test_case++) {
			// 방향 그래프, bfs 탐색, 큐에 넣을 때 최댓값을 매번 갱신
			StringTokenizer st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			int start = Integer.parseInt(st.nextToken());
			// list init
			list = new ArrayList<>();
			for(int i=0; i<101; i++) {
				list.add(new ArrayList<>());
			}
			// data init
			st = new StringTokenizer(br.readLine());
			for(int i=0; i<N/2; i++) {
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				list.get(a).add(b);
			}
			nodes = new Node[101];
			for(int i=0; i<=100; i++) {
				nodes[i] = new Node(i, 0);
			}
			result = 0;
			cnt = 0;
			visit = new boolean[101];
			bfs(start);
			excute();
			sb.append("#").append(test_case).append(" ").append(result).append("\n");
		}
		System.out.print(sb);
	}
	private static void bfs(int start) {
		ArrayDeque<Integer> ad = new ArrayDeque<>();
		ad.addLast(start);
		visit[start] = true;
		nodes[start].w += 1;
		while(!ad.isEmpty()) {
			int n = ad.removeFirst();
			for(int adj : list.get(n)) {
				if(!visit[adj]) {
					visit[adj] = true;
					ad.addLast(adj);
					nodes[adj].w = nodes[n].w + 1;
				}
			}
		}
	}
	static void excute() {
		int max = 0;
		for(Node node : nodes) {
			max = Math.max(node.w, max);
		}
		for(Node node : nodes) {
			if(node.w == max) result = Math.max(result, node.v);
		}
	}
	static class Node {
		int v, w;

		public Node(int v, int w) {
			this.v = v;
			this.w = w;
		}
	}
}
