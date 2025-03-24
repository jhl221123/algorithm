package baekjoon.Quiz19236;

import java.util.*;
import java.io.*;

/*
Gold1: 청소년 상어 / [implementation, simulation, dfs, backtracking]
1. 상어가 이동할 수 있는 모든 칸으로 이동한다.
1-1. 해당 칸에 물고기가 있다면, 물고기 번호를 더하고 해당 물고기의 방향으로 변경한다.
1-2. 물고기가 없다면, 더이상 탐색을 진행하지 않는다.
2. 모든 물고기를 순회하며 우선순위에 맞게 이동한다.
2-1. 다음 칸이 경계를 벗어나거나 상어가 있다면, 45도 반시계 회전한다.
2-1-1. 7번의 회전에도 이동할 수 있는 칸이 없다면 이동하지 않는다.
2-2. 다음 칸이 비어있다면, 현재 칸을 비우고 다음 칸으로 이동한다.
2-3. 다음 칸에 물고기가 있다면, 현재 칸과 다음 칸의 물고기 위치를 변경한다.
*/
public class Quiz19236 {

	private static final int[] dy = {10, -1, -1, 0, 1, 1, 1, 0, -1};
	private static final int[] dx = {10, 0, -1, -1, -1, 0, 1, 1, 1};

	private static int max = 0;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int[][] map = new int[4][4];
		Map<Integer, Fish> fishes = new HashMap<>();

		for(int row=0; row<4; row++) {
			String[] line = br.readLine().split(" ");

			for(int col=0, pivot=0; pivot<8; col++, pivot+=2) {
				int fishNumber = Integer.parseInt(line[pivot]);
				int fishDir = Integer.parseInt(line[pivot + 1]);

				map[row][col] = fishNumber;
				fishes.put(fishNumber, new Fish(fishNumber, row, col, fishDir));
			}
		}

		dfs(map, new Shark(0, 0, 0, 0), fishes);
		System.out.println(max);
	}

	private static void dfs(int[][] map, Shark shark, Map<Integer, Fish> fishes) {
		// 1. 현재 상어 위치에 물고기가 없다면 return
		int sr = shark.getRow();
		int sc = shark.getCol();

		if(map[sr][sc] == 0) {
			return;
		}

		// 2. 물고기가 있다면 물고기 번호를 더하고, 방향을 변경
		int fishNumber = map[sr][sc];
		shark.eat(fishes.get(fishNumber));
		fishes.remove(fishNumber);
		map[sr][sc] = 0;

		max = Math.max(max, shark.getSum());

		// 3. 물고기 위치 변경
		updateFishPosition(map, shark, fishes);

		// 4. 상어가 이동가능한 모든 곳으로 이동( 배열 복제 후 전달 )
		int dir = shark.getDir();
		int mr = shark.getRow() + dy[dir];
		int mc = shark.getCol() + dx[dir];
		int sum = shark.getSum();
		while(mr>=0 && mc>=0 && mr<4 && mc<4) {
			dfs(copyArray(map), new Shark(mr, mc, dir, sum), copyMap(fishes));
			mr += dy[dir];
			mc += dx[dir];
		}
	}

	private static void updateFishPosition(int[][] map, Shark shark, Map<Integer, Fish> fishes) {
		int sr = shark.getRow();
		int sc = shark.getCol();
		Set<Integer> numbers = fishes.keySet();
		TreeSet<Integer> orders = new TreeSet<>();
		for(Integer number : numbers) {
			orders.add(number);
		}

		co: for(Integer order : orders) {
			Fish fish = fishes.get(order);
			int dir = fish.getDir();
			int fr = fish.getRow();
			int fc = fish.getCol();

			int mr = fr + dy[dir];
			int mc = fc + dx[dir];
			int count = 0;
			while((mr==sr && mc==sc) || (mr<0 || mc<0 || mr>3 || mc>3)) {
				dir = rotate(dir);
				mr = fr + dy[dir];
				mc = fc + dx[dir];
				count++;
				if(count >= 7) continue co;
			}

			int next = map[mr][mc];
			map[mr][mc] = fish.getNumber();
			map[fr][fc] = next;

			fish.update(mr, mc, dir);
			if(next > 0) {
				Fish nextFish = fishes.get(next);
				nextFish.update(fr, fc, nextFish.getDir());
			}
		}
	}

	private static int[][] copyArray(int[][] arr) {
		int[][] copy = new int[4][4];

		for(int row=0; row<4; row++) {
			for(int col=0; col<4; col++) {
				copy[row][col] = arr[row][col];
			}
		}

		return copy;
	}

	private static Map<Integer, Fish> copyMap(Map<Integer, Fish> fishes) {
		Map<Integer, Fish> copy = new HashMap<>();

		Set<Integer> numbers = fishes.keySet();
		for(Integer number : numbers) {
			copy.put(number, fishes.get(number).copy());
		}

		return copy;
	}

	private static int rotate(int dir) {
		int newDir = dir + 1;
		if(newDir > 8) newDir %= 8;
		return newDir;
	}

	static class Shark {
		private int row;
		private int col;
		private int dir;
		private int sum;

		public Shark(int r, int c, int d, int s) {
			this.row = r;
			this.col = c;
			this.dir = d;
			this.sum = s;
		}

		public void eat(Fish fish) {
			this.dir = fish.getDir();
			this.sum += fish.getNumber();
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

		public int getSum() {
			return this.sum;
		}
	}

	static class Fish {
		private int number;
		private int row;
		private int col;
		private int dir;

		public Fish(int n, int r, int c, int d) {
			this.number = n;
			this.row = r;
			this.col = c;
			this.dir = d;
		}

		public void update(int r, int c, int d) {
			this.row = r;
			this.col = c;
			this.dir = d;
		}

		public int getNumber() {
			return this.number;
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

		public Fish copy() {
			return new Fish(this.number, this.row, this.col, this.dir);
		}
	}
}
