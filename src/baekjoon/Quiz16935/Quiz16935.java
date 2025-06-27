package baekjoon.Quiz16935;

import java.io.*;

/*
Gold5: 배열 돌리기 3 / [implementation]
*/
public class Quiz16935 {

	private static int N, M;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] NMR = br.readLine().split(" ");
		N = Integer.parseInt(NMR[0]);
		M = Integer.parseInt(NMR[1]);
		int R = Integer.parseInt(NMR[2]);
		int[][] arr = new int[N][M];
		for(int i=0; i<N; i++) {
			String[] row = br.readLine().split(" ");
			for(int j=0; j<M; j++) {
				arr[i][j] = Integer.parseInt(row[j]);
			}
		}
		String[] opNo = br.readLine().split(" ");
		for(int i=0; i<R; i++) {
			arr = operate(arr, opNo[i]);
		}

		for (int i = 0; i < arr.length; i++) {
			for (int j = 0; j < arr[0].length; j++) {
				System.out.print(arr[i][j]);
				if (j != arr[0].length - 1) {
					System.out.print(" ");
				}
			}
			System.out.println();
		}
	}

	private static int[][] operate(int[][] arr, String no) {
		if("1".equals(no)) {
			return swapTopBottom(arr);
		}
		if("2".equals(no)) {
			return swapLeftRight(arr);
		}
		if("3".equals(no)) {
			int[][] updated = rotateRight(arr);
			N = updated.length;
			M = updated[0].length;
			return updated;
		}
		if("4".equals(no)) {
			int[][] updated = rotateLeft(arr);
			N = updated.length;
			M = updated[0].length;
			return updated;
		}
		if("5".equals(no)) {
			return rotateGroupRight(arr);
		}
		if("6".equals(no)) {
			return rotateGroupLeft(arr);
		}

		throw new RuntimeException("err!");
	}

	private static int[][] swapTopBottom(int[][] arr) {
		int[][] tmp = new int[N][M];
		for(int r=0; r<N; r++) {
			for(int c=0; c<M; c++) {
				tmp[r][c] = arr[N - 1 - r][c];
			}
		}
		return tmp;
	}

	private static int[][] swapLeftRight(int[][] arr) {
		int[][] tmp = new int[N][M];
		for(int r=0; r<N; r++) {
			for(int c=0; c<M; c++) {
				tmp[r][c] = arr[r][M - 1 - c];
			}
		}
		return tmp;
	}

	private static int[][] rotateRight(int[][] arr) {
		int[][] tmp = new int[M][N];
		for(int row=0; row<N; row++) {
			for(int col=0; col<M; col++) {
				tmp[col][N - 1 - row] = arr[row][col];
			}
		}
		return tmp;
	}

	private static int[][] rotateLeft(int[][] arr) {
		int[][] tmp = new int[M][N];
		for(int row=0; row<N; row++) {
			for(int col=0; col<M; col++) {
				tmp[M - 1 - col][row] = arr[row][col];
			}
		}
		return tmp;
	}

	private static int[][] rotateGroupRight(int[][] arr) {
		int[][] tmp = new int[N][M];
		int HN = N / 2;
		int HM = M / 2;
		for(int row=0; row<N; row++) {
			for(int col=0; col<M; col++) {
				if(row < HN && col < HM) tmp[row][col + HM] = arr[row][col];
				if(row < HN && col >= HM) tmp[row + HN][col] = arr[row][col];
				if(row >= HN && col >= HM) tmp[row][col - HM] = arr[row][col];
				if(row >= HN && col < HM) tmp[row - HN][col] = arr[row][col];
			}
		}
		return tmp;
	}

	private static int[][] rotateGroupLeft(int[][] arr) {
		int[][] tmp = new int[N][M];
		int HN = N / 2;
		int HM = M / 2;
		for(int row=0; row<N; row++) {
			for(int col=0; col<M; col++) {
				if(row < HN && col < HM) tmp[row + HN][col] = arr[row][col];
				if(row < HN && col >= HM) tmp[row][col - HM] = arr[row][col];
				if(row >= HN && col >= HM) tmp[row - HN][col] = arr[row][col];
				if(row >= HN && col < HM) tmp[row][col + HM] = arr[row][col];
			}
		}
		return tmp;
	}
}
