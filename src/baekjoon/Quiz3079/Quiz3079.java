package baekjoon.Quiz3079;

import java.util.Arrays;
import java.util.Scanner;

// 전체 시간 복잡도: O(NlogMT)
public class Quiz3079 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int M = sc.nextInt();
		long[] desks = new long[N];
		for(int i=0; i<N; i++) {
			desks[i] = sc.nextInt();
		}
		long l = 1L;
		long r = Arrays.stream(desks).min().getAsLong() * M;
		long ans = 0L;
		while(l<=r) {
			long m = (l+r)/2;
			if(isPossible(m, desks, M)) {
				r = m-1;
				ans = m;
			} else l = m+1;
		}
		System.out.println(ans);
	}
	private static boolean isPossible(long target, long[] desks, int numberOfPeople) {
		long sum = 0;
		for (long desk : desks) {
			sum += target / desk;
		}
		return sum >= numberOfPeople;
	}
}
