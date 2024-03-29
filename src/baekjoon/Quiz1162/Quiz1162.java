package baekjoon.Quiz1162;

import java.util.*;
import java.io.*;

public class Quiz1162 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		List<Edge>[] list = new List[N+1];
		for(int i=0; i<=N; i++) {
			list[i] = new ArrayList<>();
		}
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			int pre = Integer.parseInt(st.nextToken());
			int ne = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			list[pre].add(new Edge(ne, 0, c));
			list[ne].add(new Edge(pre, 0, c));
		}
		boolean[][] visit = new boolean[K + 1][N + 1];
		long[][] dist = new long[K+1][N+1];
		for(int i=0; i<=K; i++) {
			Arrays.fill(dist[i], Long.MAX_VALUE);
		}
		for(int i=0; i<=K; i++) {
			dist[i][1] = 0;
		}
		PriorityQueue<Edge> q = new PriorityQueue<>(Comparator.comparingLong(e -> e.c));
		q.offer(new Edge(1, 0, 0));
		while(!q.isEmpty()) {
			Edge pre = q.poll();
			if(visit[pre.k][pre.v]) continue;
			visit[pre.k][pre.v] = true;
			for(Edge ne : list[pre.v]) {
				if(visit[pre.k][ne.v]) continue;
				long newDistNonUsingK = dist[pre.k][pre.v] + ne.c;
				long originDistNonUsingK = dist[pre.k][ne.v];
				// 포장 안하는 경우
				dist[pre.k][ne.v] = Math.min(originDistNonUsingK, newDistNonUsingK);
				q.offer(new Edge(ne.v, pre.k, dist[pre.k][ne.v]));
				// 포장 하는 경우 - 단, 포장 가능한 횟수가 남아 있어야 한다.
				if(pre.k+1 <= K) {
					if(visit[pre.k+1][ne.v]) continue;
					long newDistUsingK = dist[pre.k][pre.v];
					long originDistUsingK = dist[pre.k+1][ne.v];
					dist[pre.k+1][ne.v] = Math.min(originDistUsingK, newDistUsingK);
					q.offer(new Edge(ne.v, pre.k+1, dist[pre.k+1][ne.v]));
				}
			}
		}
		long ans = Long.MAX_VALUE;
		for(int i=0; i<=K; i++) {
			//System.out.println(Arrays.toString(dist[i]));
			ans = Math.min(ans, dist[i][N]);
		}
		System.out.println(ans);
	}
	static class Edge {
		int v, k;
		long c;
		public Edge(int v, int k, long c) {
			this.v = v;
			this.k = k;
			this.c = c;
		}
	}
}
