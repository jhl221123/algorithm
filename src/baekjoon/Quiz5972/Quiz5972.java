package baekjoon.Quiz5972;

import java.util.*;
import java.io.*;

/*
Gold5: 택배 배송 / [dijkstra]
1. 시작 지점의 인근 노드를 우선순위 큐에 넣는다.
2. 우선순위 큐를 통해 최소 비용의 노드를 선택하며 도착지점까지 탐색한다.
*/
public class Quiz5972 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] nm = br.readLine().split(" ");
		int N = Integer.parseInt(nm[0]);
		int M = Integer.parseInt(nm[1]);
		List<List<Edge>> graph = new ArrayList<>();
		for(int i=0; i<=N; i++) {
			graph.add(new ArrayList<>());
		}

		for(int i=0; i<M; i++) {
			String[] abc = br.readLine().split(" ");
			int A = Integer.parseInt(abc[0]);
			int B = Integer.parseInt(abc[1]);
			int C = Integer.parseInt(abc[2]);

			graph.get(A).add(new Edge(B, C));
			graph.get(B).add(new Edge(A, C));
		}

		int min = 100_000_000;
		boolean[] visited = new boolean[N + 1];
		visited[1] = true;
		PriorityQueue<Edge> pq = new PriorityQueue<>((o1, o2) -> o1.cost - o2.cost);
		for(Edge child : graph.get(1)) {
			pq.offer(new Edge(child.to, child.cost));
		}

		while(!pq.isEmpty()) {
			Edge edge = pq.poll();
			int to = edge.to;
			int cost = edge.cost;

			if(to == N) {
				System.out.println(cost);
				return;
			}

			if(visited[to]) {
				continue;
			}
			visited[to] = true;

			for(Edge next : graph.get(to)) {
				int nt = next.to;
				if(visited[nt]) {
					continue;
				}
				pq.offer(new Edge(nt, cost + next.cost));
			}
		}
	}

	static class Edge {
		int to, cost;

		public Edge(int to, int cost) {
			this.to = to;
			this.cost = cost;
		}
	}
}
