package baekjoon.Quiz21609;

import java.util.*;
import java.io.*;

/*
Gold2: 상어 중학교 / [implementation, simulation]
1. 블럭을 제거하고 점수를 얻는다.
1-1. floodFill로 블럭 번호, 블럭 크기, 무지개 개수, 기준 블럭 좌표를 구한다.
1-2. 우선순위가 가장 높은 블럭을 반환하며, 블럭이 존재하지 않으면 블럭 크기에 플래그를 반환한다.
1-3. 블럭 번호로 블럭을 제거하고 점수를 합한다.
2. 블럭이 떨어진다.
2-1. c -> N / r -> 0 으로 이동하며 스위핑한다.
2-2. 검은 블럭을 만나면 타겟 위치를 조정한다.
3. 반시계 회전한다.
3-1. (r, c) -> (N - c - 1 , r)
4. 블럭이 떨어진다.
*/
public class Quiz21609 {

	private static final int EMPTY = -2;
	private static final int BLACK = -1;
	private static final int RAINBOW = 0;
	private static final int[] dy = {-1, 1, 0, 0};
	private static final int[] dx = {0, 0, -1, 1};

	private static int N, M;
	private static int[][] map;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] nm = br.readLine().split(" ");
		N = Integer.parseInt(nm[0]);
		M = Integer.parseInt(nm[1]);
		map = new int[N][N];
		for(int row=0; row<N; row++) {
			String[] line = br.readLine().split(" ");

			for(int col=0; col<N; col++) {
				map[row][col] = Integer.parseInt(line[col]);
			}
		}

		int totalScore = 0;
		while(true) {
			int score = boom();
			if(score < 0) {
				break;
			}

			totalScore += score;
			fall();
			rotate();
			fall();
		}

		System.out.println(totalScore);
	}

	private static void rotate() {
		int[][] temp = new int[N][N];

		for(int row=0; row<N; row++) {
			for(int col=0; col<N; col++) {
				temp[N - col - 1][row] = map[row][col];
			}
		}

		map = temp;
	}

	private static void fall() {
		for(int col=0; col<N; col++) {
			for(int row=N-1; row>=0; row--) {
				if(map[row][col] == EMPTY) {
					for(int nr=row-1; nr>=0; nr--) {
						if(map[nr][col] >= 0) {
							map[row][col] = map[nr][col];
							map[nr][col] = EMPTY;
							break;
						}

						if(map[nr][col] == BLACK) {
							row = nr;
							break;
						}
					}
				}
			}
		}
	}

	private static int boom() {
		boolean[][] visited = new boolean[N][N];
		PriorityQueue<Block> candidates = new PriorityQueue<>((o1, o2) -> {
			if(o2.totalCount == o1.totalCount) {
				if(o2.rainbowCount == o1.rainbowCount) {
					if(o2.row == o1.row) {
						return o2.col - o1.col;
					}

					return o2.row - o1.row;
				}

				return o2.rainbowCount - o1.rainbowCount;
			}

			return o2.totalCount - o1.totalCount;
		});

		for(int row=0; row<N; row++) {
			for(int col=0; col<N; col++) {
				if(map[row][col] > 0 && !visited[row][col]) {
					Block block = floodFill(row, col, visited);
					if(block == null) {
						continue;
					}
					candidates.add(block);
				}
			}
		}

		if(candidates.isEmpty()) {
			return -1;
		}

		Block block = candidates.poll();
		removeBlock(block.row, block.col);

		return (int) Math.pow(block.totalCount, 2);
	}

	private static Block floodFill(int row, int col, boolean[][] visited) {
		boolean[][] rainbowVisited = new boolean[N][N];
		int color = map[row][col];
		int baseRow =  N + 1;
		int baseCol =  N + 1;
		int totalCount = 0;
		int rainbowCount = 0;

		ArrayDeque<int[]> ad = new ArrayDeque<>();
		ad.addLast(new int[] {row, col});
		while(!ad.isEmpty()) {
			int[] point = ad.removeFirst();
			int r = point[0];
			int c = point[1];

			if(rainbowVisited[r][c] || visited[r][c]) {
				continue;
			}

			if(map[r][c] == RAINBOW) {
				rainbowVisited[r][c] = true;
				rainbowCount++;
			} else {
				visited[r][c] = true;
				if(baseRow > r || (baseRow == r && baseCol > c)) {
					baseRow = r;
					baseCol = c;
				}
			}
			totalCount++;

			for(int d=0; d<4; d++) {
				int mr = r + dy[d];
				int mc = c + dx[d];

				if(isIn(mr, mc) && ((!rainbowVisited[mr][mc] && map[mr][mc] == RAINBOW) || (!visited[mr][mc] && map[mr][mc] == color))) {
					ad.addLast(new int[] {mr, mc});
				}
			}
		}

		if(totalCount > 1) {
			return new Block(totalCount, rainbowCount, baseRow, baseCol);
		}

		return null;
	}

	private static void removeBlock(int row, int col) {
		int color = map[row][col];
		boolean[][] visited = new boolean[N][N];

		ArrayDeque<int[]> ad = new ArrayDeque<>();
		ad.addLast(new int[] {row, col});
		while(!ad.isEmpty()) {
			int[] point = ad.removeFirst();
			int r = point[0];
			int c = point[1];

			if(visited[r][c]) {
				continue;
			}
			visited[r][c] = true;
			map[r][c] = EMPTY;

			for(int d=0; d<4; d++) {
				int mr = r + dy[d];
				int mc = c + dx[d];

				if(isIn(mr, mc) && !visited[mr][mc] && (map[mr][mc] == RAINBOW || map[mr][mc] == color)) {
					ad.addLast(new int[] {mr, mc});
				}
			}
		}
	}

	private static boolean isIn(int row, int col) {
		return row >= 0 && col >= 0 && row < N && col < N;
	}

	static class Block {
		int totalCount;
		int rainbowCount;
		int row;
		int col;

		public Block(int totalCount, int rainbowCount, int row, int col) {
			this.totalCount = totalCount;
			this.rainbowCount = rainbowCount;
			this.row = row;
			this.col = col;
		}
	}
}
