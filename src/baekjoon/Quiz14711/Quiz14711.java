package baekjoon.Quiz14711;

import java.io.*;

/*
Gold4: 타일 뒤집기 (Easy) / [dp]
*/
public class Quiz14711 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		StringBuilder[] map = new StringBuilder[N + 1];
		for(int i=0; i<N+1; i++) {
			map[i] = new StringBuilder();
		}
		map[0].append(br.readLine());
		boolean[][] toggle = new boolean[N][N];

		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				if(map[i].charAt(j) == '#') {
					if(i < N - 1) {
						toggle[i + 1][j] = !toggle[i + 1][j];
					}
					if(j > 0) {
						toggle[i][j - 1] = !toggle[i][j - 1];
					}
					if(j < N - 1) {
						toggle[i][j + 1] = !toggle[i][j + 1];
					}
				}
			}

			for(int j=0; j<N; j++) {
				if(toggle[i][j]) {
					map[i + 1].append("#");
				} else {
					map[i + 1].append(".");
				}
			}
		}

		for(int i=0; i<N; i++) {
			System.out.println(map[i]);
		}
	}
}
