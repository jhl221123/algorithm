package baekjoon.Quiz1197;

import java.util.*;
import java.io.*;

public class Quiz1197 {
	static int V, E;
	static int[] parents;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		V = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(st.nextToken());
		PriorityQueue<Edge> pq = new PriorityQueue<>((e1, e2) -> e1.cost - e2.cost);

		for(int i=0; i<E; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int cost = Integer.parseInt(st.nextToken());
			pq.offer(new Edge(from, to, cost));
		}
		parents = new int[V+1];
		makeSet();
		int link = 0;
		int sum = 0;
		while(true) {
			Edge edge = pq.poll();
			if(union(edge.from, edge.to)) {
				link++;
				sum += edge.cost;
			}
			if(link == V-1) {
				System.out.println(sum);
				return;
			}
		}
	}
	static void makeSet() {
		for(int i=1; i<=V; i++) {
			parents[i] = i;
		}
	}
	static int find(int c) {
		if(parents[c] == c) return c;
		return parents[c] = find(parents[c]);
	}
	static boolean union(int a, int b) {
		int pa = find(a);
		int pb = find(b);
		if(pa == pb) return false;
		parents[pa] = pb;
		return true;
	}
	static class Edge {
		int from, to, cost;
		public Edge(int from, int to, int cost) {
			this.from = from;
			this.to = to;
			this.cost = cost;
		}
	}
}
