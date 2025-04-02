package baekjoon.Quiz10159;

import java.util.*;
import java.io.*;

/*
Gold4: 저울 / [floyd-warshall]
1. 각 노드에서 단방향으로 이동할 수 있는 모든 노드를 탐색한다.
2. 각 노드를 순회하며 전체 노드 수에서 단방향으로 도달할 수 있는 수를 뺀 값을 출력한다.
*/
public class Quiz10159 {

	private static final int INF = 200;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int M = Integer.parseInt(br.readLine());

		int[][] graph = new int[N + 1][N + 1];
		for(int i=0; i<=N; i++) {
			Arrays.fill(graph[i], INF);
		}

		for(int i=0; i<M; i++) {
			String[] ft = br.readLine().split(" ");
			int from = Integer.parseInt(ft[0]);
			int to = Integer.parseInt(ft[1]);

			graph[from][to] = 1;
		}

		for(int via=1; via<=N; via++) {
			for(int from=1; from<=N; from++) {
				for(int to=1; to<=N; to++) {
					graph[from][to] = Math.min(graph[from][to], graph[from][via] + graph[via][to]);
				}
			}
		}

		StringBuilder sb = new StringBuilder();
		for(int from=1; from<=N; from++) {
			int count = 0;

			for(int to=1; to<=N; to++) {
				if(graph[from][to] < INF || graph[to][from] < INF) count++;
			}

			sb.append(N - count - 1).append("\n");
		}

		System.out.print(sb);
	}
}
