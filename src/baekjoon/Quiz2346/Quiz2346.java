package baekjoon.Quiz2346;

import java.io.*;
import java.util.*;

/*
Silver3: 풍선 터뜨리기 / [data structure]
*/
public class Quiz2346 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		String[] values = br.readLine().split(" ");
		ArrayDeque<Node> ad = new ArrayDeque<>();
		for(int i=1; i<=N; i++) {
			int value = Integer.parseInt(values[i-1]);
			ad.addLast(new Node(i, value));
		}

		StringBuilder sb = new StringBuilder();
		while(true) {
			Node node = ad.removeFirst();
			sb.append(node.num).append(" ");

			if(ad.isEmpty()) break;

			if(node.value > 0) {
				for(int i=1; i<node.value; i++) {
					ad.addLast(ad.removeFirst());
				}
			} else {
				node.value *= -1;
				for(int i=0; i<node.value; i++) {
					ad.addFirst(ad.removeLast());
				}
			}
		}

		System.out.println(sb);
	}

	static class Node {
		int num;
		int value;

		public Node(int num, int value) {
			this.num = num;
			this.value = value;
		}
	}
}
