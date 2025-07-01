package baekjoon.Quiz4803;

import java.util.*;
import java.io.*;

/*
Gold4: 트리 / [tree, bfs]
*/
public class Quiz4803 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		int testCaseNo = 1;
		while(true) {
			String[] nm = br.readLine().split(" ");
			int n = Integer.parseInt(nm[0]);
			int m = Integer.parseInt(nm[1]);
			if(n == 0 && m == 0) break;
			List<List<Integer>> tree = new ArrayList<>();
			for(int i=0; i<=n; i++) {
				tree.add(new ArrayList<>());
			}

			for(int i=0; i<m; i++) {
				String[] edge = br.readLine().split(" ");
				int from = Integer.parseInt(edge[0]);
				int to = Integer.parseInt(edge[1]);
				tree.get(from).add(to);
				tree.get(to).add(from);
			}

			int treeCount = 0;
			boolean[] visited = new boolean[n + 1];
			for(int i=1; i<=n; i++) {
				if(visited[i]) continue;
				int nodeCount = 0;
				int edgeCount = 0;
				ArrayDeque<Integer> ad = new ArrayDeque<>();
				ad.addLast(i);
				while(!ad.isEmpty()) {
					int node = ad.removeFirst();
					if(visited[node]) continue;
					visited[node] = true;
					nodeCount++;

					for(int next : tree.get(node)) {
						edgeCount++;
						if(visited[next]) continue;
						ad.addLast(next);
					}
				}
				if(edgeCount == (nodeCount - 1) * 2) treeCount++;
			}

			sb.append("Case").append(" ").append(testCaseNo++);
			if(treeCount == 0) {
				sb.append(": No trees.");
			} else if(treeCount == 1) {
				sb.append(": There is one tree.");
			} else {
				sb.append(": A forest of ").append(treeCount).append(" trees.");
			}
			sb.append("\n");
		}
		System.out.print(sb);
	}
}
