package baekjoon.Quiz2660;

import java.util.*;
import java.io.*;

/*
Gold5: 회장뽑기 / [shortest path, floyd-warshall]
1. 각 정점에서 나머지 정점으로 이동할 수 있는 최단 경로를 구한다.
2. 각 정점에서 갈 수 있는 정점 중, 가장 긴 경로를 구한다.
3. 2에서 구한 값이 가장 작은 정점들을 출력한다.
*/
public class Quiz2660 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[][] network = new int[N + 1][N + 1];
		for(int i=1; i<=N; i++) {
			Arrays.fill(network[i], 100);
		}

		while(true) {
			String[] edge = br.readLine().split(" ");
			int v1 = Integer.parseInt(edge[0]);
			int v2 = Integer.parseInt(edge[1]);
			if(v1 == -1 && v2 == -1) {
				break;
			}

			network[v1][v2] = 1;
			network[v2][v1] = 1;
		}

		for(int via = 1; via <= N; via++) {
			for(int from = 1; from <= N; from++) {
				for(int to = 1; to <= N; to++) {
					network[from][to] = Math.min(network[from][to], network[from][via] + network[via][to]);
				}
			}
		}

		int[] depth = new int[N + 1];
		for(int i = 1; i <= N; i++) {
			for(int j = 1; j <= N; j++) {
				if(i == j) continue;
				depth[i] = Math.max(depth[i], network[i][j]);
			}
		}

		int min = 100;
		int count = 0;
		StringBuilder sb = new StringBuilder();
		for(int i = 1; i <= N; i++) {
			int d = depth[i];

			if(min > d) {
				min = d;
				count = 1;
				sb = new StringBuilder().append(i).append(" ");
				continue;
			}

			if(min == d) {
				count++;
				sb.append(i).append(" ");
			}
		}

		System.out.println(min + " " + count);
		System.out.println(sb);
	}
}
