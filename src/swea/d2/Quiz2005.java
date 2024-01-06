package swea.d2;

import java.util.*;

public class Quiz2005 {
	public static void main(String[] args) throws Exception
	{
		Scanner sc = new Scanner(System.in);
		int T;
		T=sc.nextInt();

		for(int test_case = 1; test_case <= T; test_case++)
		{
			// N행은 N열을 가지는 2차원 배열 생성
			int N = sc.nextInt();
			int[][] arr = new int[N][];
			for(int i=0; i<N; i++) {
				arr[i] = new int[i+1];
			}
			arr[0][0] = 1;
			// 각 행의 0, 행과 같은 열은 1
			// 아니라면 이전 행의 이전 열과 현재 열의 합을 입력
			for(int i=1; i<N; i++) {
				for(int j=0; j<=i; j++) {
					if(j==0 || j==i) arr[i][j] = 1;
					else arr[i][j] = arr[i-1][j-1] + arr[i-1][j];
				}
			}
			System.out.println("#"+test_case + " ");
			for(int i=0; i<N; i++) {
				for(int j=0; j<i+1; j++) {
					System.out.print(arr[i][j] + " ");
				}
				System.out.println();
			}
		}
	}
}
