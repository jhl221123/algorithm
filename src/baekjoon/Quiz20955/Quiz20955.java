package baekjoon.Quiz20955;

import java.io.*;

/*
Gold4: 민서의 응급 수술 / [tree, union-find]
*/
public class Quiz20955 {

	private static int[] parent;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] NM = br.readLine().split(" ");
		int N = Integer.parseInt(NM[0]);
		int M = Integer.parseInt(NM[1]);

		int connectedEdgeCount = 0;
		int cycleCount = 0;
		parent = new int[N + 1];
		for(int i=1; i<=N; i++) {
			parent[i] = i;
		}

		for(int i=0; i<M; i++) {
			String[] edge = br.readLine().split(" ");
			int from = Integer.parseInt(edge[0]);
			int to = Integer.parseInt(edge[1]);
			if(find(from) == find(to)) {
				cycleCount++;
			} else {
				union(from, to);
				connectedEdgeCount++;
			}
		}

		int treeCount = N - connectedEdgeCount;
		System.out.println(treeCount - 1 + cycleCount);
	}

	private static int find(int child) {
		if(parent[child] == child) return child;
		return parent[child] = find(parent[child]);
	}

	private static void union(int l, int r) {
		int pl = find(l);
		int pr = find(r);
		if(pl < pr) {
			parent[pl] = pr;
		} else {
			parent[pr] = pl;
		}
	}
}
