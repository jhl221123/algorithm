package baekjoon.Quiz19237;

import java.util.*;
import java.io.*;

/*
Gold2: 어른 상어 / [implementation, simulation]
1. 모든 상어를 순회하며 map의 현재 위치에 번호와 냄새를 남긴다.
1-1. map[r][c][0] = 상어 번호, map[r][c][1] = 냄새
2. 모든 상어를 순회하며 map 상황과 우선순위에 따라 좌표를 이동한다.
2-1. 각 상어는 movementRules[looking][toMove]로 이동 우선순위를 관리한다.
2-2. 4방향 탐색으로 map[mr][mc][0] == 0인 격자를 찾지 못했다면, 다시 순회하며 동일한 번호로 이동한다.
2-3. 이동한 방향으로 현재 상어의 방향을 변경한다.
3. 상어 번호를 오름차순으로 순회하며 방문 처리를 통해 곂치는 상어를 퇴치한다.
3-1. 상어를 큐에서 꺼낸 후, 아직 방문하지 않았다면 visited에 방문처리하고 큐에 다시 넣는다.
3-2. 이미 방문되었다면 해당 상어는 다시 큐에 넣지 않는다.
4. 모든 격자를 순회하며 냄새를 1 감소시키고, 만약 냄새가 0이라면 번호도 0으로 만든다.
5. 전체 상어 수를 확인하고, 단 하나의 상어만 남았다면 종료한다.
*/
public class Quiz19237 {

	private static int N, M, K;
	private static int[][][] map;
	private static ArrayDeque<Shark> sharks;
	private static int[] dy = {10, -1, 1, 0, 0};
	private static int[] dx = {10, 0, 0, -1, 1};

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		map = new int[N][N][2];

		PriorityQueue<Shark> pq = new PriorityQueue<>(Comparator.comparingInt(Shark::getNo));
		for(int row=0; row<N; row++) {
			st = new StringTokenizer(br.readLine());

			for(int col=0; col<N; col++) {
				int sharkNo = Integer.parseInt(st.nextToken());
				if(sharkNo > 0) {
					pq.offer(new Shark(row, col, sharkNo, K));
				}
			}
		}

		sharks = new ArrayDeque<>();
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<M; i++) {
			Shark shark = pq.poll();
			int direction = Integer.parseInt(st.nextToken());
			shark.updateDirection(direction);

			for(int d=1; d<=4; d++) {
				String[] rule = br.readLine().split(" ");
				shark.updateRule(d, rule);
			}

			sharks.addLast(shark);
		}

		// start
		int time = 0;
		while(true) {
			int count = sharks.size();

			// 1. 상어 흔적 남긴다.
			for(int i=0; i<count; i++) {
				Shark shark = sharks.removeFirst();
				int sr = shark.getRow();
				int sc = shark.getCol();
				int sn = shark.getNo();
				int ss = shark.getSmell();

				map[sr][sc][0] = sn;
				map[sr][sc][1] = ss;
				sharks.addLast(shark);
			}

			// 2. 상어 이동한다.
			for(int i=0; i<count; i++) {
				Shark shark = sharks.removeFirst();
				int sr = shark.getRow();
				int sc = shark.getCol();
				int sn = shark.getNo();
				int ss = shark.getSmell();
				int[] priority = shark.getMovementRule();
				boolean isChanged = false;

				for(int p=1; p<=4; p++) {
					int mr = sr + dy[priority[p]];
					int mc = sc + dx[priority[p]];

					if(mr<0 || mc<0 || mr>=N || mc>=N) continue;
					if(map[mr][mc][0] == 0) {
						shark.updatePoint(mr, mc);
						shark.updateDirection(priority[p]);
						isChanged = true;
						break;
					}
				}

				if(!isChanged) {
					for(int p=1; p<=4; p++) {
						int mr = sr + dy[priority[p]];
						int mc = sc + dx[priority[p]];

						if(mr<0 || mc<0 || mr>=N || mc>=N) continue;
						if(map[mr][mc][0] == sn) {
							shark.updatePoint(mr, mc);
							shark.updateDirection(priority[p]);
							break;
						}
					}
				}

				sharks.addLast(shark);
			}

			// 3. 상어 퇴치( 이곳에서 count 변경 발생 )
			Set<String> visited = new HashSet<>();
			for(int i=0; i<count; i++) {
				Shark shark = sharks.removeFirst();
				String snapshot = shark.snapshot();

				if(visited.contains(snapshot)) continue;
				visited.add(snapshot);
				sharks.addLast(shark);
			}

			// 4. 냄새 감소
			for(int row=0; row<N; row++) {
				for(int col=0; col<N; col++) {
					if(map[row][col][1] > 0) {
						map[row][col][1]--;

						if(map[row][col][1] == 0) {
							map[row][col][0] = 0;
						}
					}
				}
			}

			time++;
			if(sharks.size() == 1) {
				break;
			}

			if(time >= 1000) {
				time = -1;
				break;
			}
		}

		System.out.println(time);
	}

	static class Shark {
		private int row;
		private int col;
		private int no;
		private int smell;
		private int direction;
		private int[][] movementRules;

		public Shark(int r, int c, int n, int s) {
			this.row = r;
			this.col = c;
			this.no = n;
			this.smell = s;
			this.direction = 0;
			this.movementRules = new int[5][5];
		}

		public void updatePoint(int r, int c) {
			this.row = r;
			this.col = c;
		}

		public void updateDirection(int d) {
			this.direction = d;
		}

		public void updateRule(int lookingAt, String[] rule) {
			for(int priority=1; priority<=4; priority++) {
				movementRules[lookingAt][priority] = Integer.parseInt(rule[priority - 1]);
			}
		}

		public int[] getMovementRule() {
			return movementRules[this.direction];
		}

		public int getRow() {
			return this.row;
		}

		public int getCol() {
			return this.col;
		}

		public int getNo() {
			return this.no;
		}

		public int getSmell() {
			return this.smell;
		}

		public String snapshot() {
			return this.row + " " + this.col;
		}
	}
}
