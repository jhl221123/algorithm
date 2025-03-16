package baekjoon.Quiz10868;

import java.io.*;
import java.util.*;

/*
Gold1: 최솟값 / [data structure, segment tree]
1. 세그먼트 트리를 활용해 구간의 최솟값을 관리한다.
2. 입력되는 구간의 최솟값을 출력한다.
*/
public class Quiz10868 {

	private static final int INF = 2_000_000_000;

	private static int N, M;
	private static int[] numbers;
	private static int[] tree;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] nm = br.readLine().split(" ");
		N = Integer.parseInt(nm[0]);
		M = Integer.parseInt(nm[1]);
		numbers = new int[N];
		for(int i=0; i<N; i++) {
			numbers[i] = Integer.parseInt(br.readLine());
		}

		initializeTree();

		StringBuilder ans = new StringBuilder();
		for(int i=0; i<M; i++) {
			String[] lr = br.readLine().split(" ");
			int l = Integer.parseInt(lr[0]) - 1;
			int r = Integer.parseInt(lr[1]) - 1;

			ans.append(findMinimum(0, N - 1, 1, l, r)).append("\n");
		}

		System.out.println(ans);
	}

	private static void initializeTree() {
		int height = (int)Math.ceil(Math.log(N) / Math.log(2)) + 1;
		tree = new int[(int)Math.pow(2, height)];
		Arrays.fill(tree, INF);

		buildTree(0, N - 1, 1);
	}

	private static int buildTree(int s, int e, int node) {
		if(s == e) return tree[node] = numbers[s];
		int m = (s + e) / 2;

		return tree[node] = Math.min(buildTree(s, m, node * 2), buildTree(m + 1, e, node * 2 + 1));
	}

	private static int findMinimum(int s, int e, int node, int l, int r) {
		if(s > r || e < l) return INF;
		if(s >= l && e <= r) return tree[node];

		int m = (s + e) / 2;
		return Math.min(findMinimum(s, m, node * 2, l, r), findMinimum(m + 1, e, node * 2 + 1, l, r));
	}
}
