package baekjoon.Quiz1917;

import java.util.*;
import java.io.*;

public class Quiz1917 {
	static int[][] map;
	static boolean[][] visit;
	static boolean[] selected;
	static int[] dy = {-1, 1, 0, 0};
	static int[] dx = {0, 0, -1, 1};
	static ArrayDeque<Point> ad;
	static boolean result;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = 3;
		StringBuilder sb = new StringBuilder();
		while(T--> 0) {
			// init
			map = new int[6][6];
			visit = new boolean[6][6];
			selected = new boolean[7];
			ad = new ArrayDeque<>();
			result = true;
			// input
			for(int i=0; i<6; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				for(int j=0; j<6; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			// execute
			setStart();
			while(!ad.isEmpty()) {
				Point point = ad.removeFirst();
				int r = point.y;
				int c = point.x;
				Dice dice = point.dice;
				visit[r][c] = true;
				if(selected[dice.getBottom()]) {
					result = false;
					break;
				}
				selected[dice.getBottom()] = true;
				for(int d=0; d<4; d++) {
					int mr = r + dy[d];
					int mc = c + dx[d];
					if(mr < 0 || mc < 0 || mr >= 6 || mc >= 6 || map[mr][mc] != 1 || visit[mr][mc]) continue;
					ad.addLast(new Point(mr, mc, dice.move(d)));
				}
			}
			if(result) sb.append("yes");
			else sb.append("no");
			sb.append("\n");
		}
		System.out.print(sb);
	}
	static void setStart() {
		for(int i=0; i<6; i++) {
			for(int j=0; j<6; j++) {
				if(map[i][j] == 1) {
					ad.addLast(new Point(i, j, new Dice()));
					return;
				}
			}
		}
	}
	static class Point {
		int y, x;
		Dice dice;

		public Point(int y, int x, Dice dice) {
			this.y = y;
			this.x = x;
			this.dice = dice;
		}
	}
	static class Dice {
		int[] dice = {0, 1, 2, 3, 5, 4, 6};;

		public Dice() {};

		public int getBottom() {
			return dice[6];
		}

		public Dice move(int dir) {
			Dice movedDice = new Dice();
			if(dir == 0) { // up
				movedDice.dice[3] = dice[3];
				movedDice.dice[5] = dice[5];
				movedDice.dice[6] = dice[4];
				movedDice.dice[4] = dice[1];
				movedDice.dice[1] = dice[2];
				movedDice.dice[2] = dice[6];
			} else if(dir == 1) { // down
				movedDice.dice[3] = dice[3];
				movedDice.dice[5] = dice[5];
				movedDice.dice[6] = dice[2];
				movedDice.dice[2] = dice[1];
				movedDice.dice[1] = dice[4];
				movedDice.dice[4] = dice[6];
			} else if(dir == 2) { // left
				movedDice.dice[2] = dice[2];
				movedDice.dice[4] = dice[4];
				movedDice.dice[6] = dice[5];
				movedDice.dice[5] = dice[1];
				movedDice.dice[1] = dice[3];
				movedDice.dice[3] = dice[6];
			} else if(dir == 3) { // right
				movedDice.dice[2] = dice[2];
				movedDice.dice[4] = dice[4];
				movedDice.dice[6] = dice[3];
				movedDice.dice[3] = dice[1];
				movedDice.dice[1] = dice[5];
				movedDice.dice[5] = dice[6];
			} else { // error
				movedDice = null;
			}
			return movedDice;
		}
	}
}
