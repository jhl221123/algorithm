package baekjoon.Quiz2357;

import java.util.*;
import java.io.*;

public class Quiz2357 {
	static int N, M;
	static int[] arr;
	static Node[] tree;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		arr = new int[N+1];
		tree = new Node[4 * N];
		for(int i=1; i<=N; i++) {
			arr[i] = Integer.parseInt(br.readLine());
		}
		init(1, 1, N);
		StringBuilder sb =  new StringBuilder();
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			int s = Integer.parseInt(st.nextToken());
			int e = Integer.parseInt(st.nextToken());
			Node node = query(1, 1, N, s, e);
			sb.append(node.min).append(" ").append(node.max).append("\n");
		}
		System.out.print(sb);
	}

	private static Node init(int idx, int l, int r) {
		if(l == r) return tree[idx] = new Node(arr[l], arr[r]);
		int mid = (l + r) / 2;
		Node left = init(idx * 2, l, mid);
		Node right = init(idx * 2 + 1, mid + 1, r);
		return tree[idx] = new Node(Math.min(left.min, right.min), Math.max(left.max, right.max));
	}

	private static Node query(int idx, int l, int r, int s, int e) {
		if(l >= s && r <= e) return tree[idx];
		if(l > e || r < s) return new Node(Long.MAX_VALUE, Long.MIN_VALUE);
		int mid = (l + r) / 2;
		Node left = query(idx * 2, l, mid, s, e);
		Node right = query(idx * 2 + 1, mid + 1, r, s, e);
		return new Node(Math.min(left.min, right.min), Math.max(left.max, right.max));
	}

	static class Node {
		long min, max;

		public Node(long min, long max) {
			this.min = min;
			this.max = max;
		}
	}
}
