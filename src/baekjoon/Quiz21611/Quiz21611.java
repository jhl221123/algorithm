package baekjoon.Quiz21611;

import java.util.*;
import java.io.*;

/*
Gold1: 마법사 상어와 블리자드 / [implementation, simulation]
1. 블리자드(d, s)로 구슬을 깨뜨린다.
1-1. int[d][s] points로 방향과 거리에 따른 좌표를 관리한다.
2. 시작지점부터 순회하며 빈 공간을 채운다.
2-1. prev != null ? prev++, next++ / prev == null ? while(next == null) next++
2-2. next == (1, 1) break
3. 크기가 4 이상인 그룹이 존재하지 않을 때까지 구슬을 깨뜨리고, 빈 공간을 채운다.
3-1. 시작지점부터 순회하며 그룹핑하고, 크기가 4 이상이면 해당 격자를 null로 치환한다.
3-2. 번호별 폭발한 구슬 개수 합계를 누적한다.
4. 남은 그룹의 크기와 번호를 탐색하고, 새로운 격자에 구슬을 채운다.
4-1. 시작지점부터 순회하며 그룹핑하고, 그룹을 활용해 새로운 구슬 목록을 생성한다.
4-2. 새로운 구슬이 격자 크기보다 크면, 남은 구슬은 제거한다.
4-3. 새로운 구슬을 새로운 격자에 입력한 후, 격자를 교체한다.
*/
public class Quiz21611 {

	private static final int SHARK = 5;
	private static final int TOP = 1;
	private static final int BOTTOM = 2;
	private static final int LEFT = 3;
	private static final int RIGHT = 4;
	private static final int[] dy = {10, -1, 1, 0, 0};
	private static final int[] dx = {10, 0, 0, -1, 1};

