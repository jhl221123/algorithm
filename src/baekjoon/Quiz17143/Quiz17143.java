package baekjoon.Quiz17143;

import java.util.*;
import java.io.*;

/*
Gold1: 낚시왕 / [implementation, simulation]
1. 낚시왕이 현재 위치에서 가장 낮은 행의 상어를 획득한다.
1-1. 격자에서 상어 번호를 지우고 Map에서 상어 정보를 제거한다.
2. 격자의 모든 상어를 순회하며, Map의 상어 정보 내부 좌표를 변경한다.
2-1. 실질적 거리[s %= (N - 1) * 2] 계산 후, 방향과 위치 조정
2-2. 조정된 상어는 크기 내림차순의 우선순위 큐에 넣는다.
2-3. 큐에서 순차적으로 상어를 가져와 새로운 격자에 번호를 입력한다.
2-4. 이미 격자에 번호가 있다면, 해당 상어의 정보를 Map에서 제거한다.
3. 낚시왕이 마지막 열에서 상어를 획득한 후 종료한다.
*/
public class Quiz17143 {

	private static final int TOP = 1;
	private static final int BOTTOM = 2;
	private static final int RIGHT = 3;
	private static final int LEFT = 4;

	private static int R, C, M;
	private static int[][] map;
	private static Map<Integer, Shark> sharks;
	private static int totalWeight = 0;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] rcm = br.readLine().split(" ");
		R = Integer.parseInt(rcm[0]);
		C = Integer.parseInt(rcm[1]);
		M = Integer.parseInt(rcm[2]);
		map = new int[R][C];

		sharks = new HashMap<>();
		for(int no=1; no<=M; no++) {
			String[] info = br.readLine().split(" ");
			int row = Integer.parseInt(info[0]) - 1;
			int col = Integer.parseInt(info[1]) - 1;
			int speed = Integer.parseInt(info[2]);
			int direction = Integer.parseInt(info[3]);
			int weight = Integer.parseInt(info[4]);

			sharks.put(no, new Shark(no, row, col, speed, direction, weight));
			map[row][col] = no;
		}

		for(int col=0; col<C; col++) {
			hookShark(col);
			moveShark();
		}

		System.out.println(totalWeight);
	}

	private static void hookShark(int col) {
		for(int row=0; row<R; row++) {
			if(map[row][col] > 0) {
				int sharkNo = map[row][col];
				Shark shark = sharks.get(sharkNo);
				totalWeight += shark.getWeight();
				sharks.remove(sharkNo);
				map[row][col] = 0;
				return;
			}
		}
	}

	private static void moveShark() {
		PriorityQueue<Shark> pq = new PriorityQueue<>((o1, o2) -> o2.getWeight() - o1.getWeight());

		for(int row=0; row<R; row++) {
			for(int col=0; col<C; col++) {
				if(map[row][col] > 0) {
					int sharkNo = map[row][col];
					Shark shark = sharks.get(sharkNo);
					Shark movedShark = shark.move(R, C);
					pq.offer(movedShark);
				}
			}
		}

		int[][] temp = new int[R][C];
		while(!pq.isEmpty()) {
			Shark shark = pq.poll();
			int sharkNo = shark.getNo();
			int row = shark.getRow();
			int col = shark.getCol();
			sharks.remove(sharkNo);
			if(temp[row][col] > 0) {
				continue;
			}

			temp[row][col] = sharkNo;
			sharks.put(sharkNo, shark);
		}

		map = temp;
	}

	static class Shark {
		private int no;
		private int row;
		private int col;
		private int speed;
		private int direction;
		private int weight;

		public Shark(int n, int r, int c, int s, int d, int w) {
			this.no = n;
			this.row = r;
			this.col = c;
			this.speed = s;
			this.direction = d;
			this.weight = w;
		}

		public Shark move(int tr, int tc) {
			int elr = (tr - 1) * 2;
			int elc = (tc - 1) * 2;
			int movement = this.speed;
			int nr = this.row;
			int nc = this.col;
			int nd = this.direction;

			if(this.direction == TOP) {
				movement %= elr;
				nr -= movement;

				if(nr < 0) {
					nr *= -1;
					nd = BOTTOM;
				}

				if(nr >= tr) {
					nr = tr - 1 - (nr - tr + 1);
					nd = TOP;
				}
			}
			else if(this.direction == BOTTOM) {
				movement %= elr;
				nr += movement;

				if(nr >= tr) {
					nr = tr - 1 - (nr - tr + 1);
					nd = TOP;
				}

				if(nr < 0) {
					nr *= -1;
					nd = BOTTOM;
				}
			}
			else if(this.direction == RIGHT) {
				movement %= elc;
				nc += movement;

				if(nc >= tc) {
					nc = tc - 1 - (nc - tc + 1);
					nd = LEFT;
				}

				if(nc < 0) {
					nc *= -1;
					nd = RIGHT;
				}
			}
			else if(this.direction == LEFT) {
				movement %= elc;
				nc -= movement;

				if(nc < 0) {
					nc *= -1;
					nd = RIGHT;
				}

				if(nc >= tc) {
					nc = tc - 1 - (nc - tc + 1);
					nd = LEFT;
				}
			}

			return new Shark(this.no, nr, nc, this.speed, nd, this.weight);
		}

		public int getNo() {
			return this.no;
		}

		public int getRow() {
			return this.row;
		}

		public int getCol() {
			return this.col;
		}

		public int getWeight() {
			return this.weight;
		}
	}
}
