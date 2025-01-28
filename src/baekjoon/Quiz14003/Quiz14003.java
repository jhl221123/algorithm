package baekjoon.Quiz14003;

import java.util.*;
import java.io.*;

public class Quiz14003 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[] numberSequence = new int[N];
		int[] parents = new int[N];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i=0; i<N; i++) {
			numberSequence[i] = Integer.parseInt(st.nextToken());
		}

		List<Node> lis = new ArrayList<>();
		lis.add(new Node(numberSequence[0], 0));
		parents[0] = 0;
		for(int i=1; i<N; i++) {
			if(lis.get(lis.size() - 1).v < numberSequence[i]) {
				parents[i] = lis.get(lis.size() - 1).idx;
				lis.add(new Node(numberSequence[i], i));
			} else {
				calculateLIS(numberSequence[i], i, lis, parents);
			}
		}

		System.out.println(lis.size());
		int next = lis.get(lis.size() - 1).idx;

		ArrayDeque<Integer> ad = new ArrayDeque<>();
		while(true) {
			ad.addFirst(numberSequence[next]);
			if(next == parents[next]) break;
			next = parents[next];
		}

		StringBuilder sb = new StringBuilder();
		while(!ad.isEmpty()) {
			sb.append(ad.removeFirst()).append(" ");
		}
		System.out.println(sb);
	}

	private static void calculateLIS(int number, int idx, List<Node> lis, int[] parents) {
		int l = 0;
		int r = lis.size();
		int pos = 0;

		while(l <= r) {
			int m = (l + r) / 2;
			if(lis.get(m).v < number) {
				l = m + 1;
			} else {
				pos = m;
				r = m -1;
			}
		}

		if(pos == 0) {
			lis.set(pos, new Node(number, idx));
			parents[idx] = idx;
			return;
		}

		lis.set(pos, new Node(number, idx));
		parents[idx] = lis.get(pos - 1).idx;
	}

	static class Node {
		int v, idx;

		public Node(int v, int idx) {
			this.v = v;
			this.idx = idx;
		}
	}
}
