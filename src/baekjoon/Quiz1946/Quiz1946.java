package baekjoon.Quiz1946;

import java.io.*;
import java.util.*;

/*
Silver1: 신입 사원 / [greedy]
*/
public class Quiz1946 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());

		StringBuilder sb = new StringBuilder();
		while(T-- > 0) {
			int N = Integer.parseInt(br.readLine());
			List<Node> list = new ArrayList<>();
			for(int i=0; i<N; i++) {
				String[] ab = br.readLine().split(" ");
				list.add(new Node(Integer.parseInt(ab[0]), Integer.parseInt(ab[1])));
			}
			list.sort(Comparator.comparingInt(o -> o.a));

			int count = 1;
			int min = list.get(0).b;
			for(int i=1; i<N; i++) {
				if(list.get(i).b < min) {
					count++;
					min = list.get(i).b;
				}
			}

			sb.append(count).append("\n");
		}

		System.out.print(sb);
	}

	static class Node {
		int a, b;

		public Node(int a, int b) {
			this.a = a;
			this.b = b;
		}
	}
}
