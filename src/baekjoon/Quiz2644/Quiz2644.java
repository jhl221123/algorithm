package baekjoon.Quiz2644;

import java.io.*;
import java.util.*;

/*
Silver2: 촌수계산 / [bfs]
*/
public class Quiz2644 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[] pair = Arrays.stream(br.readLine().split(" "))
			.mapToInt(Integer::parseInt)
			.toArray();
		int M = Integer.parseInt(br.readLine());
		List<List<Integer>> graph = new ArrayList<>();
		for(int i=0; i<=N; i++) {
			graph.add(new ArrayList<>());
		}
		for(int i=0; i<M; i++) {
			int[] edge = Arrays.stream(br.readLine().split(" "))
				.mapToInt(Integer::parseInt)
				.toArray();
			graph.get(edge[0]).add(edge[1]);
			graph.get(edge[1]).add(edge[0]);
		}

		ArrayDeque<int[]> ad = new ArrayDeque<>();
		ad.addLast(new int[] {pair[0], 0});

		boolean[] visited = new boolean[N + 1];
		int count = -1;
		while(!ad.isEmpty()) {
			int[] node = ad.removeFirst();
			if(visited[node[0]]) continue;
			visited[node[0]] = true;

			if(node[0] == pair[1]) {
				count = node[1];
				break;
			}

			for(int next : graph.get(node[0])) {
				if(visited[next]) continue;
				ad.addLast(new int[] {next, node[1] + 1});
			}
		}

		System.out.println(count);
	}
}
