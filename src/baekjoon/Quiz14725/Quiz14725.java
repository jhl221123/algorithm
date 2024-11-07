package baekjoon.Quiz14725;

import java.util.*;
import java.io.*;

public class Quiz14725 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		Node node = new Node();

		for(int i=0; i<N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int k = Integer.parseInt(st.nextToken());
			String[] strs = new String[k];
			for(int j=0; j<k; j++) {
				strs[j] = st.nextToken();
			}
			node.add(strs);
		}

		StringBuilder result = new StringBuilder();
		node.print(0, result);
		System.out.print(result);
	}

	static class Node {
		Map<String, Node> child = new HashMap<>();

		public void add(String[] strs) {
			Node parent = this;
			for(String str : strs) {
				parent.child.putIfAbsent(str, new Node());
				parent = parent.child.get(str);
			}
		}

		public void print(int depth, StringBuilder sb) {
			List<String> keys = new ArrayList<>(child.keySet());
			Collections.sort(keys);

			StringBuilder prefix = new StringBuilder();
			prefix.append("--".repeat(Math.max(0, depth)));

			for(String key : keys) {
				sb.append(prefix).append(key).append("\n");
				child.get(key).print(depth + 1, sb);
			}
		}
	}
}
