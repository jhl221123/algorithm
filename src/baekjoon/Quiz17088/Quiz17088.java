package baekjoon.Quiz17088;

import java.io.*;
import java.util.*;

public class Quiz17088 {
	private static int N;
	private static int[] B;
	private static int ans = Integer.MAX_VALUE;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		B = new int[N];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i=0; i<N; i++) {
			B[i] = Integer.parseInt(st.nextToken());
		}

		if(N <= 2) {
			System.out.println(0);
		} else {
			dfs(2, B[1]-1, (B[1]-1) - (B[0]-1), 2);
			dfs(2, B[1], B[1] - (B[0]-1), 1);
			dfs(2, B[1]+1, (B[1]+1) - (B[0]-1), 2);

			dfs(2, B[1]-1, (B[1]-1) - B[0], 1);
			dfs(2, B[1], B[1] - B[0], 0);
			dfs(2, B[1]+1, (B[1]+1) - B[0], 1);

			dfs(2, B[1]-1, (B[1]-1) - (B[0]+1), 2);
			dfs(2, B[1], B[1] - (B[0]+1), 1);
			dfs(2, B[1]+1, (B[1]+1) - (B[0]+1), 2);
			System.out.println(ans == Integer.MAX_VALUE ? -1 : ans);
		}
	}

	private static void dfs(int next, int prev, int diff, int cnt) {
		if(next == N) {
			ans = Math.min(ans, cnt);
			return;
		}

		if((B[next]-1) - prev == diff) {
			dfs(next+1, B[next]-1, diff, cnt+1);
		}

		if(B[next] - prev == diff) {
			dfs(next+1, B[next], diff, cnt);
		}

		if((B[next]+1) - prev == diff) {
			dfs(next+1, B[next]+1, diff, cnt+1);
		}
	}
}
