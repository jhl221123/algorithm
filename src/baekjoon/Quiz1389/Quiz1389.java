package baekjoon.Quiz1389;

import java.io.*;

/*
Silver1: 케빈 베이컨의 6단계 법칙 / [floyd-warshall]
*/
public class Quiz1389 {

	private static int INF = 100000;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] NM = br.readLine().split(" ");
		int N = Integer.parseInt(NM[0]);
		int M = Integer.parseInt(NM[1]);
		int[][] dist = new int[N + 1][N + 1];
		for(int i=0; i<=N; i++) {
			for(int j=0; j<=N; j++) {
				if(i==j) continue;
				dist[i][j] = INF;
			}
		}

		for(int i=0; i<M; i++) {
			String[] ft = br.readLine().split(" ");
			int from = Integer.parseInt(ft[0]);
			int to = Integer.parseInt(ft[1]);
			dist[from][to] = 1;
			dist[to][from] = 1;
		}

		for(int k=1; k<=N; k++) {
			for(int i=1; i<=N; i++) {
				for(int j=1; j<=N; j++) {
					if(i==j) continue;
					dist[i][j] = Math.min(dist[i][j], dist[i][k] + dist[k][j]);
				}
			}
		}

		int ans = 0;
		int min = INF;
		for(int i=1; i<=N; i++) {
			int sum = 0;
			for(int j=1; j<=N; j++) {
				sum += dist[i][j];
			}
			if(sum < min) {
				min = sum;
				ans = i;
			}
		}

		System.out.println(ans);
	}
}
