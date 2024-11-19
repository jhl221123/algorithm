package baekjoon.Quiz6443;

import java.util.*;
import java.io.*;

public class Quiz6443 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();

		while(T-- > 0) {
			String str = br.readLine();
			List<Node> list = new ArrayList<>();
			toList(str, list);
			int[] tgt = new int[str.length()];
			dfs(list, 0, tgt, sb);
		}

		System.out.print(sb);
	}

	private static void dfs(List<Node> list, int depth, int[] tgt, StringBuilder sb) {
		if(depth == tgt.length) {
			StringBuilder tmp = new StringBuilder();
			for(int i = 0; i < tgt.length; i++) {
				tmp.append(list.get(tgt[i]).c);
			}
			tmp.append("\n");
			sb.append(tmp);
			return;
		}

		char prior = '0';
		for(int i=0; i<list.size(); i++) {
			if(list.get(i).cnt > 0) {
				if(prior == '0' || prior != list.get(i).c) {
					prior = list.get(i).c;
					list.get(i).cnt -= 1;
					tgt[depth] = i;
					dfs(list, depth + 1, tgt, sb);
					list.get(i).cnt += 1;
				} else if(list.get(i).cnt <= 0 || prior == list.get(i).c) continue;
			}
		}
	}

	private static void toList(String str, List<Node> list) {
		boolean[] visited = new boolean[str.length()];
		for(int i=0; i<str.length(); i++) {
			Node node = new Node(str.charAt(i), 0);
			for(int j=i; j<str.length(); j++) {
				if(visited[j]) continue;
				if(str.charAt(i) == str.charAt(j)) {
					visited[j] = true;
					node.cnt += 1;
				}
			}
			if(node.cnt > 0) list.add(node);
		}
		list.sort(Comparator.comparingInt(o -> o.c));
	}

	static class Node {
		char c;
		int cnt;

		public Node(char c, int cnt) {
			this.c = c;
			this.cnt = cnt;
		}
	}
}
