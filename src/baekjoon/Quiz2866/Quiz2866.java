package baekjoon.Quiz2866;

import java.util.*;
import java.io.*;

public class Quiz2866 {
	static int R, C;
	static char[][] map;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		map = new char[R][C];
		for(int i=0; i<R; i++) {
			String str = br.readLine();
			for(int j=0; j<C; j++) {
				map[i][j] = str.charAt(j);
			}
		}

		int l = 0;
		int r = R+1;
		int cnt = 0;
		while(l<=r) {
			int m = (l + r) / 2;
			System.out.println(l + " " + r + " " + m);
			if(possible(m)) {
				l = m + 1;
				cnt = m;
			} else {
				r = m - 1;
			}
		}

		System.out.println(cnt);
	}

	private static boolean possible(int m) {
		Set<String> set = new HashSet<>();
		for(int c=0; c<C; c++) {
			StringBuilder sb = new StringBuilder();
			for(int r=m; r<R; r++) {
				sb.append(map[r][c]);
			}
			set.add(sb.toString());
		}
		return set.size() == C;
	}
}
