package baekjoon.Quiz14567;

import java.util.*;
import java.io.*;

/*
Gold5: 선수과목 / [topological-sort, dp]
1. 위상 정렬을 활용해 선수 과목을 관리한다.
2. 선수 과목을 수행할 때마다 다음 과목의 필요 학기수를 갱신한다.
*/
public class Quiz14567 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] nm = br.readLine().split(" ");
		int N = Integer.parseInt(nm[0]);
		int M = Integer.parseInt(nm[1]);
		int[] topo = new int[N + 1];
		int[] needTerms = new int[N + 1];

		List<List<Integer>> graph = new ArrayList<>();
		for(int i=0; i<=N; i++) {
			graph.add(new ArrayList<>());
		}

		for(int i=0; i<M; i++) {
			String[] pn = br.readLine().split(" ");
			int prev = Integer.parseInt(pn[0]);
			int next = Integer.parseInt(pn[1]);

			graph.get(prev).add(next);
			topo[next]++;
		}

		boolean[] visited = new boolean[N + 1];
		while(true) {
			ArrayDeque<Integer> ad = new ArrayDeque<>();
			for(int i=1; i<=N; i++) {
				if(!visited[i] && topo[i] == 0) {
					visited[i] = true;
					ad.addLast(i);
				}
			}

			if(ad.isEmpty()) {
				break;
			}

			while(!ad.isEmpty()) {
				int prev = ad.removeFirst();

				for(int next : graph.get(prev)) {
					topo[next]--;
					needTerms[next] = Math.max(needTerms[next], needTerms[prev] + 1);
				}
			}
		}

		StringBuilder sb = new StringBuilder();
		for(int i=1; i<=N; i++) {
			sb.append(needTerms[i] + 1).append(" ");
		}

		System.out.println(sb);
	}
}
