package baekjoon.Quiz1068;

import java.util.*;
import java.io.*;

/*
1. 인접 리스트로 자식 노드를 관리한다.
2. 리스트를 순회하며 리프 노드를 탐색한다.
3. 리스트를 활용해 삭제되는 노드를 관리한다.
4. 삭제되지 않은 리프노드 개수를 확인한다.
*/

public class Quiz1068 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		List<List<Integer>> list = initializeTree(N, br);
		boolean[] isDeleted = checkDeletedNode(N, br, list);
		int count = countLeafNode(N, isDeleted, list);

		System.out.println(count);
	}

	private static int countLeafNode(int N, boolean[] isDeleted, List<List<Integer>> list) {
		int count = 0;
		for(int i = 0; i< N; i++) {
			if(isDeleted[i]) continue;
			if(list.get(i).isEmpty()) {
				count++;
				continue;
			}

			boolean allDeleted = true;
			for(int child : list.get(i)) {
				if (!isDeleted[child]) {
					allDeleted = false;
					break;
				}
			}

			if(allDeleted) count++;
		}

		return count;
	}

	private static boolean[] checkDeletedNode(int N, BufferedReader br, List<List<Integer>> list) throws IOException {
		boolean[] isDeleted = new boolean[N];
		int removeNode = Integer.parseInt(br.readLine());
		ArrayDeque<Integer> ad = new ArrayDeque<>();
		ad.addLast(removeNode);

		while(!ad.isEmpty()) {
			int node = ad.removeFirst();
			if(isDeleted[node]) continue;
			isDeleted[node] = true;

			for(int next : list.get(node)) {
				if(isDeleted[next]) continue;
				ad.addLast(next);
			}
		}

		return isDeleted;
	}

	private static List<List<Integer>> initializeTree(int N, BufferedReader br) throws IOException {
		List<List<Integer>> list = new ArrayList<>();
		for(int i = 0; i< N; i++) {
			list.add(new ArrayList<>());
		}

		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i = 0; i< N; i++) {
			int parent = Integer.parseInt(st.nextToken());
			if(parent == -1) continue;
			list.get(parent).add(i);
		}

		return list;
	}
}
