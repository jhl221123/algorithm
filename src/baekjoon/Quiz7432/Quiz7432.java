package baekjoon.Quiz7432;

import java.io.*;
import java.util.*;

public class Quiz7432 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		Node node = new Node();
		for(int i=0; i<N; i++) {
			node.insert(br.readLine());
		}

		node.print(0);
	}

	static class Node {
		Map<String, Node> child = new HashMap<>();

		private void insert(String str) {
			Node p = this;
			String[] arr = str.split("\\\\");
			for(int i=0; i<arr.length; i++) {
				if (!p.child.containsKey(arr[i])) {
					p.child.put(arr[i], new Node());
				}
				p = p.child.get(arr[i]);
			}
		}

		private void print(int depth) {
			List<String> list = new ArrayList<>(child.keySet());
			Collections.sort(list);
			for(String key : list) {
				String sb = " ".repeat(Math.max(0, depth)) + key;
				System.out.println(sb);
				child.get(key).print(depth + 1);
			}
		}
	}
}
