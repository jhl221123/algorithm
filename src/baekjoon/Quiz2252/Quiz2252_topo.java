package baekjoon.Quiz2252;

import java.util.*;
import java.io.*;

public class Quiz2252_topo {
	static int N;
	static int M;
	static List<Integer>[] tree;
	static boolean[] visit;
	static ArrayDeque<Integer> ad;
	static int[] topo;
	static StringBuilder sb = new StringBuilder();
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		tree = new ArrayList[N+1];
		visit = new boolean[N+1];
		ad = new ArrayDeque<>();
		// 트리로 자신보다 앞에 있는 학생(정점)을 관리
		for(int i=1; i<=N; i++) {
			tree[i] = new ArrayList<>();
		}
		topo = new int[N+1];
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			int p1 = Integer.parseInt(st.nextToken());
			int p2 = Integer.parseInt(st.nextToken());
			tree[p1].add(p2);
			topo[p2]++;
		}
		for(int i=1; i<=N; i++) {
			if(topo[i] == 0) ad.addLast(i);
		}
		// 순서 정렬
		bfs();
		System.out.println(sb);
	}
	private static void bfs() {
		// 해당 노드가 이미 방문처리 되었다면 탐색 후 맨 앞으로 이동
		while(!ad.isEmpty()) {
			int target = ad.removeFirst();
			visit[target] = true;
			sb.append(target).append(" ");
			for(int num : tree[target]) {
				//if(visit[num]) continue;
				topo[num]--;
				if(topo[num] == 0) ad.addLast(num);
			}
		}
	}
}
