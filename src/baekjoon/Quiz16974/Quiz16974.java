package baekjoon.Quiz16974;

import java.io.*;
import java.util.*;

/*
Gold5: 레벨 햄버거 / [dp]
*/
public class Quiz16974 {
	static int N;
	static long X;
	static long[] total, pat;

	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(System.in);

		N = sc.nextInt();
		X = sc.nextLong();

		sc.close();

		total = new long[N+1];
		pat = new long[N+1];
		total[0] = 1;
		pat[0] = 1;

		for(int i=1; i<=N; i++) {
			total[i] = 1 + total[i-1] + 1 + total[i-1] + 1;
			pat[i] = pat[i-1] + 1 + pat[i-1];
		}

		long ans = recursion(N, X);

		System.out.println(ans);
	}

	static long recursion(int n, long x) {
		if(n == 0) {
			if(x == 0) {
				return 0;
			}
			else if(x == 1) {
				return 1;
			}
		}

		if(x == 1) {
			return 0;
		}
		else if(x <= 1 + total[n-1]) {
			return recursion(n-1, x-1);
		}
		else if(x == 1 + total[n-1] + 1) {
			return pat[n-1] + 1;
		}
		else if(x <= 1 + total[n-1] + 1 + total[n-1]) {
			return pat[n-1] + 1 + recursion(n-1, x - (1 + total[n-1] + 1));
		}
		else {
			return pat[n-1] + 1 + pat[n-1];
		}
	}
}
