package baekjoon.Quiz5639;

import java.util.*;
import java.io.*;

/*
0. 가장 처음 원소는 루트 임으로 root.p = 100001로 시작한다.
1. 입력된 전위 순회 순서대로 다음 작업을 수행한다.
1-1. 현재 입력되는 수(v)가 스택의 가장 위 원소(e)보다 작다면 e.l = v;, v.p = e, v를 스택에 넣는다.
1-2. 현재 입력되는 수(v)가 스택의 가장 위 원소(e)보다 크다면, e를 제거한 후 다음 원소(n)이 n > v를 만족하는 지 확인한다.
1-2-1. n > v 라면 e.r = v, v.p = e, v를 스택에 넣는다.
1-2-2. n < v 라면 1-2를 반복한다.
2. dfs 로 루트부터 시작해서 후위 순회로 출력한다.
*/
public class Quiz5639 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		Node[] tree = new Node[1000001];

		String line = br.readLine();
		int root = Integer.parseInt(line);
		tree[root] = new Node(root, 1000001);

		ArrayDeque<Integer> ad = new ArrayDeque<>();
		ad.addFirst(1000001);
		ad.addFirst(root);

		while((line = br.readLine()) != null) {
			int v = Integer.parseInt(line);
			if(v < ad.getFirst()) {
				tree[ad.getFirst()].l = v;
				tree[v] = new Node(v, ad.getFirst());
				ad.addFirst(v);
				continue;
			}

			int p;
			while(true) {
				p = ad.removeFirst();
				if(ad.getFirst() > v) {
					break;
				}
			}

			tree[p].r = v;
			tree[v] = new Node(v, p);
			ad.addFirst(v);
		}

		StringBuilder sb = new StringBuilder();
		dfs(root, tree, sb);
		System.out.print(sb);
	}

	private static void dfs(int value, Node[] tree, StringBuilder sb) {
		if(tree[value].l == -1 && tree[value].r == -1) {
			sb.append(value).append("\n");
			return;
		}

		if(tree[value].l != -1) {
			dfs(tree[value].l, tree, sb);
		}

		if(tree[value].r != -1) {
			dfs(tree[value].r, tree, sb);
		}

		sb.append(value).append("\n");
	}

	static class Node {
		int v, p, l, r;

		public Node(int v, int p) {
			this.v = v;
			this.p = p;
			this.l = -1;
			this.r = -1;
		}
	}
}
