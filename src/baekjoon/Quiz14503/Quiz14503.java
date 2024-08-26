package baekjoon.Quiz14503;

import java.util.*;
import java.io.*;

public class Quiz14503 {
	static int N, M;
	static int[][] map;
	static boolean[][] visited;
	static int[] dy = {-1, 0, 1, 0};
	static int[] dx = {0, 1, 0, -1};
	static final int FORWARD = 1;
	static final int BACKWARD = 2;
	public static void main(String[] args) throws IOException {
		// init
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		visited = new boolean[N][M];

		// robot init
		st = new StringTokenizer(br.readLine());
		int sr = Integer.parseInt(st.nextToken());
		int sc = Integer.parseInt(st.nextToken());
		int sd = Integer.parseInt(st.nextToken());
		Robot robot = new Robot(sr, sc, sd);

		// map init
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		// cleanning room
		boolean isRunning = true;
		int cnt = 1;
		visited[sr][sc] = true;
		while(isRunning) {
			//if(robot.y == 9 && robot.x == 3) return;
			//System.out.println("Y: " + robot.y + ", X: " + robot.x + ", cnt: " + cnt);
			if(canCleanRoom(robot, map, visited)) {
				robot.turnLeft();
				if(robot.canMoveTo(FORWARD, map, visited)) {
					robot.moveTo(FORWARD);
					cnt++;
					visited[robot.y][robot.x] = true;
				}
			}
			else {
				if(robot.canMoveTo(BACKWARD, map, visited)) robot.moveTo(BACKWARD);
				else isRunning = false;
			}
			// System.out.println("-> Y: " + robot.y + ", X: " + robot.x + ", cnt: " + cnt);
			// for(int i=0; i<N; i++) {
			// 	for(int j=0; j<M; j++) {
			// 		System.out.print((visited[i][j] ? 1 : 0) + " ");
			// 	}
			// 	System.out.println();
			// }
			// System.out.println();
		}

		System.out.println(cnt);
	}

	public static boolean canCleanRoom(Robot robot, int[][] map, boolean[][] visited) {
		int y = robot.y;
		int x = robot.x;
		for(int d=0; d<4; d++) {
			int my = y + dy[d];
			int mx = x + dx[d];
			if(!robot.isIn(my, mx, map)) continue;
			if(map[my][mx] == 1) continue;
			if(visited[my][mx]) continue;
			return true;
		}
		return false;
	}

	static class Robot {
		int y, x, d;

		public Robot(int y, int x, int d) {
			this.y = y;
			this.x = x;
			this.d = d;
		}

		public void turnLeft() {
			d--;
			if(d<0) d=3;
		}

		public boolean canMoveTo(int dir, int[][] map, boolean[][] visited) {
			return switch(dir) {
				case FORWARD ->
					isIn(y+dy[d], x+dx[d], map)
						&& map[y+dy[d]][x+dx[d]] != 1
						&& !visited[y+dy[d]][x+dx[d]];
				case BACKWARD ->
					isIn(y + dy[(d + 2) % 4], x + dx[(d + 2) % 4], map)
						&& map[y + dy[(d + 2) % 4]][x + dx[(d + 2) % 4]] != 1;
				default -> false;
			};
		}

		public void moveTo(int dir) {
			switch(dir) {
				case FORWARD -> {
					y += dy[d];
					x += dx[d];
				}
				case BACKWARD -> {
					y += dy[(d+2)%4];
					x += dx[(d+2)%4];
				}
			}
		}

		public boolean isIn(int y, int x, int[][] map) {
			int N = map.length;
			int M = map[0].length;
			return y >= 0 && x >= 0 && y < N && x < M;
		}
	}
}
