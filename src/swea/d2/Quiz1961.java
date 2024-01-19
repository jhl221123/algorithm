package swea.d2;

import java.util.*;

public class Quiz1961 {
	public static void main(String[] args) throws Exception
	{
		Scanner sc = new Scanner(System.in);
		int T;
		T=sc.nextInt();

		for(int test_case = 1; test_case <= T; test_case++)
		{
			int N = sc.nextInt();
			int[][] board = new int[N][N];
			for(int i=0; i<N; i++) {
				for(int j=0; j<N; j++) {
					board[i][j] = sc.nextInt();
				}
			}
			//  ans는 3개의 열로 고정, 행은 다음 규칙으로 입력
			// 1열: board[N-1][0] ~ board[0][N-1]
			// 2열: board[N-1][N-1] ~ board[0][0]
			// 3열: board[0][N-1] ~ board[N-1][0]
			// 연산시 base: Math.pow(10, N-1); > 이후 10씩 나눈다.
			String[][] ans = new String[N][3];
			//int base = (int)Math.pow(10, N-1);
			//1열
			for(int i=0; i<N; i++) {
				StringBuilder num = new StringBuilder();
				for(int j=N-1; j>=0; j--) {
					num.append(board[j][i]);
				}
				ans[i][0] = String.valueOf(num);
			}
			//2열
			for(int i=N-1, r=0; i>=0; i--, r++) {
				StringBuilder num = new StringBuilder();
				for(int j=N-1; j>=0; j--) {
					num.append(board[i][j]);
				}
				ans[r][1] = String.valueOf(num);
			}
			//3열
			for(int i=N-1, r=0; i>=0; i--, r++) {
				StringBuilder num = new StringBuilder();
				for(int j=0; j<N; j++) {
					num.append(board[j][i]);
				}
				ans[r][2] = String.valueOf(num);
			}
			System.out.println("#"+test_case);
			for(int i=0; i<N; i++) {
				for(int j=0; j<3; j++) {
					System.out.print(ans[i][j] + " ");
				}
				System.out.println();
			}
		}
	}
}
