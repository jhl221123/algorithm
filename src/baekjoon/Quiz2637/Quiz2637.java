package baekjoon.Quiz2637;

import java.util.*;
import java.io.*;

/*
Gold2: 장난감 조립 / [dp, data structure]

dfs
1. 완제품 번호부터 탐색
2. 하위 노드를 순차 탐색하며 초기화 여부 확인
3. 초기화 되어 있지 않다면, dfs
3-1. 하위 노드가 없다면 기본배열로 간주
3-2. 기본 배열의 노드 번호 인덱스++
3-3. 초기화 플래그 온
4. 초기화 되어 있다면, 하위 노드 기본 배열 × 계수 를 현재 노드 기본 배열에 반영

구조 초기화
1. 1~N 까지 노드 리스트 생성
2. 입력에 따라 하위 노드 및 계수 추가
3. 모두 추가되었다면 dfs 시작
*/
public class Quiz2637 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int M = Integer.parseInt(br.readLine());
		List<Node> graph = new ArrayList<>();
		for(int i=0; i<=N; i++) {
			graph.add(new Node(i));
		}

		for(int i=0; i<M; i++) {
			String[] info = br.readLine().split(" ");
			int X = Integer.parseInt(info[0]);
			int Y = Integer.parseInt(info[1]);
			int K = Integer.parseInt(info[2]);

			graph.get(X).addNode(graph.get(Y));
			graph.get(X).addWeight(K);
		}

		dfs(graph, N);
		int[] bcs = graph.get(N).basicNodeCounts;
		StringBuilder sb = new StringBuilder();
		for(int i=1; i<=100; i++) {
			if(bcs[i] == 0) continue;
			sb.append(i).append(" ").append(bcs[i]).append("\n");
		}
		System.out.print(sb);
	}

	private static void dfs(List<Node> graph, int no) {
		Node current = graph.get(no);
		List<Node> children = current.children;
		if(children.isEmpty()) {
			current.basicNodeCounts[current.no]++;
			current.isInit = true;
			return;
		}

		for(int i=0; i<children.size(); i++) {
			Node child = children.get(i);
			int weight = current.weights.get(i);
			if(!child.isInit) {
				dfs(graph, child.no);
			}
			for(int j=1; j<=100; j++) {
				if(child.basicNodeCounts[j] == 0) continue;
				current.basicNodeCounts[j] += (child.basicNodeCounts[j] * weight);
			}
		}
		current.isInit = true;
	}

	/*
		노드
		1. 번호
		2. 하위 노드 리스트
		2-1. 하위 노드 계수 리스트
		3. 초기화 여부
		4. 기본 부품 개수 배열
	*/
	static class Node {
		int no;
		List<Node> children;
		List<Integer> weights;
		boolean isInit;
		int[] basicNodeCounts;

		public Node(int no) {
			this.no = no;
			this.children = new ArrayList<>();
			this.weights = new ArrayList<>();
			this.isInit = false;
			this.basicNodeCounts = new int[101];
		}

		public void addNode(Node child) {
			this.children.add(child);
		}

		public void addWeight(int weight) {
			this.weights.add(weight);
		}
	}
}
