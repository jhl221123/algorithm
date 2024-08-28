package baekjoon.Quiz11000;

import java.util.*;
import java.io.*;

public class Quiz11000 {
	public static final int OUT = 0;
	public static final int IN = 1;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		PriorityQueue<Node> pq = new PriorityQueue<>((o1, o2) -> {
			if(o1.v == o2.v) return o1.type - o2.type;
			return o1.v - o2.v;
		});

		for(int i=0; i<N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int in = Integer.parseInt(st.nextToken());
			int out = Integer.parseInt(st.nextToken());
			pq.offer(new Node(in, IN));
			pq.offer(new Node(out, OUT));
		}

		int count = 0;
		int answer = 0;
		while(!pq.isEmpty()) {
			Node node = pq.poll();
			if(node.type == IN) count++;
			else count--;
			answer = Math.max(answer, count);
		}

		System.out.println(answer);
	}

	static class Node {
		int v, type;

		public Node(int v, int type) {
			this.v = v;
			this.type = type;
		}
	}
}
