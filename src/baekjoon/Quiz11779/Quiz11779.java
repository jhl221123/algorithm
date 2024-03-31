package baekjoon.Quiz11779;

import java.util.*;
import java.io.*;

public class Quiz11779 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		int m = Integer.parseInt(br.readLine());
		List<Edge>[] list = new List[n+1];
		for(int i=0; i<=n; i++) {
			list[i] = new ArrayList<>();
		}
		for(int i=0; i<m; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int cost = Integer.parseInt(st.nextToken());
			list[from].add(new Edge(to, cost));
		}
		StringTokenizer st = new StringTokenizer(br.readLine());
		int start = Integer.parseInt(st.nextToken());
		int end = Integer.parseInt(st.nextToken());
		int[] dist = new int[n+1];
		Arrays.fill(dist, Integer.MAX_VALUE);
		dist[start] = 0;
		boolean[] visit = new boolean[n+1];
		PriorityQueue<Edge> q = new PriorityQueue<>((q1, q2) -> q1.c - q2.c);
		q.offer(new Edge(start, 0));
		int[] path = new int[n+1];
		while(!q.isEmpty()) {
			Edge from = q.poll();
			if(visit[from.v]) continue;
			visit[from.v] = true;
			for(Edge to : list[from.v]) {
				int newDist = dist[from.v] + to.c;
				int originDist = dist[to.v];
				if(originDist > newDist) {
					dist[to.v] = newDist;
					to.c = newDist;
					q.offer(to);
					path[to.v] = from.v;
				}
			}
		}
		System.out.println(dist[end]);
		ArrayDeque<Integer> stack = new ArrayDeque<>();
		int now = end;
		while (now != 0) {
			stack.addLast(now);
			now = path[now];
		}
		System.out.println(stack.size());
		while(!stack.isEmpty()) {
			System.out.print(stack.removeLast() + " ");
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
