package baekjoon.Quiz10825;

import java.io.*;
import java.util.*;

/*
Silver4: 국영수 / [sort]
*/
public class Quiz10825 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		List<Node> list = new ArrayList<>();
		for(int i=0; i<N; i++) {
			String[] info = br.readLine().split(" ");
			list.add(new Node(info[0], Integer.parseInt(info[1]), Integer.parseInt(info[2]), Integer.parseInt(info[3])));
		}
		list.sort((o1, o2) -> {
			if (o1.kor == o2.kor) {
				if (o1.eng == o2.eng) {
					if (o1.mat == o2.mat) {
						return o1.name.compareTo(o2.name);
					}
					return o2.mat - o1.mat;
				}
				return o1.eng - o2.eng;
			}
			return o2.kor - o1.kor;
		});

		StringBuilder sb = new StringBuilder();
		for(int i=0; i<N; i++) {
			sb.append(list.get(i).name).append("\n");
		}
		System.out.print(sb);
	}

	static class Node {
		String name;
		int kor;
		int eng;
		int mat;

		public Node(String name, int kor, int eng, int mat) {
			this.name = name;
			this.kor = kor;
			this.eng = eng;
			this.mat = mat;
		}
	}
}
