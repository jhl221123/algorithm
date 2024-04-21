package baekjoon.Quiz2580;

import java.util.*;
import java.io.*;

public class Quiz2580 {
	static int[][] board = new int[9][9];
	static Set<Integer>[] rows = new Set[9];
	static Set<Integer>[] cols = new Set[9];
	static Set<Integer>[] region = new Set[9];
	static int[][][] idx = new int[3][9][9];
	public static void main(String[] args) throws IOException {
		for(int i=0; i<9; i++) {
			rows[i] = new HashSet<>(9);
			cols[i] = new HashSet<>(9);
			region[i] = new HashSet<>(9);
		}
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		for(int i=0; i<9; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int j=0; j<9; j++) {
				board[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		initIdx();
		initSpot();
		dfs(0, 0);
		StringBuilder sb = new StringBuilder();
		for(int i=0; i<9; i++) {
			for(int j=0; j<9; j++) {
				sb.append(board[i][j]).append(" ");
			}
			sb.append("\n");
		}
		System.out.print(sb);
	}
	private static boolean dfs(int r, int c) {
		for(int i=r; i<9; i++) {
			for(int j=c; j<9; j++) {
				if(board[i][j] != 0) continue;
				for(int k=1; k<=9; k++) {
					int rowIdx = idx[0][i][j];
					int colIdx = idx[1][i][j];
					int regionIdx = idx[2][i][j];
					// 행, 열, 구역에 이미 숫자가 있다면 돌아간다.
					if(rows[rowIdx].contains(k)
						|| cols[colIdx].contains(k)
						|| region[regionIdx].contains(k)) {
						if(k==9) return false;
					}
					// 새로운 숫자라면 선택하고 재귀
					else {
						rows[rowIdx].add(k);
						cols[colIdx].add(k);
						region[regionIdx].add(k);
						board[i][j] = k;
						if(dfs(i, 0)) return true;
						board[i][j] = 0;
						rows[rowIdx].remove(k);
						cols[colIdx].remove(k);
						region[regionIdx].remove(k);
					}
					if(k==9) return false;
				}
			}
		}
		// 마지막 숫자까지 채웠다면 종료
		return true;
	}
	private static void initSpot() {
		for(int i=0; i<9; i++) {
			for(int j=0; j<9; j++) {
				if(board[i][j] != 0) {
					int num = board[i][j];
					rows[idx[0][i][j]].add(num);
					cols[idx[1][i][j]].add(num);
					region[idx[2][i][j]].add(num);
				}
			}
		}
	}
	private static void initIdx() {
		for(int i=0; i<9; i++) {
			for(int j=0; j<9; j++) {
				idx[0][i][j] = i;
				idx[1][j][i] = i;
				if(i<3) {
					if(j<3) idx[2][i][j] = 0;
					else if(j<6) idx[2][i][j] = 1;
					else idx[2][i][j] = 2;
				}
				else if(i<6) {
					if(j<3) idx[2][i][j] = 3;
					else if(j<6) idx[2][i][j] = 4;
					else idx[2][i][j] = 5;
				}
				else {
					if(j<3) idx[2][i][j] = 6;
					else if(j<6) idx[2][i][j] = 7;
					else idx[2][i][j] = 8;
				}
			}
		}
	}
}
