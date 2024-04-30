package baekjoon.Quiz13905;

import java.util.*;
import java.io.*;

public class Quiz13905 {
	static int N, M, S, E;
	static List<Node>[] list;
	static boolean[] visit;
	static PriorityQueue<Node> pq = new PriorityQueue<>((o1, o2) -> o2.k - o1.k);
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		st = new StringTokenizer(br.readLine());
		S = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(st.nextToken());
		list = new List[N+1];
		for(int i=0; i<=N; i++) {
			list[i] = new ArrayList<>();
		}
		visit = new boolean[N+1];
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int k = Integer.parseInt(st.nextToken());
			list[from].add(new Node(to, k, k));
			list[to].add(new Node(from, k, k));
		}
		System.out.println(dijk());
	}
	static private int dijk() {
		visit[S] = true;
		for(Node node : list[S]) {
			pq.offer(node);
		}
		while(!pq.isEmpty()) {
			Node ce = pq.poll();
			if(visit[ce.to]) continue;
			visit[ce.to] = true;
			if(ce.to == E) return ce.min;
			for(Node ne : list[ce.to]) {
				if(visit[ne.to]) continue;
				ne.min = Math.min(ne.min, ce.min);
				pq.offer(ne);
			}
		}
		return 0;
	}
	static class Node {
		int to, k, min;
		public Node(int to, int k, int min) {
			this.to = to;
			this.k = k;
			this.min = min;
		}
	}
}
