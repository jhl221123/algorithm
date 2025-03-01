package baekjoon.Quiz12852;

import java.util.*;
import java.io.*;

/*
Gold5: 1로 만들기 2 / [bfs, dfs, back-tracking, dp]
1. 입력된 수를 세 가지 연산을 적용하고 우선 순위 큐에 넣는다.
2. 가장 작은 수를 매번 뽑아서 세 가지 연산을 적용한다.
3. 뽑은 수가 1이라면 종료 후 path를 출력한다.
*/
public class Quiz12852 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		PriorityQueue<Number> pq = new PriorityQueue<>((o1, o2) -> {
			if(o1.count == o2.count) {
				return o1.value - o2.value;
			}
			return o1.count - o2.count;
		});
		pq.offer(new Number(n, 0, new StringBuilder().append(n).append(" ")));

		int[] visited = new int[n + 1];
		Arrays.fill(visited, 2_000_000);
		while(!pq.isEmpty()) {
			Number number = pq.poll();
			int v = number.value;

			if(visited[v] <= number.count) continue;
			visited[v] = number.count;

			if(v == 1) {
				System.out.println(number.count);
				System.out.println(number.path);
				return;
			}

			if(v % 3 == 0) {
				int nv = v / 3;
				pq.offer(new Number(nv, number.count + 1, new StringBuilder(number.path).append(nv).append(" ")));
			}

			if(v % 2 == 0) {
				int nv = v / 2;
				pq.offer(new Number(nv, number.count + 1, new StringBuilder(number.path).append(nv).append(" ")));
			}

			int nv = v - 1;
			pq.offer(new Number(nv, number.count + 1, new StringBuilder(number.path).append(nv).append(" ")));
		}
	}

	static class Number {
		int value;
		int count;
		StringBuilder path;

		public Number(int value, int count, StringBuilder path) {
			this.value = value;
			this.count = count;
			this.path = path;
		}
	}
}
