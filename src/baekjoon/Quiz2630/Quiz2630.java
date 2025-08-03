package baekjoon.Quiz2630;

import java.io.*;

/*
Silver2: 색종이 만들기 / [dfs, divide and conquer]
*/
public class Quiz2630 {

	private static int[][] map;
	private static int[] count;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		map = new int[N][N];
		for(int i=0; i<N; i++) {
			String[] row = br.readLine().split(" ");

			for(int j=0; j<N; j++) {
				map[i][j] = Integer.parseInt(row[j]);
			}
		}

		count = new int[2];
		dfs(0, N, 0, N);
		System.out.println(count[0]);
		System.out.println(count[1]);
	}

	private static void dfs(int rs, int re, int cs, int ce) {
		if(isAllMatch(rs, re, cs, ce)) {
			count[map[rs][cs]]++;
		} else {
			dfs(rs, (rs + re) / 2, cs, (cs + ce) / 2);
			dfs((rs + re) / 2, re, cs, (cs + ce) / 2);
			dfs(rs, (rs + re) / 2, (cs + ce) / 2, ce);
			dfs((rs + re) / 2, re, (cs + ce) / 2, ce);
		}
	}

	private static boolean isAllMatch(int rs, int re, int cs, int ce) {
		for(int i=rs; i<re; i++) {
			for(int j=cs; j<ce; j++) {
				if(map[i][j] != map[rs][cs]) return false;
			}
		}
		return true;
	}
}
