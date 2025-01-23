package baekjoon.Quiz13913;

import java.util.*;
import java.io.*;

/*
1. 세 가지 방법으로 이동시키면서 bfs 한다.
2. 이미 방문했다면 continue;
3. 도착하면 history 출력
*/
public class Quiz13913 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int start = Integer.parseInt(st.nextToken());
		int end = Integer.parseInt(st.nextToken());

		if(start > end) {
			earlyPrint(start, end);
			return;
		}

		int[] visited = new int[end * 2 + 1];
		ArrayDeque<Mover> ad = new ArrayDeque<>();
		ad.addLast(new Mover(start, 0, new StringBuilder().append(start).append(" ")));

		while(!ad.isEmpty()) {
			Mover mover = ad.removeFirst();
			if(visited[mover.pos] > 0) continue;

			visited[mover.pos] = mover.time;

			if(mover.pos == end) {
				System.out.println(mover.time);
				System.out.println(mover.path);
				return;
			}

			if(mover.pos * 2 <= visited.length && visited[mover.pos * 2] <= 0) {
				Mover next = mover.copy();
				next.teleport();
				ad.addLast(next);
			}

			if(mover.pos - 1 >= 0 && visited[mover.pos - 1] <= 0) {
				Mover next = mover.copy();
				next.minus();
				ad.addLast(next);
			}

			if(mover.pos + 1 <= visited.length && visited[mover.pos + 1] <= 0) {
				Mover next = mover.copy();
				next.plus();
				ad.addLast(next);
			}
		}
	}

	private static void earlyPrint(int start, int end) {
		StringBuilder sb = new StringBuilder();
		sb.append(start - end).append("\n");
		for(int s = start; s>= end; s--) {
			sb.append(s).append(" ");
		}
		System.out.println(sb);
	}

	static class Mover {
		int pos, time;
		StringBuilder path;

		public Mover(int pos, int time, StringBuilder path) {
			this.pos = pos;
			this.time = time;
			this.path = path;
		}

		public void plus() {
			this.pos += 1;
			this.time += 1;
			this.path.append(pos).append(" ");
		}

		public void minus() {
			this.pos -= 1;
			this.time += 1;
			this.path.append(pos).append(" ");
		}

		public void teleport() {
			this.pos *= 2;
			this.time += 1;
			this.path.append(pos).append(" ");
		}

		public Mover copy() {
			return new Mover(this.pos, this.time, new StringBuilder(this.path));
		}
	}
}
