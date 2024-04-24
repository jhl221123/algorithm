package baekjoon.Quiz1238;

import java.util.*;
import java.io.*;

public class Quiz1238 {
	static int N, M, X;
	static List<Edge>[] list;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		X = Integer.parseInt(st.nextToken());
		list = new List[N+1];
		for(int i=0; i<=N; i++) {
			list[i] = new ArrayList<>();
		}
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			list[from].add(new Edge(to, v));
		}
		int ans = 0;
		for(int i=1; i<=N; i++) {
			if(i==X) continue;
			ans = Math.max(ans, getTotalDist(i, X));
		}
		System.out.println(ans);
	}
	private static int getTotalDist(int s, int e) {
		return dik(s, e) + dik(e, s);
	}
	private static int dik(int from, int to) {
		int[] dist = new int[N+1];
		for(int i=0; i<=N; i++) {
			dist[i] = Integer.MAX_VALUE;
		}
		dist[from] = 0;
		PriorityQueue<Edge> pq = new PriorityQueue<>((o1, o2) -> o1.v - o2.v);
		pq.offer(new Edge(from, 0));
		while(!pq.isEmpty()) {
			Edge edge = pq.poll();
			for(Edge ne : list[edge.to]) {
				int originDist = dist[ne.to];
				int newDist = dist[edge.to] + ne.v;
				if(newDist<originDist) {
					dist[ne.to] = newDist;
					pq.offer(new Edge(ne.to, newDist));
				}
			}
		}
		return dist[to];
	}
	static class Edge {
		int to, v;
		public Edge(int to, int v) {
			this.to = to;
			this.v = v;
		}
	}
}
