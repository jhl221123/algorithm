package baekjoon.Quiz20040;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Quiz20040 {

	private static int N, M;
	private static int[] parents;
	private static Turn[] turns;

	public static void main(String[] args) throws IOException {
		input();
		int endTurn = findEndTurn();

		System.out.println(endTurn);
	}

	private static int findEndTurn() {
		int endTurn = 0;
		for(int turn=1; turn<=M; turn++) {
			int from = turns[turn].from;
			int to = turns[turn].to;

			if(find(from) == find(to)) {
				endTurn = turn;
				break;
			}

			union(from, to);
		}

		return endTurn;
	}

	private static void input() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		parents = new int[N];
		for(int i=0; i<N; i++) {
			parents[i] = i;
		}

		turns = new Turn[M + 1];
		for(int turn=1; turn<=M; turn++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			turns[turn] = new Turn(from, to);
		}
	}

	private static int find(int num) {
		if(parents[num] == num) return num;
		return parents[num] = find(parents[num]);
	}

	private static void union(int from, int to) {
		int fp = find(from);
		int tp = find(to);

		if(fp < tp) {
			parents[tp] = fp;
			return;
		}

		parents[fp] = tp;
	}

	static class Turn {
		int from, to;

		Turn(int from, int to) {
			this.from = from;
			this.to = to;
		}
	}
}
