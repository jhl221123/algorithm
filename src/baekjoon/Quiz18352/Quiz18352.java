package baekjoon.Quiz18352;

import java.io.*;
import java.util.*;

/*
Silver2: 특정 거리의 도시 찾기 / [bfs]
*/
public class Quiz18352 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] nmkx = br.readLine().split(" ");
		int n = Integer.parseInt(nmkx[0]);
		int m = Integer.parseInt(nmkx[1]);
		int k = Integer.parseInt(nmkx[2]);
		int x = Integer.parseInt(nmkx[3]);

		List<List<Integer>> tree = new ArrayList<>();
		for(int i=0; i<=n; i++) {
			tree.add(new ArrayList<>());
		}

		for(int i=0; i<m; i++) {
			String[] edge = br.readLine().split(" ");
			int from = Integer.parseInt(edge[0]);
			int to = Integer.parseInt(edge[1]);
			tree.get(from).add(to);
		}

		int[] visited = new int[n + 1];
		ArrayDeque<int[]> ad = new ArrayDeque<>();
		ad.addLast(new int[] {x, 0});

		List<Integer> ans = new ArrayList<>();
		while(!ad.isEmpty()) {
			int[] node = ad.removeFirst();
			int num = node[0];
			int dist = node[1];
			if(visited[num] > 0) continue;
			visited[num] = dist;
			if(dist == k) {
				ans.add(num);
			}

			for(int next : tree.get(num)) {
				if(visited[next] > 0 || next == x) continue;
				ad.addLast(new int[] {next, dist + 1});
			}
		}

		if(ans.isEmpty()) {
			System.out.println(-1);
		} else {
			ans.sort(Comparator.comparingInt(o -> o));
			StringBuilder sb = new StringBuilder();
			for(int i=0; i<ans.size(); i++) {
				sb.append(ans.get(i)).append("\n");
			}
			System.out.print(sb);
		}
	}
}
