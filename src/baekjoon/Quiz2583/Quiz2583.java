package baekjoon.Quiz2583;

import java.io.*;
import java.util.*;

/*
Silver1: 영역 구하기 / [bfs]
*/
public class Quiz2583 {

	private static int[] dy = {-1, 1, 0, 0};
	private static int[] dx = {0, 0, -1, 1};

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] nmk = br.readLine().split(" ");
		int N = Integer.parseInt(nmk[0]);
		int M = Integer.parseInt(nmk[1]);
		int K = Integer.parseInt(nmk[2]);

		boolean[][] map = new boolean[N][M];
		for(int i=0; i<K; i++) {
			int[] points = Arrays.stream(br.readLine().split(" "))
				.mapToInt(Integer::parseInt)
				.toArray();
			int x1 = points[0];
			int y1 = points[1];
			int x2 = points[2];
			int y2 = points[3];

			for(int r=N-y2; r<N-y1; r++) {
				for(int c=x1; c<x2; c++) {
					map[r][c] = true;
				}
			}
		}

		List<Integer> counts = new ArrayList<>();
		for(int r=0; r<N; r++) {
			for(int c=0; c<M; c++) {
				if(map[r][c]) continue;

				ArrayDeque<int[]> ad = new ArrayDeque<>();
				ad.addLast(new int[] {r, c});

				int count = 0;
				while(!ad.isEmpty()) {
					int[] cur = ad.removeFirst();
					int cr = cur[0];
					int cc = cur[1];
					if(map[cr][cc]) continue;
					map[cr][cc] = true;
					count++;

					for(int d=0; d<4; d++) {
						int mr = cr + dy[d];
						int mc = cc + dx[d];
						if(mr<0 || mc<0 || mr>=N || mc>=M) continue;
						if(map[mr][mc]) continue;
						ad.addLast(new int[] {mr, mc});
					}
				}

				counts.add(count);
			}
		}

		counts.sort(Comparator.comparingInt(o -> o));
		System.out.println(counts.size());
		for(int i=0; i<counts.size(); i++) {
			System.out.print(counts.get(i) + " ");
		}
	}
}
