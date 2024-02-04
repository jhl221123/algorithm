package baekjoon.Quiz1260;

import java.util.*;
import java.io.*;

public class Quiz1260 {
	private static int N;
	private static int[][] arr;
	private static boolean[] visit;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int V = Integer.parseInt(st.nextToken());
		arr = new int[N+1][N+1];
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			arr[a][b] = 1;
			arr[b][a] = 1;
		}
		visit = new boolean[N+1];
		dfs(V);
		System.out.println();
		visit = new boolean[N+1];
		bfs(V);
	}
	private static void dfs(int start) {
		visit[start] = true;
		System.out.print(start + " ");
		for(int i=1; i<=N; i++) {
			if(arr[start][i] == 1 && !visit[i]) dfs(i);
		}
	}
	private static ArrayDeque<Integer> ad = new ArrayDeque<>();
	private static void bfs(int start) {
		visit[start] = true;
		ad.addLast(start);
		while(!ad.isEmpty()) {
			int target = ad.removeFirst();
			for(int i=1; i<=N; i++) {
				if(arr[target][i] == 1 && !visit[i]) {
					visit[i] = true;
					ad.addLast(i);
				}
			}
			System.out.print(target + " ");
		}
	}
}
