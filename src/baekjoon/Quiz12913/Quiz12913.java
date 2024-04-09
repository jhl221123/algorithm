package baekjoon.Quiz12913;

import java.util.*;
import java.io.*;

public class Quiz12913 {
	static int N, K;
	static int[][] map;
	static double[][] dist;
	static PriorityQueue<double[]> pq = new PriorityQueue<>(Comparator.comparingDouble(a -> a[2]));
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		map = new int[N][N];
		for(int i=0; i<N; i++) {
			String str = br.readLine();
			for(int j=0; j<N; j++) {
				map[i][j] = str.charAt(j) - '0';
			}
		}
		//init dist
		dist = new double[N][K+1];
		for(int i = 0; i<N; i++) {
			for(int j = 0; j<=K; j++) {
				if(i==0) dist[i][j] = 0;
				else dist[i][j] = Double.MAX_VALUE;
			}
		}
		// djik
		pq.offer(new double[] {0, K, 0});
		while(!pq.isEmpty()) {
			double[] target = pq.poll();
			int from = (int)target[0];
			int k = (int)target[1];
			for(int to=0; to<N; to++) {
				double toDist = map[from][to];
				double newDist = dist[from][k] + toDist;
				double originDist = dist[to][k];
				if(newDist < originDist) {
					dist[to][k] = newDist;
					pq.offer(new double[] {to, k, newDist});
				}
				if(k > 0) {
					toDist /= 2;
					newDist = dist[from][k] + toDist;
					originDist = dist[to][k-1];
					if(newDist < originDist) {
						dist[to][k-1] = newDist;
						pq.offer(new double[] {to, k-1, newDist});
					}
				}
				//                for(int i=0; i<N; i++) {
				//                    System.out.println(Arrays.toString(dist[i]));
				//                }
			}
		}
		double ans = Double.MAX_VALUE;
		for(int i=0; i<=K; i++) {
			ans = Math.min(ans, dist[1][i]);
		}
		System.out.println(ans);
	}
}
