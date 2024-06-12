package baekjoon.Quiz11403;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

// 1. 출발지점에서 인접한 node를 큐에 넣는다.
// 2. 큐에서 node를 꺼내 인접한 큐를 확인한다. -> 방문한 node는 visited 처리한다.
// 3-1. 탐색 중 출발 지점의 node를 발견하면 1
// 3-2. 큐에 더 이상의 node가 존재하지 않을 때까지 출발 지점으로 돌아오지 못하면 0
public class Quiz11403 {
	static int N;
	static List<List<Integer>> list;
	static boolean[][] visited;
	static StringBuilder sb = new StringBuilder();
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		list = new ArrayList<>();
		for(int i=0; i<=N; i++) {
			list.add(new ArrayList<>());
		}
		for(int i=1; i<=N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int j=1; j<=N; j++) {
				int bridge = Integer.parseInt(st.nextToken());
				if(bridge == 1) list.get(i).add(j);
			}
		}
		for(int i=1; i<=N; i++) {
			for(int j=1; j<=N; j++) {
				visited = new boolean[N+1][N+1];
				if(isPossible(i, j)) sb.append(1).append(" ");
				else sb.append(0).append(" ");
			}
			sb.append("\n");
		}
		System.out.print(sb);
	}
	private static boolean isPossible(int s, int e) {
		ArrayDeque<Integer> ad = new ArrayDeque<>();
		ad.addLast(s);
		while(!ad.isEmpty()) {
			int c = ad.removeFirst();
			for(int next : list.get(c)) {
				if(visited[c][next]) continue;
				visited[c][next] = true;
				if(next == e) return true;
				ad.addLast(next);
			}
		}
		return false;
	}
}
