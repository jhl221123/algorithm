package baekjoon.Quiz1922;

import java.util.*;
import java.io.*;

public class Quiz1922 {
	static int N, M;
	static int[] parent;
	static PriorityQueue<Edge> pq = new PriorityQueue<>((o1, o2) -> o1.v - o2.v);
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		M = Integer.parseInt(br.readLine());
		parent = new int[N+1];
		for(int i=0; i<M; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			pq.offer(new Edge(from, to, v));
		}
		makeSet();
		int ans = 0;
		int link = 0;
		while(!pq.isEmpty()) {
			Edge e = pq.poll();
			if(union(e.from, e.to)) {
				ans += e.v;
				link++;
			}
			if(link == N-1) break;
		}
		System.out.println(ans);
	}
	static void makeSet() {
		for(int i=1; i<=N; i++) {
			parent[i] = i;
		}
	}
	static int find(int c) {
		if(parent[c] == c) return c;
		return parent[c] = find(parent[c]);
	}
	static boolean union(int a, int b) {
		int ap = find(a);
		int bp = find(b);
		if(ap == bp) return false;
		parent[ap] = parent[bp];
		return true;
	}
	static class Edge {
		int from, to, v;
		public Edge(int from, int to, int v) {
			this.from = from;
			this.to = to;
			this.v = v;
		}
	}
}
