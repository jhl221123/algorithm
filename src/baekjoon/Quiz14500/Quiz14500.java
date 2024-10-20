package baekjoon.Quiz14500;

import java.io.*;
import java.util.*;

public class Quiz14500 {
	static int N, M;
	static int[][] map;
	static boolean[][] visit;
	static boolean[] attachedTiles;
	static int max = 0;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		visit = new boolean[N][M];
		attachedTiles = new boolean[6];
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		for(int r=0; r<N; r++) {
			for(int c=0; c<M; c++) {
				int[] point = {r, c};
				for(int t=1; t<=5; t++) {
					switch (t) {
						case 1 -> attachTileA(point);
						case 2 -> attachTileB(point);
						case 3 -> attachTileC(point);
						case 4 -> attachTileD(point);
						case 5 -> attachTileE(point);
					}
				}
			}
		}

		System.out.println(max);
	}

	private static void attachTileA(int[] point) {
		TileA tileA = new TileA();
		int sum = tileA.a1(point);
		if(sum != -1) max = Math.max(max, sum);
		sum = tileA.a2(point);
		if(sum != -1) max = Math.max(max, sum);
	}

	private static void attachTileB(int[] point) {
		TileB tileB = new TileB();
		int sum = tileB.b1(point);
		if(sum != -1) max = Math.max(max, sum);
	}

	private static void attachTileC(int[] point) {
		TileC tileC = new TileC();
		int sum = tileC.c1(point);
		if(sum != -1) max = Math.max(max, sum);
		sum = tileC.c2(point);
		if(sum != -1) max = Math.max(max, sum);
		sum = tileC.c3(point);
		if(sum != -1) max = Math.max(max, sum);
		sum = tileC.c4(point);
		if(sum != -1) max = Math.max(max, sum);
		sum = tileC.c5(point);
		if(sum != -1) max = Math.max(max, sum);
		sum = tileC.c6(point);
		if(sum != -1) max = Math.max(max, sum);
		sum = tileC.c7(point);
		if(sum != -1) max = Math.max(max, sum);
		sum = tileC.c8(point);
		if(sum != -1) max = Math.max(max, sum);
	}

	private static void attachTileD(int[] point) {
		TileD tileD = new TileD();
		int sum = tileD.d1(point);
		if(sum != -1) max = Math.max(max, sum);
		sum = tileD.d2(point);
		if(sum != -1) max = Math.max(max, sum);
		sum = tileD.d3(point);
		if(sum != -1) max = Math.max(max, sum);
		sum = tileD.d4(point);
		if(sum != -1) max = Math.max(max, sum);
	}

	private static void attachTileE(int[] point) {
		TileE tileE = new TileE();
		int sum = tileE.e1(point);
		if(sum != -1) max = Math.max(max, sum);
		sum = tileE.e2(point);
		if(sum != -1) max = Math.max(max, sum);
		sum = tileE.e3(point);
		if(sum != -1) max = Math.max(max, sum);
		sum = tileE.e4(point);
		if(sum != -1) max = Math.max(max, sum);
	}

	// 일자 타일
	static class TileA {
		public int a1(int[] s) {
			int r = s[0];
			int c = s[1];
			if(!isIn(r, c) || !isIn(r, c+1) || !isIn(r, c+2) || !isIn(r, c+3)) return -1;
			return map[r][c] + map[r][c+1] + map[r][c+2] + map[r][c+3];
		}

		public int a2(int[] s) {
			int r = s[0];
			int c = s[1];
			if(!isIn(r, c) || !isIn(r+1, c) || !isIn(r+2, c) || !isIn(r+3, c)) return -1;
			return map[r][c] + map[r+1][c] + map[r+2][c] + map[r+3][c];
		}
	}

	// 네모 타일
	static class TileB {
		public int b1(int[] s) {
			int r = s[0];
			int c = s[1];
			if(!isIn(r, c) || !isIn(r, c+1) || !isIn(r+1, c) || !isIn(r+1, c+1)) return -1;
			return map[r][c] + map[r][c+1] + map[r+1][c] + map[r+1][c+1];
		}
	}

	// 'ㄱ'자 타일
	static class TileC {
		public int c1(int[] s) {
			int r = s[0];
			int c = s[1];
			if(!isIn(r, c) || !isIn(r+1, c) || !isIn(r+2, c) || !isIn(r+2, c+1)) return -1;
			return map[r][c] + map[r + 1][c] + map[r + 2][c] + map[r + 2][c + 1];
		}

		public int c2(int[] s) {
			int r = s[0];
			int c = s[1];
			if(!isIn(r, c) || !isIn(r+1, c) || !isIn(r+2, c) || !isIn(r+2, c-1)) return -1;
			return map[r][c] + map[r + 1][c] + map[r + 2][c] + map[r + 2][c - 1];
		}

		public int c3(int[] s) {
			int r = s[0];
			int c = s[1];
			if(!isIn(r, c) || !isIn(r+1, c) || !isIn(r+2, c) || !isIn(r, c+1)) return -1;
			return map[r][c] + map[r + 1][c] + map[r + 2][c] + map[r][c + 1];
		}

		public int c4(int[] s) {
			int r = s[0];
			int c = s[1];
			if(!isIn(r, c) || !isIn(r+1, c) || !isIn(r+2, c) || !isIn(r, c-1)) return -1;
			return map[r][c] + map[r + 1][c] + map[r + 2][c] + map[r][c - 1];
		}

		public int c5(int[] s) {
			int r = s[0];
			int c = s[1];
			if(!isIn(r, c) || !isIn(r, c+1) || !isIn(r, c+2) || !isIn(r-1, c)) return -1;
			return map[r][c] + map[r][c + 1] + map[r][c + 2] + map[r - 1][c];
		}

		public int c6(int[] s) {
			int r = s[0];
			int c = s[1];
			if(!isIn(r, c) || !isIn(r, c+1) || !isIn(r, c+2) || !isIn(r-1, c+2)) return -1;
			return map[r][c] + map[r][c + 1] + map[r][c + 2] + map[r - 1][c + 2];
		}

		public int c7(int[] s) {
			int r = s[0];
			int c = s[1];
			if(!isIn(r, c) || !isIn(r, c+1) || !isIn(r, c+2) || !isIn(r+1, c)) return -1;
			return map[r][c] + map[r][c + 1] + map[r][c + 2] + map[r + 1][c];
		}

		public int c8(int[] s) {
			int r = s[0];
			int c = s[1];
			if(!isIn(r, c) || !isIn(r, c+1) || !isIn(r, c+2) || !isIn(r+1, c+2)) return -1;
			return map[r][c] + map[r][c + 1] + map[r][c + 2] + map[r + 1][c + 2];
		}
	}

	// '산'자 타일
	static class TileD {
		public int d1(int[] s) {
			int r = s[0];
			int c = s[1];
			if(!isIn(r, c) || !isIn(r, c+1) || !isIn(r, c+2) || !isIn(r+1, c+1)) return -1;
			return map[r][c] + map[r][c + 1] + map[r][c + 2] + map[r + 1][c + 1];
		}

		public int d2(int[] s) {
			int r = s[0];
			int c = s[1];
			if(!isIn(r, c) || !isIn(r, c+1) || !isIn(r, c+2) || !isIn(r-1, c+1)) return -1;
			return map[r][c] + map[r][c + 1] + map[r][c + 2] + map[r - 1][c + 1];
		}

		public int d3(int[] s) {
			int r = s[0];
			int c = s[1];
			if(!isIn(r, c) || !isIn(r+1, c) || !isIn(r+2, c) || !isIn(r+1, c+1)) return -1;
			return map[r][c] + map[r + 1][c] + map[r + 2][c] + map[r + 1][c + 1];
		}

		public int d4(int[] s) {
			int r = s[0];
			int c = s[1];
			if(!isIn(r, c) || !isIn(r+1, c) || !isIn(r+2, c) || !isIn(r+1, c-1)) return -1;
			return map[r][c] + map[r + 1][c] + map[r + 2][c] + map[r + 1][c - 1];
		}
	}

	// 지그재그 타일
	static class TileE {
		public int e1(int[] s) {
			int r = s[0];
			int c = s[1];
			if(!isIn(r, c) || !isIn(r+1, c) || !isIn(r+1, c+1) || !isIn(r+2, c+1)) return -1;
			return map[r][c] + map[r + 1][c] + map[r + 1][c + 1] + map[r + 2][c + 1];
		}

		public int e2(int[] s) {
			int r = s[0];
			int c = s[1];
			if(!isIn(r, c) || !isIn(r+1, c) || !isIn(r+1, c-1) || !isIn(r+2, c-1)) return -1;
			return map[r][c] + map[r + 1][c] + map[r + 1][c - 1] + map[r + 2][c - 1];
		}

		public int e3(int[] s) {
			int r = s[0];
			int c = s[1];
			if(!isIn(r, c) || !isIn(r+1, c) || !isIn(r+1, c-1) || !isIn(r, c+1)) return -1;
			return map[r][c] + map[r + 1][c] + map[r + 1][c - 1] + map[r][c + 1];
		}

		public int e4(int[] s) {
			int r = s[0];
			int c = s[1];
			if(!isIn(r, c) || !isIn(r+1, c) || !isIn(r+1, c+1) || !isIn(r, c-1)) return -1;
			return map[r][c] + map[r + 1][c] + map[r + 1][c + 1] + map[r][c - 1];
		}
	}

	private static boolean isIn(int r, int c) {
		return r >= 0 && c >= 0 && r < N && c < M;
	}
}
