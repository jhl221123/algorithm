package swea.d2;

import java.util.*;

// 전체 시간 복잡도: O(N)
public class Quiz1986 {
	public static void main(String[] args) throws Exception
	{
		Scanner sc = new Scanner(System.in);
		int T;
		T=sc.nextInt();

		for(int test_case = 1; test_case <= T; test_case++)
		{
			int N = sc.nextInt();
			int sum = 1;
			for(int i=2; i<=N; i++) {
				if(i%2==0) sum-=i;
				else sum+=i;
			}
			System.out.println("#"+test_case+" "+sum);
		}
	}
}
