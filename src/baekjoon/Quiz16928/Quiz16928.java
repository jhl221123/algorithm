package baekjoon.Quiz16928;

import java.util.*;
import java.io.*;

public class Quiz16928 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		HashMap<Integer, Integer> ladder = new HashMap<>(N);
		HashMap<Integer, Integer> snake = new HashMap<>(M);

		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			int s = Integer.parseInt(st.nextToken());
			int e = Integer.parseInt(st.nextToken());
			ladder.put(s, e);
		}
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			int s = Integer.parseInt(st.nextToken());
			int e = Integer.parseInt(st.nextToken());
			snake.put(s, e);
		}

		boolean[] visited = new boolean[101];
		ArrayDeque<Node> ad = new ArrayDeque<>();
		ad.addLast(new Node(1, 0));
		visited[1] = true;

		while(true) {
			Node cn = ad.removeFirst();

			if(cn.v == 100) {
				System.out.println(cn.cnt);
				break;
			}
			// 사다리, 뱀, 가장 큰 위치 모두 큐에 저장
			int max = cn.v;
			for(int i=1; i<=6; i++) {
				if(cn.v + i > 100) break;

				// 사다리 이동
				if(ladder.get(cn.v+i) != null) {
					int next = ladder.get(cn.v + i);
					// 이미 이동했다면 pass
					if(visited[next]) continue;
					visited[next] = true;
					ad.addLast(new Node(next, cn.cnt+1));
					continue;
				}

				// 뱀 이동
				if(snake.get(cn.v + i) != null) {
					int next = snake.get(cn.v + i);
					if(visited[next]) continue;
					visited[next] = true;
					ad.addLast(new Node(next, cn.cnt+1));
					continue;
				}

				// 주사위 칸 만큼 이동
				if(visited[cn.v + i]) continue;
				visited[cn.v + i] = true;
				ad.addLast(new Node(cn.v + i, cn.cnt+1));
			}
		}
	}

	static class Node {
		int v, cnt;

		public Node(int v, int cnt) {
			this.v = v;
			this.cnt = cnt;
		}
	}
}
