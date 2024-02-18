package baekjoon.Quiz1074;

import java.util.*;

public class Quiz1074 {
	static int N;
	static int ans;
	static int r;
	static int c;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		N = (int) Math.pow(2, N);
		r = sc.nextInt();
		c = sc.nextInt();

		dfs(0, 0);
		System.out.println(ans);
	}
	private static void dfs(int nr, int nc) {
		if(N==1) return;
		N/=2;
		if(r-nr<N && c-nc<N) dfs(nr, nc);
		else if(r-nr<N && c-nc>=N) {
			ans += N * N * 1;
			dfs(nr, nc+N);
		} else if(r-nr>=N && c-nc<N) {
			ans += N * N * 2;
			dfs(nr+N, nc);
		} else {
			ans += N * N * 3;
			dfs(nr+N, nc+N);
		}
	}
}
