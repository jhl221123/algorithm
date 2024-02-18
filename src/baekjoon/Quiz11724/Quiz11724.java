package baekjoon.Quiz11724;

import java.util.*;

public class Quiz11724 {
	static int N;
	static boolean[] visit;
	static List<Integer>[] graph;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		int M = sc.nextInt();
		visit = new boolean[N+1];
		graph = new List[N+1];
		for(int i=0; i<=N; i++) {
			graph[i] = new ArrayList<>();
		}
		for(int i=0; i<M; i++) {
			int a = sc.nextInt();
			int b = sc.nextInt();
			graph[a].add(b);
			graph[b].add(a);
		}
		int cnt = 0;
		while(!isComplete()) {
			cnt++;
			bfs(getSpot());
		}
		System.out.println(cnt);
	}
	private static void bfs(int idx) {
		ArrayDeque<Integer> ad = new ArrayDeque<>();
		ad.addLast(idx);
		visit[idx] = true;
		while(!ad.isEmpty()) {
			int point = ad.removeFirst();
			for(int spot : graph[point]) {
				if(!visit[spot]) {
					visit[spot] = true;
					ad.addLast(spot);
				}
			}
		}
	}
	private static boolean isComplete() {
		boolean result = true;
		for(int i=1; i<=N; i++) {
			if(!visit[i]) result = false;
		}
		return result;
	}
	private static int getSpot() {
		for(int i=1; i<=N; i++) {
			if(!visit[i]) return i;
		}
		return -1;
	}
}
