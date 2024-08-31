package baekjoon.Quiz1374;

import java.util.*;
import java.io.*;

public class Quiz1374 {

	public static final int INPUT = 0;
	public static final int OUTPUT = 1;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		PriorityQueue<Node> pq = new PriorityQueue<>((o1, o2) -> {
			if(o1.v == o2.v) return o2.type - o1.type;
			return o1.v - o2.v;
		});

		while(N-- > 0) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int num = Integer.parseInt(st.nextToken());
			int s = Integer.parseInt(st.nextToken());
			int e = Integer.parseInt(st.nextToken());
			pq.offer(new Node(s, INPUT));
			pq.offer(new Node(e, OUTPUT));
		}

		int min = 0;
		int cnt = 0;
		while(!pq.isEmpty()) {
			Node node = pq.poll();
			if(node.type == INPUT) {
				cnt++;
				min = Math.max(min, cnt);
			} else cnt--;
		}

		System.out.println(min);
	}

	static class Node {
		int v, type;

		public Node(int v, int type) {
			this.v = v;
			this.type = type;
		}
	}
}
