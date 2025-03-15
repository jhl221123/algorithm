package baekjoon.Quiz2042;

import java.io.*;

/*
Gold1: 구간 합 구하기 / [data structure, segment tree]
1. 세그먼트 트리를 활용해 구간 합을 관리한다.
2. a가 1일 경우, 값을 변경한다.
3. a가 2일 경우, 해당 구간의 합을 탐색한다.
*/
public class Quiz2042 {

	private static int N, M, K;
	private static long[] numbers;
	private static long[] tree;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] nmk = br.readLine().split(" ");
		N = Integer.parseInt(nmk[0]);
		M = Integer.parseInt(nmk[1]);
		K = Integer.parseInt(nmk[2]);
		numbers = new long[N];
		for(int i=0; i<N; i++) {
			numbers[i] = Long.parseLong(br.readLine());
		}

		initializeTree();
		StringBuilder ans = new StringBuilder();
		for(int i=0; i<M + K; i++) {
			String[] abc = br.readLine().split(" ");
			int a = Integer.parseInt(abc[0]);

			if(a == 1) {
				int b = Integer.parseInt(abc[1]);
				long c = Long.parseLong(abc[2]);

				long delta = c - numbers[b - 1];
				updateNumber(0, N - 1, 1, b - 1, delta);
				numbers[b - 1] = c;
			} else {
				int b = Integer.parseInt(abc[1]);
				int c = Integer.parseInt(abc[2]);

				ans.append(sumInterval(0, N - 1, 1, b - 1, c - 1)).append("\n");
			}
		}

		System.out.print(ans);
	}

	private static void initializeTree() {
		int height = (int)Math.ceil(Math.log(N) / Math.log(2)) + 1;
		int totalNodeCount = (int)Math.pow(2, height) - 1;
		tree = new long[totalNodeCount + 1];
		buildTree(0, N - 1, 1);
	}

	private static long buildTree(int s, int e, int node) {
		if(s == e) return tree[node] = numbers[s];
		int m = (s + e) / 2;
		return tree[node] = buildTree(s, m, node * 2) + buildTree(m + 1, e, node * 2 + 1);
	}

	private static void updateNumber(int s, int e, int node, int idx, long delta) {
		if(s <= idx && e >= idx) tree[node] += delta;
		else return;

		if(s == e) return;

		int m = (s + e) / 2;
		updateNumber(s, m, node * 2, idx, delta);
		updateNumber(m + 1, e, node * 2 + 1, idx, delta);
	}

	private static long sumInterval(int s, int e, int node, int l, int r) {
		if(s > r || e < l) return 0;
		if(l <= s && r >= e) return tree[node];

		int m = (s + e) / 2;
		return sumInterval(s, m, node * 2, l, r) + sumInterval(m + 1, e, node * 2 + 1, l, r);
	}
}
