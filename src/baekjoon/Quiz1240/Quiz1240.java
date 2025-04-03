package baekjoon.Quiz1240;

import java.util.*;
import java.io.*;

/*
Gold5: 노드사이의 거리 / [bfs]
1. 인접 리스트에 각 노드 사이의 거리를 입력한다.
2. 넓이 우선 탐색으로 출발지 노드부터 도착지 노드까지 거리를 합하며 탐색한다.
*/
public class Quiz1240 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] nm = br.readLine().split(" ");
		int N = Integer.parseInt(nm[0]);
		int M = Integer.parseInt(nm[1]);
		List<List<int[]>> graph = new ArrayList<>();
		for(int i=0; i<=N; i++) {
			graph.add(new ArrayList<>());
		}

		for(int i=0; i<N-1; i++) {
			String[] edgeInfo = br.readLine().split(" ");
			int from = Integer.parseInt(edgeInfo[0]);
			int to = Integer.parseInt(edgeInfo[1]);
			int dist = Integer.parseInt(edgeInfo[2]);

			graph.get(from).add(new int[] {to, dist});
			graph.get(to).add(new int[] {from, dist});
		}

		StringBuilder sb = new StringBuilder();
		for(int i=0; i<M; i++) {
			String[] edgeInfo = br.readLine().split(" ");
			int from = Integer.parseInt(edgeInfo[0]);
			int to = Integer.parseInt(edgeInfo[1]);

			boolean[] visited = new boolean[N + 1];
			ArrayDeque<int[]> ad = new ArrayDeque<>();
			ad.addLast(new int[] {from, 0});
			while(!ad.isEmpty()) {
				int[] node = ad.removeFirst();
				int number = node[0];
				int dist = node[1];

				if(number == to) {
					sb.append(dist).append("\n");
				}

				if(visited[number]) {
					continue;
				}
				visited[number] = true;

				for(int[] next : graph.get(number)) {
					if(visited[next[0]]) {
						continue;
					}
					ad.addLast(new int[] {next[0], next[1] + dist});
				}
			}
		}

		System.out.print(sb);
	}
}
