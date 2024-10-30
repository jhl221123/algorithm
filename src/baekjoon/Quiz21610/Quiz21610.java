package baekjoon.Quiz21610;

import java.util.*;
import java.io.*;

public class Quiz21610 {
	static int N, M;
	static int[][] map;
	static boolean[][] cloud;
	static boolean[][] moved;
	static int[] dy = {-10, 0, -1, -1, -1, 0, 1, 1, 1};
	static int[] dx = {-10, -1, -1, 0, 1, 1, 1, 0, -1};

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N+1][N+1];
		cloud = new boolean[N+1][N+1];

		for(int i=1; i<=N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=1; j<=N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		cloud[N][1] = true;
		cloud[N][2] = true;
		cloud[N-1][1] = true;
		cloud[N-1][2] = true;

		for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			int d = Integer.parseInt(st.nextToken());
			int s = Integer.parseInt(st.nextToken());

			moveCloud(d, s);
			rainToMap();
			copyBug();
			createCloud();
		}

		int sum = sumTotalWater();
		System.out.println(sum);
	}

	private static void moveCloud(int d, int s) {
		moved = new boolean[N+1][N+1];
		for(int r=1; r<=N; r++) {
			for(int c=1; c<=N; c++) {
				if(cloud[r][c]) {
					int mr = dy[d] * s + r;
					int mc = dx[d] * s + c;
					if(mr > N) mr %= N;
					if(mr < 1) mr = N - Math.abs(mr) % N;
					if(mc > N) mc %= N;
					if(mc < 1) mc = N - Math.abs(mc) % N;
					moved[mr][mc] = true;
				}
			}
		}
	}

	private static void rainToMap() {
		for(int r=1; r<=N; r++) {
			for(int c=1; c<=N; c++) {
				if(moved[r][c]) map[r][c]++;
			}
		}
	}

	private static void copyBug() {
		for(int r=1; r<=N; r++) {
			for(int c=1; c<=N; c++) {
				if(moved[r][c]) {
					int count = 0;
					for(int d=2; d<=8; d++) {
						if(d%2==1) continue;
						int mr = dy[d] + r;
						int mc = dx[d] + c;
						if(mr > N || mc > N || mr < 1 || mc < 1) continue;
						if(map[mr][mc] > 0) count++;
					}
					map[r][c] += count;
				}
			}
		}
	}

	private static void createCloud() {
		cloud = new boolean[N+1][N+1];
		for(int r=1; r<=N; r++) {
			for(int c=1; c<=N; c++) {
				if(map[r][c] >= 2 && !moved[r][c]) {
					map[r][c] -= 2;
					cloud[r][c] = true;
				}
			}
		}
	}

	private static int sumTotalWater() {
		int sum = 0;
		for(int r=1; r<=N; r++) {
			for(int c=1; c<=N; c++) {
				sum += map[r][c];
			}
		}
		return sum;
	}
}
