package baekjoon.Quiz17136;

import java.util.Scanner;

// back: 현재 개수가 결과보다 크거나 같다면 return
// base: 종이 끝까지 도달하면 현재 개수를 대입
// recur: 종이 크기 별로 부착 시도(재귀를 통해 모든 경우 계산 시도), 종이 개수가 0이라면 continue; 부착 불가능하면 continue;
public class Quiz17136 {
	static int[][] board = new int[10][10];
	static int[] paper = new int[]{5, 5, 5, 5, 5};
	static int result = 26;
	static int nextR;
	static int nextC;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		for(int i=0; i<10; i++) {
			for(int j=0; j<10; j++) {
				board[i][j] = sc.nextInt();
			}
		}
		recur(0,0,0);
		System.out.println(result != 26 ? result : -1);
	}
	private static void recur(int r, int c, int cnt) {
		if(result <= cnt) return;
		nextPoint(r, c);
		if(nextR == -1 && nextC == -1) {
			result = cnt;
			return;
		}
		int nr = nextR;
		int nc = nextC;
		for(int i=0; i<5; i++) {
			if(paper[i]==0) continue;
			if(!isValid(nr, nc, i+1)) continue;
			paper[i]--;
			fill(nr, nc, i+1, 0);
			recur(nr, nc, cnt+1);
			fill(nr, nc, i+1, 1);
			paper[i]++;
		}
	}
	private static void nextPoint(int r, int c) {
		for(int i=r; i<10; i++) {
			for(int j=0; j<10; j++) {
				if(board[i][j] == 1) {
					nextR = i;
					nextC = j;
					return;
				}
			}
		}
		nextR = -1;
		nextC = -1;
	}
	private static boolean isValid(int r, int c, int size) {
		if(r+size >10||c+size>10) return false;
		for(int i=0; i<size; i++) {
			for(int j=0; j<size; j++) {
				if(board[r+i][c+j] != 1) return false;
			}
		}
		return true;
	}
	private static void fill(int r, int c, int size, int color) {
		for(int i=0; i<size; i++) {
			for(int j=0; j<size; j++) {
				board[r+i][c+j] = color;
			}
		}
	}
}
