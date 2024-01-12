package swea.d2;

import java.util.*;

public class Quiz1979 {
	public static void main(String[] args) throws Exception
	{
		Scanner sc = new Scanner(System.in);
		int T;
		T=sc.nextInt();
		for(int test_case = 1; test_case <= T; test_case++)
		{
			// 각 행, 열별 길이 측정
			// 1이라면 현재 길이 + 1, 0이라면 현재 길이와 K 비교 후 현재 길이 초기화
			int N = sc.nextInt();
			int K = sc.nextInt();
			int[][] board = new int[N][N];
			int currentLength = 0;
			int count = 0;
			for(int i=0; i<N; i++) {
				for(int j=0; j<N; j++) {
					int target = sc.nextInt();
					if(target == 1) currentLength++;
					else {
						if(currentLength == K) count++;
						currentLength = 0;
					}
					board[i][j] = target;
				}
				if(currentLength == K) count++;
				currentLength = 0;
			}
			for(int i=0; i<N; i++) {
				for(int j=0; j<N; j++) {
					int target = board[j][i];
					if(target == 1) currentLength++;
					else {
						if(currentLength == K) count++;
						currentLength = 0;
					}
				}
				if(currentLength == K) count++;
				currentLength = 0;
			}
			System.out.println("#" + test_case + " " + count);
		}
	}
}
