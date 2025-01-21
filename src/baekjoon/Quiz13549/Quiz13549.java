package baekjoon.Quiz13549;

import java.util.*;
import java.io.*;

/*
1. 현재 위치에서 * 2, + 1, - 1 이동하고 큐에 넣는다.
2. 이전에 도착한 곳이라도 시간이 더 작다면 큐에 넣는다.
2-1. 순차적으로 최단 경로가 보장되는 다익스트라와 달리 0-1 BFS는 뒷순서에서 최단 경로가 변경될 수 있다.
3. 2번 과정으로 인해 + 1 보다 - 1을 먼저 큐에 넣어줘야 한다.
3-1. 동일한 pos 라도 - 1로 처리한 값이 + 1로 처리한 값보다 * 2를 많이 사용할 수 있기 때문에 time이 더 작을 수 있다.
 */

public class Quiz13549 {

	private static final int INF = 1_000_000;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());

		int[] visited = new int[200_001];
		Arrays.fill(visited, INF);

		ArrayDeque<Position> pq = new ArrayDeque<>();
		pq.offer(new Position(N, 0));
		visited[N] = 1;

		if(N > K) {
			System.out.println(N - K);
			return;
		}

		while(!pq.isEmpty()) {
			for(int i=0; i<pq.size(); i++) {
				Position cur = pq.poll();
				if(cur.pos < 0) continue;
				if(cur.pos == K) {
					System.out.println(cur.time);
					return;
				}
				if(visited[cur.pos] <= cur.time) continue;
				visited[cur.pos] = cur.time;

				if(cur.pos * 2 <= 200001) {
					Position next = Position.copyOf(cur);
					next.moveDouble();

					pq.offer(next);
				}

				if(cur.pos - 1 >= 0) {
					Position next = Position.copyOf(cur);
					next.moveMinus();

					pq.offer(next);
				}

				if(cur.pos + 1 <= K) {
					Position next = Position.copyOf(cur);
					next.movePlus();

					pq.offer(next);
				}
			}
		}
	}

	static class Position {
		int pos;
		int time;

		public Position(int pos, int time) {
			this.pos = pos;
			this.time = time;
		}

		public static Position copyOf(Position position) {
			return new Position(position.pos, position.time);
		}

		public void moveDouble() {
			this.pos = this.pos * 2;
		}

		public void movePlus() {
			this.pos = this.pos + 1;
			this.time = this.time + 1;
		}

		public void moveMinus() {
			this.pos = this.pos - 1;
			this.time = this.time + 1;
		}
	}
}
