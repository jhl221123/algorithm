package baekjoon.Quiz1939;

import java.util.*;
import java.io.*;

public class Quiz1939 {
	static int N, M, S, E;
	static List<Node>[] list;
	static int[] visit;
	static PriorityQueue<Node> pq = new PriorityQueue<>((o1, o2) -> o2.k - o1.k);
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		list = new List[N+1];
		for(int i=0; i<=N; i++) {
			list[i] = new ArrayList<>();
		}
		visit = new int[N+1];
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int k = Integer.parseInt(st.nextToken());
			list[from].add(new Node(to, k, k));
			list[to].add(new Node(from, k, k));
		}
		st = new StringTokenizer(br.readLine());
		S = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(st.nextToken());
		System.out.println(dijk());
	}
	static private int dijk() {
		int sm = 1000000001;
		for(Node node : list[S]) {
			sm = Math.max(sm, node.min);
			pq.offer(node);
		}
		visit[S] = sm;
		while(!pq.isEmpty()) {
			Node ce = pq.poll();
			if(visit[ce.to] >= ce.min) continue;
			visit[ce.to] = ce.min;
			if(ce.to == E) return ce.min;
			for(Node ne : list[ce.to]) {
				if(visit[ne.to] >= ne.min) continue;
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
