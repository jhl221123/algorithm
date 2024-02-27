package baekjoon.Quiz1916;

import java.util.*;
import java.io.*;

public class Quiz1916 {
	// 우선순위 큐로 간선 리스트 표현
	// 시작 정점에서 거리가 짧은 순서대로 탐색
	// 다음 경로까지 최소 거리: (현재 정점의 결정된 최소 거리 + 다음 정점까지 거리), 저장된 거리 비교
	static int N, M;
	static List<Edge>[] list;
	static PriorityQueue<Edge> q = new PriorityQueue<>((e1, e2) -> e1.cost - e2.cost);
	static int[] dist;
	static boolean[] visit;
	static final int INF = Integer.MAX_VALUE;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		M = Integer.parseInt(br.readLine());
		list = new List[N+1];
		for(int i=0; i<=N; i++) {
			list[i] = new ArrayList<>();
		}
		// init list
		for(int i=1; i<=M; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			list[from].add(new Edge(to, v));
			//            list[to].add(new Edge(from, v));
		}
		// init dist
		dist = new int[N+1];
		Arrays.fill(dist, INF);
		visit = new boolean[N+1];
		// input start, end
		StringTokenizer st = new StringTokenizer(br.readLine());
		int start = Integer.parseInt(st.nextToken());
		int end = Integer.parseInt(st.nextToken());
		dist[start] = 0;
		q.offer(new Edge(start, 0));
		while(!q.isEmpty()) {
			Edge from = q.poll();
			if(visit[from.value]) continue;
			visit[from.value] = true;

			for(Edge to : list[from.value]) {
				int newDist = dist[from.value] + to.cost;
				int originDist = dist[to.value];
				if( newDist < originDist) {
					dist[to.value] = newDist;
					to.cost = newDist;
					q.offer(to);
				}
			}
		}
		System.out.println(dist[end]);
	}
	static class Edge {
		int value, cost;
		public Edge(int value, int cost) {
			this.value = value;
			this.cost = cost;
		}
	}
}
