package baekjoon.Quiz1915;

import java.util.Scanner;

// 구간합과 parametric search 활용
// 전체 시간 복잡도: O(N*M*log(min(N, M))
public class Quiz1915 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int M = sc.nextInt();
		int[][] acc = new int[N+1][M+1];
		for(int i=1; i<=N; i++) {
			String line = sc.next();
			for(int j=1; j<=M; j++) {
				acc[i][j] = acc[i-1][j] + acc[i][j-1] - acc[i-1][j-1];
				if(line.charAt(j-1) == '1') acc[i][j]++;
			}
		}
		int l=1;
		int r = Math.min(N, M);
		int ans = 0;
		while(l<=r) {
			int m = (l+r)/2;
			if(isExist(acc, m)) {
				ans = m * m;
				l = m+1;
			} else r = m-1;
		}
		System.out.println(ans);
	}
	private static boolean isExist(int[][] acc, int m) {
		int N = acc.length - 1;
		int M = acc[0].length - 1;
		for(int i=1; i<=N-m+1; i++) {
			for(int j=1; j<=M-m+1; j++) {
				int count = acc[i+m-1][j+m-1] - acc[i+m-1][j-1] - acc[i-1][j+m-1] + acc[i-1][j-1];
				if(count == m * m) return true;
			}
		}
		return false;
	}
}
