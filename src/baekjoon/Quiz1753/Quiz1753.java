package baekjoon.Quiz1753;

import java.util.*;
import java.io.*;

public class Quiz1753 {
	static int V, E;
	static List<List<Edge>> list;
	static PriorityQueue<Edge> q;
	static boolean[] visit;
	static int[] cost;
	static final int INF = Integer.MAX_VALUE;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		V = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(st.nextToken());
		int start = Integer.parseInt(br.readLine());
		list = new ArrayList<>();
		for(int i=0; i<=V; i++) {
			list.add(new ArrayList<>());
		}
		//init
		for(int i=0; i<E; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int cost = Integer.parseInt(st.nextToken());
			list.get(from).add(new Edge(to, cost));
		}
		q = new PriorityQueue<>((e1, e2) -> e1.c - e2.c);
		visit = new boolean[V+1];
		cost = new int[V+1];
		Arrays.fill(cost, INF);
		// excute
		cost[start] = 0;
		q.offer(new Edge(start, 0));
		while(!q.isEmpty()) {
			Edge from = q.poll();
			if(visit[from.v]) continue;
			visit[from.v] = true;
			for(Edge to : list.get(from.v)) {
				int originDist = cost[to.v];
				int newDist = cost[from.v] + to.c;
				if(newDist < originDist) {
					cost[to.v] = newDist;
					to.c = newDist;
					q.offer(to);
				}
			}
		}
		for(int i=1; i<=V; i++) {
			System.out.println(cost[i] == INF ? "INF" : cost[i]);
		}
	}
	static class Edge {
		int v, c;
		public Edge(int v, int c) {
			this.v = v;
			this.c = c;
		}
	}
}