	private static int N, M, B;
	private static int[][] map;
	private static int[][][] blizzard;
	private static Set<String> curvePoints;
	private static int[] boomCounts = new int[4];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] nm = br.readLine().split(" ");
		N = Integer.parseInt(nm[0]);
		M = Integer.parseInt(nm[1]);
		B = N / 2;
		map = new int[N][N];
		for(int row=0; row<N; row++) {
			String[] line = br.readLine().split(" ");

			for(int col=0; col<N; col++) {
				map[row][col] = Integer.parseInt(line[col]);
				if(row == B && col == B) {
					map[row][col] = SHARK;
				}
			}
		}
		initializeCurvePoints();
		initializeBlizzard();

		for(int i=0; i<M; i++) {
			String[] ds = br.readLine().split(" ");
			int d = Integer.parseInt(ds[0]);
			int s = Integer.parseInt(ds[1]);

			useBlizzard(d, s);
			// printMap("after blizzard!");
			fillBlankCell();
			// printMap("blizzard -> fill!");

			int boomCount = 0;
			int bc = 1;
			while(true) {
				boomCount = boom();
				// printMap("boom! : " + bc++);
				if(boomCount == 0) {
					break;
				}
				fillBlankCell();
				// printMap("boom -> fill! : " + bc++);
			}

			renewal();
			// printMap("after renewal!");
		}

		System.out.println(boomCounts[1] + (boomCounts[2] * 2) + (boomCounts[3] * 3));
	}

	private static void printMap(String title) {
		System.out.println(title);

		for(int row=0; row<N; row++) {
			for(int col=0; col<N; col++) {
				System.out.print(map[row][col] + " ");
			}
			System.out.println();
		}

		System.out.println();
	}

	private static void renewal() {
		int totalCount = N * N - 1;

		ArrayDeque<Integer> beads = new ArrayDeque<>();
		int[] cursor = new int[] {B, B - 1, BOTTOM};
		int beadNo = map[B][B - 1];

		if(beadNo == 0) {
			return;
		}

		int bizCount = 1;
		while(true) {
			if(cursor[0] == 0 && cursor[1] == 0) {
				beads.addLast(bizCount);
				if(beads.size() >= totalCount) {
					break;
				}

				beads.addLast(beadNo);
				if(beads.size() >= totalCount) {
					break;
				}
			}
			cursor = toNext(cursor);

			if(map[cursor[0]][cursor[1]] == beadNo) {
				// 다음 구슬이 같은 번호일 경우
				bizCount++;
			} else {
				// 다음 구슬이 다른 번호일 경우
				beads.addLast(bizCount);
				if(beads.size() >= totalCount) {
					break;
				}

				beads.addLast(beadNo);
				if(beads.size() >= totalCount) {
					break;
				}

				if(map[cursor[0]][cursor[1]] == 0) {
					// 다음 구슬이 존재하지 않을 경우
					break;
				}

				beadNo = map[cursor[0]][cursor[1]];
				bizCount = 1;
			}
		}

		map = createNewMap(beads);
	}

	private static int boom() {
		int[] cursor = new int[] {B, B - 1, BOTTOM};
		int beadNo = map[B][B - 1];

		if(beadNo == 0) {
			return 0;
		}

		ArrayDeque<int[]> ad = new ArrayDeque<>();
		ad.addLast(new int[] {cursor[0], cursor[1]});
		int boomCount = 0;
		while(true) {
			if(cursor[0] == 0 && cursor[1] == 0) {
				int groupSize = ad.size();
				if(groupSize >= 4) {
					boomCounts[beadNo] += groupSize;
					boomCount++;

					while (!ad.isEmpty()) {
						int[] removeTarget = ad.removeFirst();
						map[removeTarget[0]][removeTarget[1]] = 0;
					}
				}
				break;
			}
			cursor = toNext(cursor);

			if(map[cursor[0]][cursor[1]] == beadNo) {
				// 다음 구슬이 같은 번호일 경우
				ad.addLast(new int[] {cursor[0], cursor[1]});
			} else {
				// 다음 구슬이 다른 번호일 경우
				int groupSize = ad.size();
				if(groupSize >= 4) {
					boomCounts[beadNo] += groupSize;
					boomCount++;

					while (!ad.isEmpty()) {
						int[] removeTarget = ad.removeFirst();
						map[removeTarget[0]][removeTarget[1]] = 0;
					}
				} else {
					ad.clear();
				}

				// 다음 구슬이 존재하지 않을 경우
				if(map[cursor[0]][cursor[1]] == 0) {
					break;
				}

				ad.addLast(new int[] {cursor[0], cursor[1]});
				beadNo = map[cursor[0]][cursor[1]];
			}
		}

		return boomCount;
	}

	private static void fillBlankCell() {
		int[] cursor = new int[] {B, B-1, BOTTOM};
		ArrayDeque<Integer> newBiz = new ArrayDeque<>();

		while(true) {
			int num = map[cursor[0]][cursor[1]];
			if(num > 0) newBiz.addLast(num);
			if(cursor[0] == 0 && cursor[1] == 0) {
				break;
			}
			cursor = toNext(cursor);
		}

		map = createNewMap(newBiz);
	}

	private static int[][] createNewMap(ArrayDeque<Integer> beads) {
		int[][] newMap = new int[N][N];
		newMap[B][B] = SHARK;

		int[] cursor = new int[] {B, B - 1, BOTTOM};
		while(!beads.isEmpty()) {
			int biz = beads.removeFirst();
			newMap[cursor[0]][cursor[1]] = biz;

			if(cursor[0] == 0 && cursor[1] == 0) {
				break;
			}
			cursor = toNext(cursor);
		}

		return newMap;
	}

	private static void useBlizzard(int d, int s) {
		for(int ns=1; ns<=s; ns++) {
			int row = blizzard[d][ns][0];
			int col = blizzard[d][ns][1];

			map[row][col] = 0;
		}
	}

	private static void initializeBlizzard() {
		blizzard = new int[5][B + 1][2];

		int[] center = new int[] {B, B};
		for(int d=1; d<=4; d++) {
			for(int s=1; s<=B; s++) {
				blizzard[d][s][0] = center[0] + s * dy[d];
				blizzard[d][s][1] = center[1] + s * dx[d];
			}
		}
	}

	private static void initializeCurvePoints() {
		curvePoints = new HashSet<>();

		int[] center = new int[] {B, B};
		for(int i=1; i<=B; i++) {
			curvePoints.add((center[0] - (i-1)) + " " + (center[1] - (i-1) - 1));
			curvePoints.add((center[0] - i) + " " + (center[1] + i));
			curvePoints.add((center[0] + i) + " " + (center[1] + i));
			curvePoints.add((center[0] + i) + " " + (center[1] - i));
		}
	}

	private static int[] toNext(int[] point) {
		int r = point[0];
		int c = point[1];

		if(r == 0 && c == 0) {
			throw new RuntimeException("can't move to next");
		}

		int d = point[2];

		int mr = r + dy[d];
		int mc = c + dx[d];
		if(curvePoints.contains(mr + " " + mc)) {
			if(d == TOP) d = LEFT;
			else if(d == BOTTOM) d = RIGHT;
			else if(d == LEFT) d = BOTTOM;
			else if(d == RIGHT) d = TOP;
		}

		return new int[] {mr, mc, d};
	}
}
