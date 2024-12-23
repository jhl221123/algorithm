package baekjoon.Quiz3190;

import java.util.*;
import java.io.*;

/*
1. 현재 방향에 맞게 뱀이 이동한다.
1-1. 이동한 곳이 격자 밖이거나, 뱀의 위치라면 종료한다.
1-2. 이동한 곳의 사과 유무와 관계없이 현재 위치를 뱀 머리 부분에 추가한다.
1-3. 이동한 곳에 사과가 없다면, 뱀 꼬리 부분의 좌표를 ad에서 제거한다.
2. 시간을 증가시킨다.
2-2. 다음 방향변환 정보와 현재 시간을 비교한다.
2-3. 방향변환 시간이라면 뱀의 방향을 변경하고, 방향 변환 정보를 q에서 poll한다.
*/

public class Quiz3190 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int K = Integer.parseInt(br.readLine());
		boolean[][] map = new boolean[N][N];
		int[] dy = {-1, 1, 0, 0};
		int[] dx = {0, 0, -1, 1};

		for(int i=0; i<K; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int r = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			map[r-1][c-1] = true;
		}

		int L = Integer.parseInt(br.readLine());
		ArrayDeque<Switch> switchs = new ArrayDeque<>();
		for(int i=0; i<L; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int time = Integer.parseInt(st.nextToken());
			String dir = st.nextToken();
			switchs.addLast(new Switch(time, dir));
		}

		int totalTime = 0;
		Snake snake = new Snake(N, new int[]{0, 0}, 3);
		Switch nextSwitch = switchs.removeFirst();
		while(!snake.isDie()) {
			int[] cur = snake.getHead();
			int dir = snake.getDir();
			int[] next = new int[] {cur[0] + dy[dir], cur[1] + dx[dir]};
			if(snake.overMap(next) || snake.exist(next)) {
				snake.kill();
				break;
			}
			snake.move(next, map[next[0]][next[1]]);
			map[next[0]][next[1]] = false;
			totalTime++;
			if(totalTime == nextSwitch.getTime()) {
				snake.turn(nextSwitch.getDir());
				if(!switchs.isEmpty()) nextSwitch = switchs.removeFirst();
			}
		}

		System.out.println(totalTime+1);
	}

	static class Snake {
		ArrayDeque<int[]> body = new ArrayDeque<>();
		boolean[][] exist;
		int dir;
		boolean die;

		public Snake(int N, int[] init, int dir) {
			this.body.addFirst(init);
			this.exist = new boolean[N][N];
			this.exist[init[0]][init[1]] = true;
			this.dir = dir;
			this.die = false;
		}

		public int[] getHead() {
			return body.getFirst();
		}

		public int getDir() {
			return this.dir;
		}

		public void move(int[] spot, boolean existApple) {
			this.body.addFirst(spot);
			this.exist[spot[0]][spot[1]] = true;
			if(!existApple) {
				int[] revSpot = this.body.removeLast();
				this.exist[revSpot[0]][revSpot[1]] = false;
			}
		}

		public boolean overMap(int[] spot) {
			return spot[0] < 0 || spot[1] < 0 || spot[0] >= exist.length || spot[1] >= exist.length;
		}

		public boolean exist(int[] spot) {
			return exist[spot[0]][spot[1]];
		}

		public void turn(String d) {
			if("L".equals(d)) {
				if(dir == 0) dir = 2;
				else if(dir == 1) dir = 3;
				else if(dir == 2) dir = 1;
				else if(dir == 3) dir = 0;
			} else if("D".equals(d)) {
				if(dir == 0) dir = 3;
				else if(dir == 1) dir = 2;
				else if(dir == 2) dir = 0;
				else if(dir == 3) dir = 1;
			}
		}

		public void kill() {
			this.die = true;
		}

		public boolean isDie() {
			return this.die;
		}
	}

	static class Switch {
		int time;
		String dir;

		public Switch(int time, String dir) {
			this.time = time;
			this.dir = dir;
		}

		public int getTime() {
			return this.time;
		}

		public String getDir() {
			return this.dir;
		}
	}
}
