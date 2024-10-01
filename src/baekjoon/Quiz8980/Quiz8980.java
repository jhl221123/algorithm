package baekjoon.Quiz8980;

import java.io.*;
import java.util.*;

public class Quiz8980 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int C = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(br.readLine());
		Node[] nodes = new Node[M];
		int[] remainSpaces = new int[N+1];
		Arrays.fill(remainSpaces, C);

		for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			int s = Integer.parseInt(st.nextToken());
			int e = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			nodes[i] = new Node(s, e, c);
		}

		Arrays.sort(nodes, (o1, o2) -> {
			if(o1.e == o2.e) return o1.s - o2.s;
			return o1.e - o2.e;
		});

		int sum = 0;
		for(int i=0; i<M; i++) {
			Node node = nodes[i];
			int min = C;
			for(int base = node.s; base < node.e; base++) {
				min = Math.min(remainSpaces[base], min);
			}
			if(min >= node.c) {
				sum += node.c;
				for(int base = node.s; base < node.e; base++) {
					remainSpaces[base] -= node.c;
				}
			} else {
				sum += min;
				for(int base = node.s; base < node.e; base++) {
					remainSpaces[base] -= min;
				}
			}
		}

		System.out.println(sum);
	}

	static class Node {
		int s, e, c;

		public Node(int s, int e, int c) {
			this.s = s;
			this.e = e;
			this.c = c;
		}
	}
}
