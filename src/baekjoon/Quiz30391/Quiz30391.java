package baekjoon.Quiz30391;

import java.util.*;
import java.io.*;

/*
Gold4: 트리의 지름? / [tree]
*/
public class Quiz30391 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] NK = br.readLine().split(" ");
		int N = Integer.parseInt(NK[0]);
		int K = Integer.parseInt(NK[1]);

		StringBuilder sb = new StringBuilder();
		ArrayDeque<Integer> ad = new ArrayDeque<>();
		ad.addLast(1);
		int child = 2;
		while(!ad.isEmpty() && child <= N) {
			int parent = ad.removeFirst();
			int edgeCount = parent == 1 ? K : K - 1;

			for(int i=0; i<edgeCount; i++) {
				if(child > N) {
					break;
				}
				sb.append(parent).append(" ").append(child).append("\n");
				ad.addLast(child);
				child++;
			}
		}

		System.out.print(sb);
	}
}
