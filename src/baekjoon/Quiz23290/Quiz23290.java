package baekjoon.Quiz23290;

import java.util.*;
import java.io.*;

/*
Gold1: 마법사 상어와 복제 / [implementation, simulation]
1. 현재 물고기 위치를 복제해둔다.
2. 모든 물고기를 순회하며 위치와 방향을 변경한다.
2-1. 물고기 위치를 모두 변경한 후, 격자상의 위치도 변경한다.
2-2. 이동할 수 있는 곳이 없다면 변경하지 않는다.
3. 상어가 제거할 수 있는 물고기 수의 최대값을 갱신하며 이동 가능한 경로를 탐색한다.
3-1. 최대값인 동시에 사전순으로 가장 빠른 경로로 이동한다.
3-2. 해당 경로로 이동하면서 물고기 냄새(2)를 남긴다.
4. 물고기 냄새를 순회하며 0 이상인 곳은 1 감소시킨다.
5. 복제 배열을 순회하며 현재 격자에 물고기를 생성한다.
*/
public class Quiz23290 {

	private static final int L = 4;
	private static final int[] DY = {10, 0, -1, -1, -1, 0, 1, 1, 1};
	private static final int[] DX = {10, -1, -1, 0, 1, 1, 1, 0, -1};

	private static int M, S;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] ms = br.readLine().split(" ");
		M = Integer.parseInt(ms[0]);
		S = Integer.parseInt(ms[1]);

		ArrayDeque<Fish> initialFishes = new ArrayDeque<>();
		for(int fishCount=0; fishCount<M; fishCount++) {
			String[] fishInfo = br.readLine().split(" ");
			int row = Integer.parseInt(fishInfo[0]) - 1;
			int col = Integer.parseInt(fishInfo[1]) - 1;
			int dir = Integer.parseInt(fishInfo[2]);

			initialFishes.addLast(new Fish(row, col, dir));
		}

		FishHolderMap fishesMap = new FishHolderMap();
		fishesMap.addFishes(initialFishes);

		String[] sharkInfo = br.readLine().split(" ");
		Shark shark = new Shark(Integer.parseInt(sharkInfo[0]) - 1, Integer.parseInt(sharkInfo[1]) - 1);

		int[][] smell = new int[L][L];
		while(S-- > 0) {
			ArrayDeque<Fish> copiedFishes = fishesMap.copyFishes();
			FishHolderMap fishesMovedMap = fishesMap.moveFishes(shark, smell);

			reduceSmell(smell);

			shark = shark.moveAndRemoveFishes(fishesMovedMap, smell);
			fishesMovedMap.addFishes(copiedFishes);
			fishesMap = fishesMovedMap;
		}

		System.out.println(fishesMap.getAllFishCount());
	}

	private static void reduceSmell(int[][] smell) {
		for(int row=0; row<L; row++) {
			for(int col=0; col<L; col++) {
				if(smell[row][col] > 0) {
					smell[row][col]--;
				}
			}
		}
	}

	static class FishHolderMap {
		private FishHolder[][] map;

		public FishHolderMap() {
			this.map = new FishHolder[L][L];
			for(int row=0; row<L; row++) {
				for(int col=0; col<L; col++) {
					this.map[row][col] = new FishHolder();
				}
			}
		}

		public int getAllFishCount() {
			int totalCount = 0;

			for(int row=0; row<L; row++) {
				for(int col=0; col<L; col++) {
					totalCount += this.getFishCount(row, col);
				}
			}

			return totalCount;
		}

		public int getFishCount(int row, int col) {
			return this.map[row][col].size();
		}

		public void addFishes(ArrayDeque<Fish> fishes) {
			while(!fishes.isEmpty()) {
				Fish fish = fishes.removeFirst();
				int row = fish.getRow();
				int col = fish.getCol();
				this.map[row][col].addFirst(fish);
			}
		}

		public void removeFishes(int row, int col) {
			FishHolder fishHolder = this.map[row][col];
			fishHolder.removeAll();
		}

		public FishHolderMap moveFishes(Shark shark, int[][] smell) {
			ArrayDeque<Fish> moveQueue = new ArrayDeque<>();

			for(int row=0; row<L; row++) {
				for(int col=0; col<L; col++) {
					FishHolder fishHolder = this.map[row][col];
					if(fishHolder.isEmpty()) {
						continue;
					}

					fishHolder.moveFishes(moveQueue, shark, smell);
				}
			}

			FishHolderMap newFishHolderMap = new FishHolderMap();
			newFishHolderMap.addFishes(moveQueue);
			return newFishHolderMap;
		}

		public ArrayDeque<Fish> copyFishes() {
			ArrayDeque<Fish> copyQueue = new ArrayDeque<>();

			for(int row=0; row<L; row++) {
				for(int col=0; col<L; col++) {
					FishHolder fishHolder = this.map[row][col];
					if(fishHolder.isEmpty()) {
						continue;
					}

					fishHolder.copyFishes(copyQueue);
				}
			}

			return copyQueue;
		}
	}

	static class FishHolder {
		private ArrayDeque<Fish> fishes;

		public FishHolder() {
			fishes = new ArrayDeque<>();
		}

		public int size() {
			return this.fishes.size();
		}

		public boolean isEmpty() {
			return this.fishes.isEmpty();
		}

		public void addFirst(Fish fish) {
			this.fishes.addLast(fish);
		}

		public Fish removeFirst() {
			return this.fishes.removeFirst();
		}

		public void removeAll() {
			this.fishes.clear();
		}

		public void moveFishes(ArrayDeque<Fish> moveQueue, Shark shark, int[][] smell) {
			int currentSize = this.size();

			for(int i=0; i<currentSize; i++) {
				Fish fish = this.removeFirst();
				moveQueue.addLast(fish.move(shark, smell));
				this.addFirst(fish);
			}
		}

		public void copyFishes(ArrayDeque<Fish> copyQueue) {
			int currentSize = this.size();

			for(int i=0; i<currentSize; i++) {
				Fish fish = this.removeFirst();
				copyQueue.addLast(fish.copy());
				this.addFirst(fish);
			}
		}
	}

	static class Fish {
		private int row;
		private int col;
		private int dir;

		public Fish(int r, int c, int d) {
			this.row = r;
			this.col = c;
			this.dir = d;
		}

		public int getRow() {
			return this.row;
		}

		public int getCol() {
			return this.col;
		}

		public int getDir() {
			return this.dir;
		}

		public Fish move(Shark shark, int[][] smell) {
			int md = this.dir;
			int changeCount = 0;

			while(true) {
				int mr = this.row + DY[md];
				int mc = this.col + DX[md];
				changeCount++;

				if(isIn(mr, mc) && !shark.isSamePos(mr, mc) && smell[mr][mc] == 0) {
					return new Fish(mr, mc, md);
				}

				if(changeCount > 8) {
					return new Fish(this.row, this.col, this.dir);
				}

				md--;
				if(md <= 0) {
					md = 8;
				}
			}
		}

		public Fish copy() {
			return new Fish(this.row, this.col, this.dir);
		}

		private boolean isIn(int r, int c) {
			return r >= 0 && c >= 0 && r < L && c < L;
		}
	}

	static class Shark {

		private static final int[] dy = {10, -1, 0, 1, 0};
		private static final int[] dx = {10, 0, -1, 0, 1};

		private int row;
		private int col;
		private List<String> moveRule;

		public Shark(int r, int c) {
			this.row = r;
			this.col = c;
			initializeMoveRule();
		}

		public boolean isSamePos(int r, int c) {
			return this.row == r && this.col == c;
		}

		public Shark moveAndRemoveFishes(FishHolderMap map, int[][] smell) {
			/// 1. 64방향 중 물고기를 최대 제거할 수 있는 방향을 탐색한다.
			int moveRuleSize = moveRule.size();
			int maxCount = -1;
			int candidateRuleIdx = -1;

			for(int idx = 0; idx< moveRuleSize; idx++) {
				String rule = moveRule.get(idx);
				int mr = this.row;
				int mc = this.col;

				int removedFishCount = 0;
				boolean isPossible = true;
				Set<String> visited = new HashSet<>();
				for(int order=0; order<3; order++) {
					int dir = rule.charAt(order) - '0';
					mr += dy[dir];
					mc += dx[dir];

					if(!isIn(mr, mc)) {
						isPossible = false;
						break;
					}

					String snapshot = mr + " " + mc;
					if(!visited.contains(snapshot)) {
						removedFishCount += map.getFishCount(mr, mc);
						visited.add(snapshot);
					}
				}

				if(isPossible) {
					if(maxCount < removedFishCount) {
						maxCount = removedFishCount;
						candidateRuleIdx = idx;
					}
				}
			}
			/// 2. holder를 비우고 냄새를 추가한다.
			String rule = this.moveRule.get(candidateRuleIdx);
			int nextRow = this.row;
			int nextCol = this.col;

			for(int order=0; order<3; order++) {
				int dir = rule.charAt(order) - '0';
				nextRow += dy[dir];
				nextCol += dx[dir];

				if(map.getFishCount(nextRow, nextCol) > 0) {
					map.removeFishes(nextRow, nextCol);
					smell[nextRow][nextCol] = 2;
				}
			}
			return new Shark(nextRow, nextCol);
		}

		private void initializeMoveRule() {
			this.moveRule = new ArrayList<>();

			int path = 111;
			while(true) {
				moveRule.add(String.valueOf(path));
				if(path == 444) {
					break;
				}

				path++;
				int mod = path % 10;
				if(mod > 4) {
					path = (path / 10 + 1) * 10;
					path += (mod - 4);
				}

				mod = (path % 100) / 10;
				int temp = path % 10;
				if(mod > 4) {
					path = (path / 100 + 1) * 100;
					path += (mod - 4) * 10;
					path += temp;
				}
			}
		}

		private boolean isIn(int r, int c) {
			return r >= 0 && c >= 0 && r < L && c < L;
		}
	}
}
