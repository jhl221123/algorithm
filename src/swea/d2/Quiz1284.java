package swea.d2;

import java.util.*;

public class Quiz1284 {
	public static void main(String[] args) throws Exception
	{
		Scanner sc = new Scanner(System.in);
		int T;
		T=sc.nextInt();

		for(int test_case = 1; test_case <= T; test_case++)
		{
			// 두 회사 모두 계산 후 Math.min으로 답 도출
			int P = sc.nextInt();
			int Q = sc.nextInt();
			int R = sc.nextInt();
			int S = sc.nextInt();
			int W = sc.nextInt();
			int rateA = P * W;
			int rateB;
			if(W>R) rateB = Q+((W-R)*S);
			else rateB = Q;
			System.out.println("#"+test_case+" "+Math.min(rateA, rateB));
		}
	}
}
