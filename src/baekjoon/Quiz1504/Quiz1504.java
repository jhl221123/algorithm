package baekjoon.Quiz1504;

import java.util.*;
import java.io.*;

public class Quiz1504 {
	static int N, E, sp1, sp2;
	static List<Edge>[] list;
	static int[] dist;
	static PriorityQueue<Edge> pq;
	static int INF = 200000000;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(st.nextToken());
		list = new List[N+1];
		for(int i=0; i<=N; i++) {
			list[i] = new ArrayList<>();
		}
		for(int i=0; i<E; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			list[from].add(new Edge(to, v));
			list[to].add(new Edge(from, v));
		}
		st = new StringTokenizer(br.readLine());
		sp1 = Integer.parseInt(st.nextToken());
		sp2 = Integer.parseInt(st.nextToken());

		int a = dik(1, sp1);
		int b = dik(sp1, sp2);
		int c = dik(sp2, N);
		int sum1 = (a==INF || b==INF || c==INF) ? -1 : a+b+c;
		a = dik(1, sp2);
		b = dik(sp2, sp1);
		c = dik(sp1, N);
		int sum2 = (a==INF || b==INF || c==INF) ? -1 : a+b+c;
		int ans = Math.min(sum1, sum2);
		System.out.println(ans);
	}
	static int dik(int s, int e) {
		// init dist
		dist = new int[N+1];
		for(int i=0; i<=N; i++) {
			dist[i] = INF;
		}
		dist[s] = 0;
		// init pq
		pq = new PriorityQueue<>(Comparator.comparingInt(o -> o.v));
		pq.offer(new Edge(s, 0));
		// dik
		while(!pq.isEmpty()) {
			Edge edge = pq.poll();
			int cur = edge.to;
			for(Edge ne : list[cur]) {
				int originDist = dist[ne.to];
				int newDist = dist[cur] + ne.v;
				if(newDist < originDist) {
					dist[ne.to] = newDist;
					pq.offer(new Edge(ne.to, newDist));
				}
			}
		}
		return dist[e];
	}
	static class Edge {
		int to, v;
		public Edge(int to, int v) {
			this.to = to;
			this.v = v;
		}
	}
}
