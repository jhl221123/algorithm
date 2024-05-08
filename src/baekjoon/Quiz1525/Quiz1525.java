package baekjoon.Quiz1525;

import java.util.*;
import java.io.*;

/*1. 입력
2. board가 1234567890이 될 때까지 0을 이동
3. 타켓에 도달하면 depth를 반환
4. 반복이 끝날때까지 도달하지 못하면 -1을 반환*/
public class Quiz1525 {
	static Set<Integer> check = new HashSet<>();
	static ArrayDeque<Search> ad = new ArrayDeque<>();
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		for(int i=0; i<3; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int j=0; j<3; j++) {
				Puzzle.puzzle[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		int startBoard = Puzzle.encode();
		check.add(startBoard);
		ad.addLast(new Search(startBoard, 0));
		while(!ad.isEmpty()) {
			Search search = ad.removeFirst();
			int board = search.board;
			int depth = search.depth;
			if(board == 123456780) {
				System.out.println(depth);
				return;
			}
			Puzzle.decode(board);
			for(int d=0; d<4; d++) {
				int movedBoard = Puzzle.move(d);
				if(movedBoard == -1 || check.contains(movedBoard)) continue;
				check.add(movedBoard);
				ad.addLast(new Search(movedBoard, depth+1));
			}
		}
		System.out.println(-1);
	}
	static class Search {
		int board, depth;

		public Search(int board, int depth) {
			this.board = board;
			this.depth = depth;
		}
	}
	static class Puzzle {
		static int[][] puzzle = new int[3][3];
		static int[] zeroIdx = new int[2];

		static int encode() {
			int code = 0;
			for(int i=0; i<3; i++) {
				for(int j=0; j<3; j++) {
					code = code * 10 + puzzle[i][j];
				}
			}
			return code;
		}
		static void decode(int board) {
			for(int i=2; i>=0; i--) {
				for(int j=2; j>=0; j--) {
					puzzle[i][j] = board % 10;
					if(puzzle[i][j] == 0) {
						zeroIdx[0] = i;
						zeroIdx[1] = j;
					}
					board /= 10;
				}
			}
		}
		static int move(int d) {
			int movedBoard = -1;
			int R = zeroIdx[0];
			int C = zeroIdx[1];
			if(d == 0) { // up
				if(R > 0) {
					swap(R, C, R-1, C);
					movedBoard = encode();
					swap(R, C, R-1, C);
				}
			} else if(d == 1) { // down
				if(R < 2) {
					swap(R, C, R+1, C);
					movedBoard = encode();
					swap(R, C, R+1, C);
				}
			} else if(d == 2) { // left
				if(C > 0) {
					swap(R, C, R, C-1);
					movedBoard = encode();
					swap(R, C, R, C-1);
				}
			} else if(d == 3) {
				if(C < 2) {
					swap(R, C, R, C+1);
					movedBoard = encode();
					swap(R, C, R, C+1);
				}
			} else { // error
				movedBoard = -2;
			}
			return movedBoard;
		}
	}
	static void swap(int r1, int c1, int r2, int c2) {
		int temp = Puzzle.puzzle[r1][c1];
		Puzzle.puzzle[r1][c1] = Puzzle.puzzle[r2][c2];
		Puzzle.puzzle[r2][c2] = temp;
	}
}
