package programmers.kakao_winter_internship_2024;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DonutAndRodGraph {
	static List<List<Integer>> list;
	static int[] outDegree;
	static int[] inDegree;
	static int[] visited;
	static int edgeSize, createdEdge, donutCount, rodCount, eightCount;

	public static void main(String[] args) {
		int[][] arr = {{4, 11}, {1, 12}, {8, 3}, {12, 7}, {4, 2}, {7, 11}, {4, 8}, {9, 6}, {10, 11}, {6, 10}, {3, 5}, {11, 1}, {5, 3}, {11, 9}, {3, 8}}; // 4, 0, 1, 2
		// int[][] arr = {{2, 3}, {4, 3}, {1, 1}, {2, 1}}; // 2, 1, 1, 0
		int[] result = solution(arr);
		System.out.println(Arrays.toString(result));
	}
	public static int[] solution(int[][] edges) {
		edgeSize = getEdgeSize(edges);
		outDegree = new int[edgeSize+1];
		inDegree = new int[edgeSize+1];
		visited = new int[edgeSize+1];
		initGraph(edges);
		searchCreatedEdge();
		visited = new int[edgeSize+1];
		countGraphType();
		int[] answer = {createdEdge, donutCount, rodCount, eightCount};
		return answer;
	}
	private static int getEdgeSize(int[][] edges) {
		int max = 0;
		for(int[] edge : edges) {
			max = Math.max(max, edge[0]);
			max = Math.max(max, edge[1]);
		}
		return max;
	}

	private static void initGraph(int[][] edges) {
		list = new ArrayList<>();
		for(int i=0; i<=edgeSize; i++) {
			list.add(new ArrayList<>());
		}
		for(int[] edge : edges) {
			list.get(edge[0]).add(edge[1]);
			outDegree[edge[0]]++;
			inDegree[edge[1]]++;
		}
	}
	// 새로 생성된 edge는 진입 차수가 없으며, 최소 2개 이상의 진출 차수를 가진다.
	// 이 조건을 만족하는 edge는 유일하기 때문에 다음과 같이 수정했다.
	private static void searchCreatedEdge() {
		for(int i=1; i<=edgeSize; i++) {
			if(outDegree[i] >= 2 && inDegree[i] == 0) createdEdge = i;
		}
	}

	private static void countGraphType() {
		for(int edge : list.get(createdEdge)) {
			boolean donutFlag = false;
			boolean eightFlag = false;
			ArrayDeque<Integer> ad = new ArrayDeque<>();
			ad.addLast(edge);
			while(!ad.isEmpty()) {
				int ce = ad.removeFirst();
				visited[ce]++;
				if(outDegree[ce] >=2) eightFlag = true;
				if(visited[ce] >= 2) {
					if(!eightFlag) donutFlag = true;
					break;
				}
				for(int ne : list.get(ce)) {
					if(visited[ne] >= 2) continue;
					ad.addLast(ne);
				}
			}
			if(donutFlag) donutCount++;
			else if(eightFlag) eightCount++;
			else rodCount++;
		}
	}

	// 첫 시도 방법: 진출 차수가 가장 높은 것들 중 사이클이 없는 edge가 생성된 edge
	// 비효율 적인 이유: 진출 차수가 2일 때를 제외하면, 항상 새로 생성된 edge가 가장 큰 진출 차수를 가진다.
	// private static void searchCreatedEdge() {
	// 	int max = 0;
	// 	for(int degreeOfAdvancement : outDegree) {
	// 		max = Math.max(max, degreeOfAdvancement);
	// 	}
	// 	for(int candidate=1; candidate<=edgeSize; candidate++) {
	// 		if(outDegree[candidate] == max && isCreatedEdge(candidate)) {
	// 			createdEdge = candidate;
	// 			return;
	// 		}
	// 	}
	// 	System.out.println("Search Created Edge Error!");
	// }
	// private static boolean isCreatedEdge(int candidate) {
	// 	ArrayDeque<Integer> ad = new ArrayDeque<>();
	// 	for(Integer edge : list.get(candidate)) {
	// 		ad.addLast(edge);
	// 	}
	// 	while(!ad.isEmpty()) {
	// 		int ce = ad.removeFirst();
	// 		if(ce == candidate) return false;
	// 		visited[ce]++;
	// 		for(Integer ne : list.get(ce)) {
	// 			if(visited[ne] >= 2) continue;
	// 			ad.addLast(ne);
	// 		}
	// 	}
	// 	return true;
	// }
}
