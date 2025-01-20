package baekjoon.Quiz1717;

import java.util.*;
import java.io.*;

/*
1. 합집합 연산은 두 원소의 최상위 부모를 연결한다.
2. 두 원소의 최상위 부모가 같다면 동일한 집합에 속한 것으로 판단한다.
 */

public class Quiz1717 {
	private static int n, m;
	private static int[] parent;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		parent = new int[n+1];

		initialize();

		StringBuilder sb = new StringBuilder();
		for(int i=0; i<m; i++) {
			st = new StringTokenizer(br.readLine());
			int operator = Integer.parseInt(st.nextToken());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());

			if(operator == 0) {
				union(a, b);
			} else if(operator == 1) {
				int pa = find(a);
				int pb = find(b);
				if(pa == pb) sb.append("YES").append("\n");
				else sb.append("NO").append("\n");
			}
		}

		System.out.print(sb);
	}

	private static int find(int num) {
		if(parent[num] == num) return num;
		return parent[num] = find(parent[num]);
	}

	private static void union(int a, int b) {
		int pa = find(a);
		int pb = find(b);
		if(pa < pb) {
			parent[pb] = pa;
			return;
		}
		parent[pa] = pb;
	}

	private static void initialize() {
		for(int i=0; i<=n; i++) {
			parent[i] = i;
		}
	}
}
