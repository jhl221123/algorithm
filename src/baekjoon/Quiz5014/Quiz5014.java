package baekjoon.Quiz5014;

import java.io.*;
import java.util.*;

/*
Silver1: 스타트링크 / [bfs]
*/
public class Quiz5014 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] info = br.readLine().split(" ");
		int F = Integer.parseInt(info[0]);
		int S = Integer.parseInt(info[1]);
		int G = Integer.parseInt(info[2]);
		int U = Integer.parseInt(info[3]);
		int D = Integer.parseInt(info[4]);

		ArrayDeque<int[]> ad = new ArrayDeque<>();
		ad.addLast(new int[] {S, 0});
		boolean[] visited = new boolean[F + 1];

		int min = -1;
		while(!ad.isEmpty()) {
			int[] node = ad.removeFirst();
			int cur = node[0];
			int count = node[1];
			if(visited[cur]) continue;
			visited[cur] = true;

			if(cur == G) {
				min = count;
				break;
			}

			int next = cur + U;
			if(next <= F) {
				ad.addLast(new int[] {next, count + 1});
			}

			next = cur - D;
			if(next > 0) {
				ad.addLast(new int[] {next, count + 1});
			}
		}

		if(min >= 0) System.out.println(min);
		else System.out.println("use the stairs");
	}
}
