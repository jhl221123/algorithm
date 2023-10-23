package baekjoon.Quiz2417;

import java.util.Scanner;

// 전체 시간 복잡도: O(logb)
public class Quiz2417 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		long N = sc.nextLong();
		long a = 0;
		long b = 1L << 32;
		long ans = 0;
		// O(logb)
		while(a<=b) {
			long m = (a+b)/2;
			double square = Math.pow(m, 2);
			if(square>=N) {
				ans = m;
				b = m-1;
			}
			else a = m+1;
		}
		System.out.println(ans);
	}
}
