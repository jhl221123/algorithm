package baekjoon.Quiz18405;

import java.util.*;
import java.io.*;

/*
Gold5: 경쟁적 전염 / [implementation, bfs]
1. 초기 바이러스를 번호 오름차순으로 관리한다.
1-1. 초기 바이러스가 하나도 존재하지 않는다면 탐색하지 않는다.
2. 바이러스를 순회하며 번식시키고, 번식된 시간을 함께 저장한다.
2-1. 모든 칸에 바이러스가 전염되었다면 탐색을 종료한다.
3. 지정된 칸의 번식 시간과 비교해 0 or no를 출력한다.
*/
public class Quiz18405 {

	private static final int[] dy = {-1, 1, 0, 0};
	private static final int[] dx = {0, 0, -1, 1};

	private static int N, K;
	private static int[][][] map;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] nk = br.readLine().split(" ");
		N = Integer.parseInt(nk[0]);
		K = Integer.parseInt(nk[1]);
		map = new int[N][N][2];

		PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(o -> o[2]));
		for(int row=0; row<N; row++) {
			String[] line = br.readLine().split(" ");

			for(int col=0; col<N; col++) {
				map[row][col][0] = Integer.parseInt(line[col]);
				if(map[row][col][0] > 0) pq.offer(new int[] {row, col, map[row][col][0], 0});
			}
		}

		ArrayDeque<int[]> ad = new ArrayDeque<>();
		int initialVirusCount = pq.size();
		for(int i=0; i<initialVirusCount; i++) {
			ad.addLast(pq.poll());
		}

		boolean[][] visited = new boolean[N][N];
		while(!ad.isEmpty()) {
			int[] virus = ad.removeFirst();
			int r = virus[0];
			int c = virus[1];
			int num = virus[2];
			int s = virus[3];

			if(visited[r][c]) {
				continue;
			}

			visited[r][c] = true;
			map[r][c][0] = num;
			map[r][c][1] = s;

			for(int d=0; d<4; d++) {
				int mr = r + dy[d];
				int mc = c + dx[d];

				if(!isIn(mr, mc) || visited[mr][mc]) {
					continue;
				}

				ad.addLast(new int[] {mr, mc, num, s + 1});
			}
		}

		String[] sxy = br.readLine().split(" ");
		int s = Integer.parseInt(sxy[0]);
		int x = Integer.parseInt(sxy[1]) - 1;
		int y = Integer.parseInt(sxy[2]) - 1;

		System.out.println(map[x][y][1] > s ? 0 : map[x][y][0]);
	}

	private static boolean isIn(int row, int col) {
		return row >= 0 && col >= 0 && row < N && col < N;
	}
}
