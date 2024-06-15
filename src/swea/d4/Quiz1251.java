package swea.d4;

import java.util.*;
import java.io.*;

public class Quiz1251 {
	static int N;
	static Island[] islands;
	static double E;
	static boolean[] visit;
	static double sum;
	static PriorityQueue<Edge> q = new PriorityQueue<>(Comparator.comparingDouble(e -> e.dist));
	static StringBuilder sb = new StringBuilder();
	public static void main(String[] args) throws Exception
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());

		for(int test_case = 1; test_case <= T; test_case++)
		{
			N = Integer.parseInt(br.readLine());
			StringTokenizer rst = new StringTokenizer(br.readLine());
			StringTokenizer cst = new StringTokenizer(br.readLine());
			E = Double.parseDouble(br.readLine());
			islands = new Island[N];
			for(int i=0; i<N; i++) {
				int r = Integer.parseInt(rst.nextToken());
				int c = Integer.parseInt(cst.nextToken());
				islands[i] = new Island(r, c);
			}
			visit = new boolean[N];
			sum = 0;
			prim();
			sb.append("#").append(test_case).append(" ").append(Math.round(sum)).append("\n");
		}
		System.out.print(sb);
	}
	static void prim() {
		q.offer(new Edge(0 ,0, 0));
		while(!q.isEmpty()) {
			Edge edge = q.poll();
			if(visit[edge.to]) continue;
			visit[edge.to] = true;
			// 확정된 섬의 거리 * E 를 합계에 누적
			sum += (Math.pow(edge.dist, 2) * E);
			updateQ(edge);
		}
	}
	static void updateQ(Edge edge) {
		int from = edge.to;
		Island target = islands[from];
		for(int i=0; i<N; i++) {
			if(visit[i]) continue;
			Island island = islands[i];
			int tr = island.r;
			int tc = island.c;
			double dist = Math.sqrt(Math.pow(Math.abs(target.r - tr), 2) + Math.pow(Math.abs(target.c-tc), 2));
			q.offer(new Edge(from, i, dist));
		}
	}
	static class Island {
		int r, c;
		public Island(int r, int c) {
			this.r = r;
			this.c = c;
		}
	}
	static class Edge {
		int from, to;
		double dist;
		public Edge(int from, int to, double dist) {
			this.from = from;
			this.to = to;
			this.dist = dist;
		}
	}
}
