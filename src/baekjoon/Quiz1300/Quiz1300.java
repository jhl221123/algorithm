package baekjoon.Quiz1300;

import java.util.Scanner;

// 전체 시간 복잡도: O(NlogN)
public class Quiz1300 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int K = sc.nextInt();
		long l = 1;
		long r = (long)N *N;
		long ans = -1;
		while(l<=r) {
			long m = (l+r)/2;
			long count = 0;
			// 각 수는 행 * 열, 따라서 각 숫자를 행으로 나누면 열 번호가 나온다.
			// 모든 행을 돌면서 m/행을 더해주게 되면 열의 크기를 구함으로써 m보다 작은 수를 구할 수 있게 된다.
			for(int i=1; i<=N; i++) {
				count += Math.min(m/i, N);
			}
			if(count >= K) {
				ans = m;
				r = m-1;
			} else l = m+1;
		}
		System.out.println(ans);
	}
}
