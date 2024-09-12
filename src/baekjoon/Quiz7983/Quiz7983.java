package baekjoon.Quiz7983;

import java.io.*;
import java.util.*;

public class Quiz7983 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		Node[] nodes = new Node[N];
		for(int i=0; i<N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int d = Integer.parseInt(st.nextToken());
			int t = Integer.parseInt(st.nextToken());
			nodes[i] = new Node(d, t);
		}

		Arrays.sort(nodes, (o1, o2) -> o2.t - o1.t);

		int ans = nodes[0].t;
		for(int i=0; i<N; i++) {
			if(nodes[i].t < ans) ans = nodes[i].t;
			ans -= nodes[i].d;
		}

		System.out.println(ans);
	}

	static class Node {
		int d, t;

		public Node(int d, int t) {
			this.d = d;
			this.t = t;
		}
	}
}
