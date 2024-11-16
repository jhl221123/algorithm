package baekjoon.Quiz16719;

import java.io.*;
import java.util.*;

public class Quiz16719 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String str = br.readLine();
		ArrayDeque<Node> ad = new ArrayDeque<>();
		boolean[] used = new boolean[str.length()];
		StringBuilder sb = new StringBuilder();

		for(int i=0; i<str.length(); i++) {
			int next = -1;
			String min = "";
			for(int j=0; j<str.length(); j++) {
				if(used[j]) continue;
				String temp = toString(ad, j, str.charAt(j));
				if(min.isEmpty() || min.compareTo(temp) > 0) {
					next = j;
					min = temp;
				}
			}
			used[next] = true;
			insert(ad, next, str.charAt(next));
			sb.append(min).append("\n");
		}

		System.out.print(sb);
	}

	private static String toString(ArrayDeque<Node> ad, int idx, char target) {
		StringBuilder sb = new StringBuilder();
		boolean used = false;
		int l = ad.size();

		for(int i=0; i<l; i++) {
			Node node = ad.removeFirst();
			if(!used && node.idx > idx) {
				sb.append(target);
				used = true;
			}
			sb.append(node.c);
			ad.addLast(node);
		}
		if(!used) sb.append(target);
		return sb.toString();
	}

	private static void insert(ArrayDeque<Node> ad, int idx, char target) {
		boolean used = false;
		int l = ad.size();

		for(int i=0; i<l; i++) {
			Node node = ad.removeFirst();
			if(!used && node.idx > idx) {
				ad.addLast(new Node(target, idx));
				used = true;
			}
			ad.addLast(node);
		}
		if(!used) ad.addLast(new Node(target, idx));
	}

	static class Node {
		char c;
		int idx;

		public Node(char c, int idx) {
			this.c = c;
			this.idx = idx;
		}
	}
}
