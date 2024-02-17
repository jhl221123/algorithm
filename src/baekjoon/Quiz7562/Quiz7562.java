package baekjoon.Quiz7562;

import java.util.*;

public class Quiz7562 {
	public static void main(String[] args) {
		int[] dy = {-1, -2, -2, -1, 1, 2, 2, 1};
		int[] dx = {-2, -1, 1, 2, 2, 1, -1, -2};
		Scanner scanner = new Scanner(System.in);
		int T = scanner.nextInt();
		StringBuilder sb = new StringBuilder();

		while(T --> 0) {
			int N = scanner.nextInt();
			int sr = scanner.nextInt();
			int sc = scanner.nextInt();
			int er = scanner.nextInt();
			int ec = scanner.nextInt();
			int[][] visit = new int[N][N];
			ArrayDeque<int[]> ad = new ArrayDeque<>();
			ad.addLast(new int[] {sr, sc});
			visit[sr][sc] = 1;
			while(!ad.isEmpty()) {
				int[] point = ad.removeFirst();
				int r = point[0];
				int c = point[1];
				for(int i=0; i<8; i++) {
					int mr = r + dy[i];
					int mc = c + dx[i];
					if(mr<0||mr>=N||mc<0||mc>=N) continue;
					if(visit[mr][mc]==0) {
						visit[mr][mc] = visit[r][c] + 1;
						ad.addLast(new int[] {mr, mc});
					}
				}
			}
			sb.append(visit[er][ec] - 1).append("\n");
		}
		System.out.println(sb);
	}
}
