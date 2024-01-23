package swea.d2;

import java.util.*;

public class Quiz1959 {
	public static void main(String[] args) throws Exception
	{
		Scanner sc = new Scanner(System.in);
		int T;
		T=sc.nextInt();

		for(int test_case = 1; test_case <= T; test_case++)
		{
			// N과 M 중 더 큰수가 외부 반복
			// 작은 수는 내부 반복
			int N = sc.nextInt();
			int M = sc.nextInt();
			int[] arrN = new int[N];
			int[] arrM = new int[M];
			for(int i=0; i<N; i++) {
				arrN[i] = sc.nextInt();
			}
			for(int i=0; i<M; i++) {
				arrM[i] = sc.nextInt();
			}
			int externalEnd = 0;
			int internalEnd = 0;
			int[] exArr;
			int[] inArr;
			if(N>M) {
				externalEnd = N;
				internalEnd = M;
				exArr = arrN;
				inArr = arrM;
			} else {
				externalEnd = M;
				internalEnd = N;
				exArr = arrM;
				inArr = arrN;
			}
			int maxValue = 0;
			for(int i=0; i<=externalEnd - internalEnd; i++) {
				int currentValue =0;
				for(int j=0; j<internalEnd; j++) {
					currentValue += exArr[i+j] * inArr[j];
				}
				maxValue = Math.max(maxValue, currentValue);
			}
			System.out.println("#"+test_case+ " " + maxValue);
		}
	}
}
