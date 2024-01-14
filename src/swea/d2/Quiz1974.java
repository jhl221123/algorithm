package swea.d2;

import java.util.*;

public class Quiz1974 {
	public static void main(String[] args) throws Exception
	{
		Scanner sc = new Scanner(System.in);
		int T;
		T=sc.nextInt();

		for(int test_case = 1; test_case <= T; test_case++)
		{
			int[][] board = new int[9][9];
			for(int i=0; i<9; i++) {
				for(int j=0; j<9; j++) {
					board[i][j] = sc.nextInt();
				}
			}
			int result = 1;
			// 각 행 확인
			for(int i=0; i<9; i++) {
				boolean[] check = new boolean[9];
				for(int j=0; j<9; j++) {
					if(check[board[i][j]-1]) result = 0;
					check[board[i][j]-1] = true;
				}
			}
			// 각 열 확인
			for(int i=0; i<9; i++) {
				boolean[] check = new boolean[9];
				for(int j=0; j<9; j++) {
					if(check[board[j][i]-1]) result = 0;
					check[board[j][i]-1] = true;
				}
			}
			// 각 region 확인
			for(int i=0; i<9; i+=3) {
				for(int j=0; j<9; j+=3) {
					if(!isPossible(i, j, board)) result = 0;
				}
			}
			System.out.println("#"+test_case+" "+result);
		}
	}
	private static boolean isPossible(int row, int col, int[][] board) {
		boolean[] check = new boolean[9];
		for(int i=row; i<row+3; i++) {
			for(int j=col; j<col+3; j++) {
				if(check[board[i][j]-1]) return false;
				check[board[i][j]-1] = true;
			}
		}
		return true;
	}
}
