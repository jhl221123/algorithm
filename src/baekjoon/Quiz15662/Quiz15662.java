package baekjoon.Quiz15662;

import java.util.*;
import java.io.*;

public class Quiz15662 {
	public static final int CLOCK = 1;
	public static final int REVERSE = -1;
	static int T, K;
	static Gear[] gears;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		T = Integer.parseInt(br.readLine());
		gears = new Gear[T + 1];

		for(int i=1; i<=T; i++) {
			char[] poles = br.readLine().toCharArray();
			gears[i] = new Gear(poles);
		}

		K = Integer.parseInt(br.readLine());
		for(int i=0; i<K; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int num = Integer.parseInt(st.nextToken());
			int dir = Integer.parseInt(st.nextToken());
			rotate(num, dir);
		}

		int count = countSPoleFrom(gears);
		System.out.println(count);
	}

	private static int countSPoleFrom(Gear[] gears) {
		int count = 0;
		for(int i=1; i<=T; i++) {
			if(gears[i].poles[0] == '1') count++;
		}
		return count;
	}

	private static void rotate(int num, int dir) {
		int reverseDir = reverseDirection(dir);
		if(num > 1 && !gears[num].sameOf(6, gears[num-1].poles[2])) {
			moveLeft(num - 1, reverseDir);
		}
		if(num < T && !gears[num].sameOf(2, gears[num+1].poles[6])) {
			moveRight(num + 1, reverseDir);
		}
		gears[num].turn(dir);
	}

	private static void moveLeft(int num, int dir) {
		int reverseDir = reverseDirection(dir);
		if(num > 1 && !gears[num].sameOf(6, gears[num-1].poles[2])) {
			moveLeft(num - 1, reverseDir);
		}
		gears[num].turn(dir);
	}

	private static void moveRight(int num, int dir) {
		int reverseDir = reverseDirection(dir);
		if(num < T && !gears[num].sameOf(2, gears[num+1].poles[6])) {
			moveRight(num + 1, reverseDir);
		}
		gears[num].turn(dir);
	}

	private static int reverseDirection(int dir) {
		return dir * -1;
	}

	static class Gear {
		char[] poles;

		public Gear(char[] poles) {
			this.poles = poles;
		}

		public void turn(int d) {
			switch(d) {
				case CLOCK -> clock();
				case REVERSE -> reverse();
			}
		}

		public boolean sameOf(int idx, char pole) {
			return poles[idx] == pole;
		}

		private void clock() {
			char temp = poles[7];
			for(int i=7; i>0; i--) {
				poles[i] = poles[i-1];
			}
			poles[0] = temp;
		}

		private void reverse() {
			char temp = poles[0];
			for(int i=1; i<8; i++) {
				poles[i-1] = poles[i];
			}
			poles[7] = temp;
		}
	}
}
