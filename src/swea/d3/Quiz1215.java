package swea.d3;

import java.util.*;

public class Quiz1215 {
	public static void main(String args[]) throws Exception
	{
		Scanner sc = new Scanner(System.in);
		int T=10;
		for(int test_case = 1; test_case <= T; test_case++)
		{
			// isPall 메서드 활용해서 모든 행과 열을 대상으로 탐색
			// N이 짝수일 경우 r=N/2, 홀수일 경우 r=N/2+1
			int N = sc.nextInt();
			char[][] board = new char[8][8];
			for(int i=0; i<8; i++) {
				String str = sc.next();
				for(int j=0; j<8; j++) {
					board[i][j] = str.charAt(j);
				}
			}
			int count =0;
			for(int i=0; i<8; i++) {
				for(int j=0; j<=8-N; j++) {
					if(isRowPallin(board, i, j, N)) {
						count++;
					}
					if(isColPallin(board, j, i, N)) {
						count++;
					}
				}
			}
			System.out.println("#" + test_case + " " +count);
		}
	}
	private static boolean isRowPallin(char[][] board, int r, int c, int N) {
		int[] arr = new int[N];
		for(int i=c; i<c+N; i++) {
			arr[i-c] = board[r][i];
		}
		int left = N / 2 - 1;
		int right = N % 2 == 0 ? N / 2 : N / 2 + 1;
		while(left>=0) {
			if(arr[left--] != arr[right++]) return false;
		}
		return true;
	}
	private static boolean isColPallin(char[][] board, int r, int c, int N) {
		int[] arr = new int[N];
		for(int i=r; i<r+N; i++) {
			arr[i-r] = board[i][c];
		}
		int left = N / 2 - 1;
		int right = N % 2 == 0 ? N / 2 : N / 2 + 1;
		while(left>=0) {
			if(arr[left--] != arr[right++]) return false;
		}
		return true;
	}
}
