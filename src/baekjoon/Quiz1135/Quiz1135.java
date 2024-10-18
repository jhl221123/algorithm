package baekjoon.Quiz1135;

import java.util.*;
import java.io.*;

public class Quiz1135 {
	static int N;
	static int[] parents;
	static int[] depths;
	static int[] seconds;
	static List<List<Integer>> childs = new ArrayList<>();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		parents = new int[N];
		depths = new int[N];
		seconds = new int[N];

		// parents 초기화
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i=0; i<N; i++) {
			parents[i] = Integer.parseInt(st.nextToken());
		}

		// childs 초기화
		for(int i=0; i<N; i++) {
			childs.add(new ArrayList<>());
		}

		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				if(parents[j] == i) childs.get(i).add(j);
			}
		}

		// depths 초기화
		int maxDepth = countDepth();
		execute(maxDepth);

		System.out.println(seconds[0]);
	}

	private static int countDepth() {
		int maxDepth = 0;
		ArrayDeque<Node> ad = new ArrayDeque<>();
		ad.addLast(new Node(0, 0));

		while(!ad.isEmpty()) {
			Node node = ad.removeFirst();
			maxDepth = Math.max(maxDepth, node.depth);
			for(int child : childs.get(node.v)) {
				depths[child] = node.depth + 1;
				ad.addLast(new Node(child, node.depth + 1));
			}
		}
		return maxDepth;
	}

	private static void execute(int maxDepth) {
		while(maxDepth >= 0) {
			for(int i=0; i<N; i++) {
				if(depths[i] == maxDepth) determineSeconds(i);
			}
			maxDepth--;
		}
	}

	private static void determineSeconds(int num) {
		int minSeconds = 0;
		int additional = 1;
		PriorityQueue<Integer> pq = new PriorityQueue<>((o1, o2) -> o2 - o1);

		for(int child : childs.get(num)) {
			pq.offer(seconds[child]);
		}

		while(!pq.isEmpty()) {
			minSeconds = Math.max(minSeconds, pq.poll() + additional++);
		}

		seconds[num] = minSeconds;
	}

	static class Node {
		int v, depth;

		public Node(int v, int depth) {
			this.v = v;
			this.depth = depth;
		}
	}
}
