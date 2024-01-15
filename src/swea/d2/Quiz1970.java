package swea.d2;

import java.util.*;

public class Quiz1970 {
	public static void main(String[] args) throws Exception
	{
		Scanner sc = new Scanner(System.in);
		int T;
		T=sc.nextInt();

		for(int test_case = 1; test_case <= T; test_case++)
		{
			int N = sc.nextInt();
			int[] money = new int[8];
			while(N>0) {
				if(N>=50000) {
					money[0] += N / 50000;
					N %= 50000;
				} else if(N>=10000) {
					money[1] += N / 10000;
					N %= 10000;
				} else if(N>=5000) {
					money[2] += N / 5000;
					N %= 5000;
				} else if(N>=1000) {
					money[3] += N / 1000;
					N %= 1000;
				} else if(N>=500) {
					money[4] += N / 500;
					N %= 500;
				} else if(N>=100) {
					money[5] += N / 100;
					N %= 100;
				} else if(N>=50) {
					money[6] += N / 50;
					N %= 50;
				} else if(N>=10) {
					money[7] += N / 10;
					N %= 10;
				} else break;
			}
			System.out.println("#"+test_case);
			for(int i=0; i<8; i++) {
				System.out.print(money[i] + " ");
			}
			System.out.println();
		}
	}
}
