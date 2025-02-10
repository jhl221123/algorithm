package baekjoon.Quiz1516;

import java.io.*;
import java.util.*;

public class Quiz1516 {

	private static int N;
	private static List<List<Integer>> dag;
	private static int[] topology;
	private static int[] times;
	private static int[] increments;

	public static void main(String[] args) throws IOException {
		input();
		calculateBuildingTimes();
		printBuildingTimes();
	}

	private static void printBuildingTimes() {
		for(int i=1; i<=N; i++) {
			System.out.println(times[i]);
		}
	}

	private static void calculateBuildingTimes() {
		ArrayDeque<Integer> queue = new ArrayDeque<>();
		for(int i=1; i<=N; i++) {
			if(topology[i] == 0) {
				queue.addLast(i);
			}
		}

		while(!queue.isEmpty()) {
			int cur = queue.removeFirst();

			for(int j=0; j<dag.get(cur).size(); j++) {
				int next = dag.get(cur).get(j);
				topology[next]--;
				increments[next] = Math.max(increments[next], times[cur]);

				if(topology[next] == 0) {
					times[next] += increments[next];
					queue.addLast(next);
				}
			}
		}
	}

	private static void input() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		topology = new int[N + 1];
		increments = new int[N + 1];
		times = new int[N + 1];
		dag = new ArrayList<>();

		for(int i=0; i<=N; i++) {
			dag.add(new ArrayList<>());
		}

		for(int i=1; i<=N; i++) {
			String[] tokens = br.readLine().split(" ");
			topology[i] = tokens.length - 2;
			for(int j=0; j<tokens.length; j++) {
				int token = Integer.parseInt(tokens[j]);
				if(token == -1) break;

				if(j==0) {
					times[i] = token;
					continue;
				}
				dag.get(token).add(i);
			}
		}
	}
}
