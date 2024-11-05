package baekjoon.Quiz14002;

import java.io.*;
import java.util.*;

public class Quiz14002 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[] A = new int[N];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i=0; i<N; i++) {
			A[i] = Integer.parseInt(st.nextToken());
		}

		// 가장 긴 부분 수열 탐색
		Node[] nodes = new Node[N];
		for(int i=0; i<N; i++) {
			Node node = new Node(i, i, A[i], 1);
			nodes[i] = node;
			for(int j=i-1; j>=0; j--) {
				if(node.v > nodes[j].v && node.l <= nodes[j].l) {
					node.p = nodes[j].i;
					node.l = nodes[j].l + 1;
				}
			}
		}

		// 가장 긴 부분 수열 마지막 원소 탐색
		int idx = 0;
		int max = 0;
		for(int i=0; i<N; i++) {
			if(nodes[i].l > max) {
				idx = i;
				max = nodes[i].l;
			}
		}

		// 역추적
		StringBuilder sb = new StringBuilder();
		int next = idx;
		while(true) {
			sb.append(nodes[next].v).append(" ");
			if(nodes[next].p == nodes[next].i) break;
			next = nodes[next].p;
		}

		// 역순 출력
		String[] strs = sb.toString().split(" ");
		StringJoiner sj = new StringJoiner(" ");
		for(int i=strs.length-1; i>=0; i--) {
			sj.add(strs[i]);
		}
		System.out.println(strs.length);
		System.out.println(sj);
	}

	static class Node {
		int p, i, v, l;

		public Node(int p, int i, int v, int l) {
			this.p = p;
			this.i = i;
			this.v = v;
			this.l = l;
		}
	}
}
