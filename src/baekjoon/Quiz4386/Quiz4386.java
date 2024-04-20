package baekjoon.Quiz4386;

import java.util.*;
import java.io.*;

public class Quiz4386 {
	static int N;
	static int[] parent;
	static Star[] stars;
	static PriorityQueue<Edge> pq = new PriorityQueue<>(Comparator.comparingDouble(e -> e.dist));
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		parent = new int[N];
		stars = new Star[N];
		for(int i=0; i<N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			double x = Double.parseDouble(st.nextToken());
			double y = Double.parseDouble(st.nextToken());
			stars[i] = new Star(y, x);
		}
		for(int i=0; i<N; i++) {
			for(int j=i; j<N; j++) {
				if(i==j) continue;
				Star s1 = stars[i];
				Star s2 = stars[j];
				double dist = Math.sqrt(Math.pow(s1.y - s2.y, 2) + Math.pow(s1.x - s2.x, 2));
				pq.offer(new Edge(i, j, dist));
			}
		}
		makeSet();
		int link = 0;
		double sum = 0;
		while(!pq.isEmpty()) {
			Edge edge = pq.poll();
			if(union(edge.from, edge.to)) {
				link++;
				sum += edge.dist;
			}
			if(link == N-1) break;
		}
		System.out.printf("%.2f", sum);
	}
	static void makeSet() {
		for(int i=0; i<N; i++) {
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
		parent[ap] = bp;
		return true;
	}
	static class Star {
		double y, x;
		public Star(double y, double x) {
			this.y = y;
			this.x = x;
		}
	}
	static class Edge {
		int from , to;
		double dist;
		public Edge(int from , int to, double dist) {
			this.from = from;
			this.to = to;
			this.dist = dist;
		}
	}
}
