package baekjoon.Quiz1647;

import java.util.*;
import java.io.*;
public class Quiz1647 {
	// MST 만들고, 완성된 MST의 간선 중 최대값을 제외하고 합을 구한다.
	static int N, M;
	static int[] parent;
	static PriorityQueue<Edge> pq = new PriorityQueue<>(Comparator.comparingInt(e -> e.cost));
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		makeSet();
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int cost = Integer.parseInt(st.nextToken());
			pq.offer(new Edge(from, to, cost));
		}
		int max = 0;
		int sum = 0;
		int link = 0;
		while(!pq.isEmpty()) {
			Edge edge = pq.poll();
			if(union(edge.from, edge.to)) {
				max = Math.max(max, edge.cost);
				sum += edge.cost;
				link++;
				if(link == N-1) break;
			}
		}
		System.out.println(sum - max);
	}
	static void makeSet() {
		parent = new int[N+1];
		for(int i=1; i<=N; i++) {
			parent[i] = i;
		}
	}
	static int find(int c) {
		if(parent[c] == c) return c;
		return parent[c] = find(parent[c]);
	}
	static boolean union(int from, int to) {
		int fp = find(from);
		int tp = find(to);
		if(fp == tp) return false;
		parent[fp] = tp;
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
