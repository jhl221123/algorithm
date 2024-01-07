package swea.d2;

import java.util.*;

// 전체 시간 복잡도: O(N*N)
public class Quiz2001 {
	public static void main(String[] args) throws Exception
	{
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();

		for(int test_case = 1; test_case <= T; test_case++)
		{
			int N = sc.nextInt();
			int M = sc.nextInt();
			int[][] acc = new int[N+1][N+1];
			for(int i=1; i<=N; i++) {
				for(int j=1; j<=N; j++) {
					acc[i][j] = acc[i-1][j] + acc[i][j-1] - acc[i-1][j-1] + sc.nextInt();
				}
			}
			int ans = 0;
			for(int i=1; i<=N-M+1; i++) {
				for(int j=1; j<=N-M+1; j++) {
					int sum = acc[i+M-1][j+M-1] - acc[i-1][j+M-1] - acc[i+M-1][j-1] + acc[i-1][j-1];
					ans = Math.max(ans, sum);
				}
			}
			System.out.println("#" + test_case + " " + ans);
		}
	}
}
