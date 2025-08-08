package baekjoon.Quiz1991;

import java.io.*;
import java.util.*;

/*
Silver1: 트리 순회 / [tree, dfs]
*/
public class Quiz1991 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		List<List<Integer>> tree = new ArrayList<>();
		for(int i=0; i<26; i++) {
			tree.add(new ArrayList<>());
		}

		for(int i=0; i<N; i++) {
			String[] info = br.readLine().split(" ");
			int node = info[0].charAt(0) - 'A';
			int left = ".".equals(info[1]) ? 0 : info[1].charAt(0) - 'A';
			int right = ".".equals(info[2]) ? 0 : info[2].charAt(0) - 'A';
			tree.get(node).add(left);
			tree.get(node).add(right);
		}

		StringBuilder sb = new StringBuilder();
		dfs1(0, tree, sb);
		sb.append("\n");
		dfs2(0, tree, sb);
		sb.append("\n");
		dfs3(0, tree, sb);

		System.out.println(sb);
	}

	private static void dfs1(int node, List<List<Integer>> tree, StringBuilder sb) {
		int left = tree.get(node).get(0);
		int right = tree.get(node).get(1);

		sb.append((char)('A' + node));
		if(tree.get(node).get(0) != 0) {
			dfs1(left, tree, sb);
		}
		if(tree.get(node).get(1) != 0) {
			dfs1(right, tree, sb);
		}
	}

	private static void dfs2(int node, List<List<Integer>> tree, StringBuilder sb) {
		int left = tree.get(node).get(0);
		int right = tree.get(node).get(1);

		if(tree.get(node).get(0) != 0) {
			dfs2(left, tree, sb);
		}
		sb.append((char)('A' + node));
		if(tree.get(node).get(1) != 0) {
			dfs2(right, tree, sb);
		}
	}

	private static void dfs3(int node, List<List<Integer>> tree, StringBuilder sb) {
		int left = tree.get(node).get(0);
		int right = tree.get(node).get(1);

		if(tree.get(node).get(0) != 0) {
			dfs3(left, tree, sb);
		}
		if(tree.get(node).get(1) != 0) {
			dfs3(right, tree, sb);
		}
		sb.append((char)('A' + node));
	}
}
