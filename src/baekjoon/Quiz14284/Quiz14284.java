package baekjoon.Quiz14284;

import java.util.*;
import java.io.*;

/*
Gold5: 간선 이어가기 2 / [dijkstra]
1. 목표 지점간 최단 경로를 찾는다.
2. 최단 경로의 가중치를 출력한다.
*/
public class Quiz14284 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] nm = br.readLine().split(" ");
		int n = Integer.parseInt(nm[0]);
		int m = Integer.parseInt(nm[1]);
		List<List<Node>> list = new ArrayList<>();
		for(int i=0; i<=n; i++) {
			list.add(new ArrayList<>());
		}

		for(int i=0; i<m; i++) {
			String[] abc = br.readLine().split(" ");
			int a = Integer.parseInt(abc[0]);
			int b = Integer.parseInt(abc[1]);
			int c = Integer.parseInt(abc[2]);

			list.get(a).add(new Node(b, c));
			list.get(b).add(new Node(a, c));
		}

		String[] se = br.readLine().split(" ");
		int start = Integer.parseInt(se[0]);
		int end = Integer.parseInt(se[1]);
		boolean[] visited = new boolean[n + 1];
		PriorityQueue<Node> pq = new PriorityQueue<>((o1, o2) -> o1.dist - o2.dist);
		pq.offer(new Node(start, 0));

		while(!pq.isEmpty()) {
			Node node = pq.poll();
			if(node.to == end) {
				System.out.println(node.dist);
				break;
			}

			if(visited[node.to]) continue;
			visited[node.to] = true;

			for(Node next : list.get(node.to)) {
				if(visited[next.to]) continue;
				pq.offer(new Node(next.to, next.dist + node.dist));
			}
		}
	}

	static class Node {
		int to, dist;

		public Node(int to, int dist) {
			this.to = to;
			this.dist = dist;
		}
	}
}
