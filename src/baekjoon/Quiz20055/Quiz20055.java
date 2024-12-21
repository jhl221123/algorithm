package baekjoon.Quiz20055;

import java.util.*;
import java.io.*;

/*

	1. 타일 리스트와 컨베이어 벨트를 따로 관리한다.
	1-1. 각 타일은 식별번호, 내구도, 로봇유무 필드를 가진다.
	2. 가장 먼저 컨베이어 벨트의 타일 순서를 한 칸씩 민다.
	2-1. 타일을 미는 메서드 필요하다.
	2-2. 타일을 밀고나서 N(idx:N-1)번째 타일에 로봇이 있다면 내린다.
	3. N-1~1번의 타일을 확인하면서 로봇이 있다면, 로봇을 다음 칸으로 옮긴다.
	3-1. 다음 칸에 이미 로봇이 있거나, 다음 타일의 내구도가 0이라면 옮기지 않는다.
	3-2. N(idx:N-1)번째 타일 확인 후, 로봇이 있다면 해당 타일에서 로봇을 내린다.
	3-3. 로봇을 옮겼다면, 로봇을 옮긴 타일의 내구도를 1 감소시킨다.
	4. 컨베이어 1번 위치의 타일에 로봇을 올린다.
	4-1. 해당 타일의 내구도가 0이라면 올리지 않는다.
	5. 내구도가 0인 타일의 개수가 K개 이상인지 확인한다.
	5-1. K개 이상이면 종료한다.
	5-2. K개 미만이라면 1단계 올린다.

*/

public class Quiz20055 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		Conveyor conveyor = new Conveyor(N * 2);
		int round = 1;

		// 컨베이어 벨트 초기화
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<N*2; i++) {
			conveyor.addTile(new Tile(i, Integer.parseInt(st.nextToken()), false));
		}

		while(true) {
			conveyor.rotate();
			conveyor.removeRobot();
			conveyor.moveRobot();
			conveyor.addRobot();
			if(!conveyor.healthCheck(K)) break;
			round++;
		}

		System.out.println(round);
	}

	static class Conveyor {
		LinkedList<Tile> conveyor;
		int total;
		int half;

		public Conveyor(int total) {
			this.conveyor = new LinkedList<>();
			this.total = total;
			this.half = total / 2;
		}

		public void addTile(Tile tile) {
			conveyor.add(tile);
		}

		public void rotate() {
			conveyor.addFirst(conveyor.removeLast());
		}

		public void addRobot() {
			Tile startTile = conveyor.get(0);
			if(startTile.enoughLife()) {
				startTile.reduceLife(1);
				startTile.addRobot();
			}
		}

		public void removeRobot() {
			Tile endTile = conveyor.get(half-1);
			endTile.removeRobot();
		}

		public void moveRobot() {
			for(int i=half-2; i>=0; i--) {
				Tile tile = conveyor.get(i);
				if(tile.hasRobot()) {
					Tile next = conveyor.get(i+1);
					if(!next.hasRobot() && next.enoughLife()) {
						next.reduceLife(1);
						next.addRobot();
						tile.removeRobot();
						if(i+1 == half-1) next.removeRobot();
					}
				}
			}
		}

		public boolean healthCheck(int limit) {
			int count = 0;

			for(Tile tile : conveyor) {
				if(!tile.enoughLife()) {
					count++;
				}
			}

			return limit > count;
		}
	}

	static class Tile {
		int no, life;
		boolean hasRobot;

		public Tile(int no, int life, boolean hasRobot) {
			this.no = no;
			this.life = life;
			this.hasRobot = hasRobot;
		}

		public void addRobot() {
			this.hasRobot = true;
		}

		public void removeRobot() {
			this.hasRobot = false;
		}

		public boolean hasRobot() {
			return this.hasRobot;
		}

		public boolean enoughLife() {
			return life > 0;
		}

		public void reduceLife(int size) {
			life -= size;
		}
	}
}
