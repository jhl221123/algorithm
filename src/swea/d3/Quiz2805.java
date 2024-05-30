package swea.d3;

import java.util.Scanner;

public class Quiz2805 {
	public static void main(String[] args) throws Exception
	{
		Scanner sc = new Scanner(System.in);
		int T;
		T=sc.nextInt();

		for(int test_case = 1; test_case <= T; test_case++)
		{
			int N = sc.nextInt();
			int[][] arr = new int[N][N];
			for(int i=0; i<N; i++) {
				String str = sc.next();
				for(int j=0; j<N; j++) {
					arr[i][j] = str.charAt(j)-'0';
				}
			}
			int sum = 0;
			int base = N/2;
			boolean flag = false;
			for(int i=0; i<N; i++) {
				for(int j=N-base-1; j<=base; j++) {
					sum += arr[i][j];
				}
				if(!flag) base += 1;
				else base -= 1;
				if(base == N-1) flag = true;
			}
			System.out.println("#"+test_case+" "+sum);
		}
	}
}
