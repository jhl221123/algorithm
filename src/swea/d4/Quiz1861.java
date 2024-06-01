package swea.d4;

import java.util.ArrayDeque;
import java.util.Scanner;

public class Quiz1861 {
	static int[] dy = {-1, 1, 0, 0};
	static int[] dx = {0, 0, -1, 1};
	public static void main(String[] args) throws Exception
	{
		Scanner sc = new Scanner(System.in);
		int T;
		T=sc.nextInt();

		for(int test_case = 1; test_case <= T; test_case++)
		{
			int N = sc.nextInt();
			int[][] room = new int[N][N];
			// 모든 좌표에서 출발 -> 이동 수가 같다면 시작 점이 작은 곳으로 갱신, 이동 수가 크면 해당 좌표로 갱신
			for(int i=0; i<N; i++) {
				for(int j=0; j<N; j++) {
					room[i][j] = sc.nextInt();
				}
			}
			int spot = N * N + 1;
			int max = 0;
			for(int i=0; i<N; i++) {
				for(int j=0; j<N; j++) {
					int dist = move(i, j, room);
					if(max == dist) spot = Math.min(spot, room[i][j]);
					else if(max < dist) {
						max = dist;
						spot = room[i][j];
					}
				}
			}
			System.out.println("#" + test_case + " " + spot + " " + max);
		}
	}
	private static int move(int r, int c, int[][] room) {
		ArrayDeque<int[]> ad = new ArrayDeque<>();
		ad.addLast(new int[] {r, c});
		int count = 1;
		while(!ad.isEmpty()) {
			int[] point = ad.removeFirst();
			int cr = point[0];
			int cc = point[1];
			for(int i=0; i<4; i++) {
				int mr = cr + dy[i];
				int mc = cc + dx[i];
				if(mr<0||mr>=room[0].length||mc<0||mc>=room[0].length) continue;
				if(room[mr][mc] == room[cr][cc] + 1) {
					count++;
					ad.addLast(new int[] {mr, mc});
				}
			}
		}
		return count;
	}
}
