package baekjoon.Quiz1285;

import java.io.*;

public class Quiz1285 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		char[][] map = new char[N][N];
		for(int i=0; i<N; i++) {
			String str = br.readLine();
			for(int j=0; j<N; j++) {
				map[i][j] = str.charAt(j);
			}
		}

		int ans = 401;
		for(int bit=0; bit<(1<<N); bit++) {

			int backCountForMap = 0;
			for(int col=0; col<N; col++) {

				int backCountForCol = 0;
				for(int row=0; row<N; row++) {

					char c = map[row][col];
					if((bit & (1<<row)) != 0) c = c == 'T' ? 'H' : 'T';
					if(c == 'T') backCountForCol++;
				}

				backCountForMap += Math.min(backCountForCol, N - backCountForCol);
			}

			ans = Math.min(ans, backCountForMap);
		}

		System.out.println(ans);
	}
}
