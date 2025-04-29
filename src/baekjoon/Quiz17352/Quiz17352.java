package baekjoon.Quiz17352;

import java.util.*;
import java.io.*;

/*
Gold5: 여러분의 다리가 되어 드리겠습니다! / [bfs, union-find]
1. 하나의 노드에서 출발해 이동 가능한 모든 노드를 탐색한다.
2. 탐색한 노드중 하나와 탐색하지 않는 노드 하나를 출력한다.
3. N이 2라면 1 2 를 출력한다.
*/
public class Quiz17352 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());

		if(N == 2) {
			System.out.println("1 2");
			return;
		}

		List<List<Integer>> list = new ArrayList<>();
		for(int i=0; i<=N; i++) {
			list.add(new ArrayList<>());
		}

		int startNode = 0;
		for(int i=0; i<N-2; i++) {
			String[] ft = br.readLine().split(" ");
			int from = Integer.parseInt(ft[0]);
			int to = Integer.parseInt(ft[1]);
			startNode = from;

			list.get(from).add(to);
			list.get(to).add(from);
		}

		boolean[] visited = new boolean[N + 1];
		ArrayDeque<Integer> ad = new ArrayDeque<>();
		ad.addLast(startNode);

		while(!ad.isEmpty()) {
			int node = ad.removeFirst();
			if(visited[node]) continue;
			visited[node] = true;

			for(int next : list.get(node)) {
				if(visited[next]) continue;
				ad.addLast(next);
			}
		}

		for(int i=1; i<=N; i++) {
			if(!visited[i]) {
				System.out.println(startNode + " " + i);
				break;
			}
		}
	}
}
