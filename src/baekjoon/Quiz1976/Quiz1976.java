package baekjoon.Quiz1976;

import java.util.*;
import java.io.*;

public class Quiz1976 {
	private static int[] parents;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int M = Integer.parseInt(br.readLine());
		int[][] bridges = initializeBridge(N, br);

		initializeParents(N);
		calculatePossibleRoute(N, bridges);

		int[] route = getMovingRoute(br, M);
		printAnswer(route);
	}

	private static int[][] initializeBridge(int N, BufferedReader br) throws IOException {
		int[][] map = new int[N +1][N +1];
		StringTokenizer st;
		for(int i = 1; i<= N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 1; j<= N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		return map;
	}

	private static void initializeParents(int N) {
		parents = new int[N +1];
		for(int i = 1; i<= N; i++) {
			parents[i] = i;
		}
	}

	private static void calculatePossibleRoute(int N, int[][] map) {
		for(int i = 1; i<= N; i++) {
			int ip = find(i);
			for(int j = 1; j<= N; j++) {
				if(map[i][j] == 1) {
					int jp = find(j);
					union(ip, jp);
				}
			}
		}
	}

	private static int[] getMovingRoute(BufferedReader br, int M) throws IOException {
		StringTokenizer st;
		st = new StringTokenizer(br.readLine());
		int[] route = new int[M];
		for(int i = 0; i< M; i++) {
			route[i] = Integer.parseInt(st.nextToken());
		}
		return route;
	}

	private static void printAnswer(int[] route) {
		boolean isPossible = true;
		for(int i = 1; i< route.length; i++) {
			if(parents[route[i]] != parents[route[i-1]]) {
				isPossible = false;
				break;
			}
		}

		System.out.println(isPossible ? "YES" : "NO");
	}

	private static int find(int child) {
		if(child == parents[child]) return child;
		return parents[child] = find(parents[child]);
	}

	private static void union(int from, int to) {
		int fp = find(from);
		int tp = find(to);
		if(fp <= tp) {
			parents[tp] = fp;
			return;
		}
		parents[fp] = tp;
	}
}
