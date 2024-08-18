package baekjoon.Quiz20440;

import java.util.*;
import java.io.*;

public class Quiz20440 {
	static final int INPUT = 1;
	static final int OUTPUT = 0;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		Node[] nodes = new Node[N * 2];
		int idx = 0;
		while(idx < N * 2) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int input = Integer.parseInt(st.nextToken());
			int output = Integer.parseInt(st.nextToken());
			nodes[idx++] = new Node(input, INPUT);
			nodes[idx++] = new Node(output, OUTPUT);
		}
		Arrays.sort(nodes, (o1, o2) -> {
			if(o1.v == o2.v) return o1.type - o2.type;
			return o1.v - o2.v;
		});

		int max = 0;
		int cnt = 0;
		int s = 0;
		int e = 0;
		for(int i=N * 2 - 1; i>=0; i--) {
			if(nodes[i].type == OUTPUT) {
				cnt++;
				if(cnt > max) {
					s = nodes[i].v;
					max = cnt;
				} else if(cnt == max && nodes[i].v != e) s = nodes[i].v;
			} else if(nodes[i].type == INPUT) {
				cnt--;
				if(cnt + 1 == max) e = nodes[i].v;
			}
		}
		System.out.println(max);
		System.out.println(e + " " + s);
	}

	static class Node {
		int v;
		int type;

		public Node(int v, int type) {
			this.v = v;
			this.type = type;
		}
	}
}
