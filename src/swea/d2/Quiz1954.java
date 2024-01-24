package swea.d2;

import java.util.*;

public class Quiz1954 {
	public static void main(String[] args) throws Exception
	{
		Scanner sc = new Scanner(System.in);
		int T;
		T=sc.nextInt();
		for(int test_case = 1; test_case <= T; test_case++)
		{
			// 열 이동 -> N-1에 도달하면 행 이동 -> N-1에 도달하면 열 이동
			// 행과 열이 N-1에 도달해서 전환시 idx가 0이라면 플래그: +, idx가 N-1이라면 플래그: -
			int N = sc.nextInt();
			int[][] arr = new int[N][N];
			boolean[][] check = new boolean[N][N];
			int num = 1;
			int r=0;
			int c=0;
			boolean turnOfRow = false;
			boolean rowMinus = false;
			boolean colMinus = false;
			while(num<=N*N) {
				if(!turnOfRow) {
					if(!rowMinus) {
						// 열 증가
						arr[r][c++] = num++;
						check[r][c-1] = true;
						if(c>=N || check[r][c]) {
							c--;
							r++;
							turnOfRow = !turnOfRow;
							rowMinus = !rowMinus;
						}
					} else {
						// 열 감소
						arr[r][c--] = num++;
						check[r][c+1] = true;
						if(c<0 || check[r][c]) {
							c++;
							r--;
							turnOfRow = !turnOfRow;
							rowMinus = !rowMinus;
						}
					}
				} else {
					if(!colMinus) {
						// 행 증가
						arr[r++][c] = num++;
						check[r-1][c] = true;
						if(r>=N || check[r][c]) {
							r--;
							c--;
							turnOfRow = !turnOfRow;
							colMinus = !colMinus;
						}
					} else {
						// 행 감소
						arr[r--][c] = num++;
						check[r+1][c] = true;
						if(r<0 || check[r][c]) {
							r++;
							c++;
							turnOfRow = !turnOfRow;
							colMinus = !colMinus;
						}
					}
				}
			}
			System.out.println("#" + test_case);
			for(int i=0; i<N; i++) {
				for(int j=0; j<N; j++) {
					System.out.print(arr[i][j] + " ");
				}
				System.out.println();
			}
		}
	}
}
