package programmers.basic.lv2;

import java.util.*;

public class DivideWires {
	public static void main(String[] args) {
		int result = solution(9, new int[][] {{1,3},{2,3},{3,4},{4,5},{4,6},{4,7},{7,8},{7,9}}); // 3
		System.out.println(result);
	}
	public static int solution(int n, int[][] wires) {
		List<List<Integer>> tree = getTree(wires);
		boolean[] visited = new boolean[tree.size()];
		int answer = 101;
		for(int from=1; from<=n; from++) {
			if(tree.get(from).isEmpty()) continue;
			visited[from] = true;
			for(int to : tree.get(from)) {
				if(visited[to]) continue;
				int nodeCnt = getNodeCnt(tree, from, to);
				answer = Math.min(answer, Math.abs(nodeCnt - (n - nodeCnt)));
			}
		}
		return answer;
	}
	private static List<List<Integer>> getTree(int[][] arr) {
		List<List<Integer>> tree = new ArrayList<>();
		for(int i=0; i<=arr.length+1; i++) {
			tree.add(new ArrayList<>());
		}
		for(int[] edge : arr) {
			tree.get(edge[0]).add(edge[1]);
			tree.get(edge[1]).add(edge[0]);
		}
		return tree;
	}
	private static int getNodeCnt(List<List<Integer>> tree, int from, int to) {
		ArrayDeque<Integer> ad = new ArrayDeque<>();
		boolean[] visited = new boolean[tree.size()];
		visited[to] = true;
		int cnt = 1;
		for(int ne : tree.get(to)) {
			if(ne == from) continue;
			ad.addLast(ne);
			visited[ne] = true;
			cnt++;
		}
		while(!ad.isEmpty()) {
			int ce = ad.removeFirst();
			if(tree.get(ce).isEmpty()) continue;
			for(int ne : tree.get(ce)) {
				if(visited[ne]) continue;
				visited[ne] = true;
				ad.addLast(ne);
				cnt++;
			}
		}
		return cnt;
	}
}
