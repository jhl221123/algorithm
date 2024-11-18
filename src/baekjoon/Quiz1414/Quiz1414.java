package baekjoon.Quiz1414;

import java.util.*;
import java.io.*;

public class Quiz1414 {
	static int N;
	static int[] parents;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());

		// 정점이 하나일 경우, 연결이 필요없으므로 그대로 기부
		if(N == 1) {
			String str = br.readLine();
			Node node = new Node(1, 1, str.charAt(0));
			System.out.println(node.cost);
			return;
		}

		parents = new int[N+1];
		int total = 0;
		PriorityQueue<Node> pq = new PriorityQueue<>(Comparator.comparingInt(o -> o.cost));
		for(int i=0; i<N; i++) {
			String str = br.readLine();
			for(int j=0; j<N; j++) {
				Node node = new Node(i+1, j+1, str.charAt(j));
				total += node.cost;
				if(i == j) continue;
				pq.offer(node);
			}
		}

		for(int i=0; i<=N; i++) {
			parents[i] = i;
		}

		int link = 0;
		int sum = 0;
		while(!pq.isEmpty()) {
			Node node = pq.poll();
			if(node.cost == 0) continue;
			if(union(node.from, node.to)) {
				link++;
				sum += node.cost;
				if(link == N-1) {
					System.out.println(total - sum);
					return;
				}
			}
		}

		System.out.println(-1);
	}

	private static int find(int c) {
		if(parents[c] == c) return c;
		return parents[c] = find(parents[c]);
	}

	private static boolean union(int from, int to) {
		int pf = find(from);
		int pt = find(to);
		if(pf == pt) return false;
		parents[pf] = pt;
		return true;
	}

	static class Node {
		int from, to, cost;

		public Node(int from, int to, char c) {
			this.from = from;
			this.to = to;
			this.cost = trans(c);
		}

		private int trans(char c) {
			if(c == '0') {
				return 0;
			} else if(c >= 'a' && c <= 'z') {
				return (c - 'a') + 1;
			} else {
				return (c - 'A') + 27;
			}
		}
	}
}
