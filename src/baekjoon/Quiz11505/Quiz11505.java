package baekjoon.Quiz11505;

import java.io.*;

/*
Gold1: 구간 곱 구하기 / [data structure, segment tree]
1. 세그먼트 트리를 활용해 구간 곱을 관리한다.
2. a가 1일 경우, 값을 변경한다.
2-1. 변경 전, 숫자 배열의 값을 먼저 변경한 후 트리에 반영한다.
3. a가 2일 경우, 해당 구간의 곱을 탐색한다.
*/
public class Quiz11505 {

	private static final long MOD = 1_000_000_007L;

	private static int N, M, K;
	private static long[] tree;
	private static long[] numbers;

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
				int b = Integer.parseInt(abc[1]) - 1;
				long c = Long.parseLong(abc[2]);

				numbers[b] = c;
				updateNode(0, N - 1, 1, b, c);
			} else {
				int b = Integer.parseInt(abc[1]) - 1;
				int c = Integer.parseInt(abc[2]) - 1;
				ans.append(multiplyInterval(0, N - 1, 1, b, c)).append("\n");
			}
		}

		System.out.print(ans);
	}

	private static void initializeTree() {
		int height = (int)Math.ceil(Math.log(N) / Math.log(2)) + 1;
		tree = new long[(int)Math.pow(2, height)];
		buildTree(0, N - 1, 1);
	}

	private static long buildTree(int s, int e, int node) {
		if(s == e) return tree[node] = numbers[s];

		int m = (s + e) / 2;
		return tree[node] = buildTree(s, m, node * 2) * buildTree(m + 1, e, node * 2 + 1) % MOD;
	}

	private static long updateNode(int s, int e, int node, int idx, long updatedValue) {
		if(s > idx || e < idx) return tree[node];
		if(s == e) {
			if(s == idx) tree[node] = updatedValue;
			return tree[node];
		}

		int m = (s + e) / 2;
		return tree[node] = updateNode(s, m, node * 2, idx, updatedValue) * updateNode(m + 1, e, node * 2 + 1, idx, updatedValue) % MOD;
	}

	private static long multiplyInterval(int s, int e, int node, int l, int r) {
		if(s > r || e < l) return 1L;
		if(l <= s && r >= e) return tree[node];

		int m = (s + e) / 2;
		return multiplyInterval(s, m, node * 2, l, r) * multiplyInterval(m + 1, e, node * 2 + 1, l, r) % MOD;
	}
}
