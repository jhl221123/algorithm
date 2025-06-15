package baekjoon.Quiz27114;

import java.io.*;
import java.util.*;

/*
Gold4: 조교의 맹연습 / [dp]
*/
public class Quiz27114 {

	private static final int INIT = 0;
	private static final int LEFT = 1;
	private static final int RIGHT = 2;
	private static final int BACK = 3;
	private static final int INF = Integer.MAX_VALUE - 10;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] ABCK = br.readLine().split(" ");
		int A = Integer.parseInt(ABCK[0]);
		int B = Integer.parseInt(ABCK[1]);
		int C = Integer.parseInt(ABCK[2]);
		int K = Integer.parseInt(ABCK[3]);
		int[] ABC = new int[] {-1, A, B, C};

		int[][] dp = new int[K + 1][4];
		for(int i=0; i<=K; i++) {
			Arrays.fill(dp[i], INF);
		}
		dp[0][INIT] = 0;

		for(int k=1; k<=K; k++) {
			for(int to=0; to<4; to++) {
				for(int dir=LEFT; dir<=BACK; dir++) {
					int energy = ABC[dir];
					if(k < energy) continue;
					dp[k][to] = Math.min(dp[k][to], dp[k - energy][from(to, dir)] + 1);
				}
			}
		}

		System.out.println(dp[K][0] == INF ? -1 : dp[K][0]);
	}

	private static int from(int to, int dir) {
		if(to == INIT) {
			if(dir == LEFT) return RIGHT;
			if(dir == RIGHT) return LEFT;
			if(dir == BACK) return BACK;
			else throw new RuntimeException("INIT ERROR!");
		}
		if(to == LEFT) {
			if(dir == LEFT) return INIT;
			if(dir == RIGHT) return BACK;
			if(dir == BACK) return RIGHT;
			else throw new RuntimeException("LEFT ERROR!");
		}
		if(to == RIGHT) {
			if(dir == LEFT) return BACK;
			if(dir == RIGHT) return INIT;
			if(dir == BACK) return LEFT;
			else throw new RuntimeException("RIGHT ERROR!");
		}
		if(to == BACK) {
			if(dir == LEFT) return LEFT;
			if(dir == RIGHT) return RIGHT;
			if(dir == BACK) return INIT;
			else throw new RuntimeException("BACK ERROR!");
		}
		else throw new RuntimeException("turn ERROR!");
	}
}
