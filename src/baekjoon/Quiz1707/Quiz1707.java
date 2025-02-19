package baekjoon.Quiz1707;

import java.util.*;
import java.io.*;

/*
Gold4: 이분 그래프 / [BFS]
1. 정점에 번호를 부여하며 bfs 탐색한다.
2. 다음 정점의 번호는 현재 정점 번호 + 1이다.
3. 다음 정점의 번호가 0보다 크다면 이미 방문한 정점으로 탐색을 멈춘다.
3-1. 다음 정점의 번호가 현재 번호와 같다면 이분 그래프가 될 수 없다.
*/
public class Quiz1707 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int K = Integer.parseInt(br.readLine());
		StringBuilder answer = new StringBuilder();

		while(K-- > 0) {
			String[] VE = br.readLine().split(" ");
			int V = Integer.parseInt(VE[0]);
			int E = Integer.parseInt(VE[1]);

			List<Set<Integer>> graph = new ArrayList<>();
			for(int i=0; i<=V; i++) {
				graph.add(new HashSet<>());
			}

			for(int i=0; i<E; i++) {
				String[] uv = br.readLine().split(" ");
				int u = Integer.parseInt(uv[0]);
				int v = Integer.parseInt(uv[1]);
				graph.get(u).add(v);
				graph.get(v).add(u);
			}

			int[] visited = new int[V + 1];
			boolean possible = true;
			bk: for(int i=1; i<=V; i++) {
				if(visited[i] <= 0) {
					ArrayDeque<int[]> ad = new ArrayDeque<>();
					ad.addLast(new int[] {i, 1});

					while(!ad.isEmpty()) {
						int[] node = ad.removeFirst();
						int current = node[0];
						int count = node[1];

						if(visited[current] > 0) {
							continue;
						}
						visited[current] = count;

						for(int next : graph.get(current)) {
							if(visited[next] == count) {
								possible = false;
								break bk;
							}

							if(visited[next] > 0) {
								continue;
							}

							ad.addLast(new int[] {next, count + 1});
						}
					}
				}
			}

			if(possible) {
				answer.append("YES").append("\n");
			} else {
				answer.append("NO").append("\n");
			}
		}

		System.out.print(answer);
	}
}
