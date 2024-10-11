package baekjoon.Quiz14499;

import java.io.*;
import java.util.*;

public class Quiz14499 {

	public static final int RIGHT = 1;
	public static final int LEFT = 2;
	public static final int TOP = 3;
	public static final int BOTTOM = 4;
	static int N, M;
	static int[] dy = {-100, 0, 0, -1, 1};
	static int[] dx = {-100, 1, -1, 0, 0};

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		int r = Integer.parseInt(st.nextToken());
		int c = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());

		int[][] map = new int[N][M];
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		Dice dice = new Dice(r, c);
		StringBuilder sb = new StringBuilder();

		st = new StringTokenizer(br.readLine());
		for(int i=0; i<K; i++) {
			int dir = Integer.parseInt(st.nextToken());
			int mr = dice.r + dy[dir];
			int mc = dice.c + dx[dir];
			if(!isIn(mr, mc)) continue;
			dice.move(dir);
			if(map[mr][mc] == 0) {
				map[mr][mc] = dice.getBottom();
			} else {
				dice.changeBottom(map[mr][mc]);
				map[mr][mc] = 0;
			}
			sb.append(dice.getTop()).append("\n");
		}

		System.out.print(sb);
	}

	static class Dice {
		int r, c;
		int[] values = new int[7];

		public Dice(int r, int c) {
			this.r = r;
			this.c = c;
		}

		private int getBottom() {
			return values[6];
		}

		private int getTop() {
			return values[1];
		}

		private void changeBottom(int num) {
			values[6] = num;
		}

		private void move(int dir) {
			switch(dir) {
				case RIGHT -> right();
				case LEFT -> left();
				case TOP -> top();
				case BOTTOM -> bottom();
			}
		}

		// 지도 위의 1~6 까지의 번호 위치는 항상 고정, 대신 이동할 때마다 값이 변경
		private void right() {
			values[0] = values[6];
			values[6] = values[3];
			values[3] = values[1];
			values[1] = values[4];
			values[4] = values[0];
			c++;
		}
		private void left() {
			values[0] = values[6];
			values[6] = values[4];
			values[4] = values[1];
			values[1] = values[3];
			values[3] = values[0];
			c--;
		}
		private void top() {
			values[0] = values[6];
			values[6] = values[2];
			values[2] = values[1];
			values[1] = values[5];
			values[5] = values[0];
			r--;
		}
		private void bottom() {
			values[0] = values[6];
			values[6] = values[5];
			values[5] = values[1];
			values[1] = values[2];
			values[2] = values[0];
			r++;
		}
	}
	private static boolean isIn(int r, int c) {
		return r >= 0 && c >= 0 && r < N && c < M;
	}
}
