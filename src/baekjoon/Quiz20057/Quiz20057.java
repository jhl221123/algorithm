package baekjoon.Quiz20057;

import java.io.*;

/*
Gold3: 마법사 상어와 토네이도 / [implementation, simulation]
1. 초기 위치: ( N/2 , N/2 ), 초기 방향: 0(왼쪽) -> 좌.하.우.상:0.1.2.3
2. 현재 위치와 방향에 맞게 다음 칸의 모래를 날린다.
2-1. 방향에 따른 모래 이동칸과 비율을 배열로 관리한다.
2-2. 다음 위치와 모래량을 전달하고, 모래가 날아간 칸과 양을 담고있는 배열을 받는다.
2-3. 해당 배열을 순회하며 해당 좌표에 모래양을 합하고, 격자를 벗어나는 좌표의 모래는 총합계에 합산한다.
3. 현재 방향을 참고하여 다음 칸으로 이동한다.
3-1. 이동한 칸이 방향을 변경해야하는 칸이라면 방향을 변경한다.
3-2. 이동한 칸이 ( 0, 0 )이라면 탐색을 종료한다.
*/
public class Quiz20057 {

	private static final int[] dy = {0, 1, 0, -1};
	private static final int[] dx = {-1, 0, 1, 0};
	private static int[][][] sandRule = {
		{{-2, 0, 2}, {2, 0, 2}, {-1, 0, 7}, {1, 0, 7}, {-1, 1, 1}, {1, 1, 1}, {-1, -1, 10}, {1, -1, 10}, {0, -2, 5}},
		{{0, -2, 2}, {0, 2, 2}, {0, -1, 7}, {0, 1, 7}, {-1, -1, 1}, {-1, 1, 1}, {1, -1, 10}, {1, 1, 10}, {2, 0, 5}},
		{{-2, 0, 2}, {2, 0, 2}, {-1, 0, 7}, {1, 0, 7}, {-1, -1, 1}, {1, -1, 1}, {-1, 1, 10}, {1, 1, 10}, {0, 2, 5}},
		{{0, -2, 2}, {0, 2, 2}, {0, -1, 7}, {0, 1, 7}, {1, -1, 1}, {1, 1, 1}, {-1, -1, 10}, {-1, 1, 10}, {-2, 0, 5}}
	};

	private static int N;
	private static int[][] map;
	private static int[][] curvePoint;
	private static int total;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		map = new int[N][N];
		for(int row=0; row<N; row++) {
			String[] line = br.readLine().split(" ");

			for(int col=0; col<N; col++) {
				map[row][col] = Integer.parseInt(line[col]);
			}
		}

		int[] currentPoint = {N / 2, N / 2, 0};
		initializeCurvePoint(currentPoint[0], currentPoint[1]);

		int curvePointIdx = 0;
		while(true) {
			int[] nextPoint = getNextPoint(currentPoint);
			blowSand(nextPoint);

			currentPoint[0] = nextPoint[0];
			currentPoint[1] = nextPoint[1];
			currentPoint[2] = nextPoint[2];
			if(currentPoint[0] == 0 && currentPoint[1] == 0) break;
			if(curvePointIdx < curvePoint.length && currentPoint[0] == curvePoint[curvePointIdx][0] && currentPoint[1] == curvePoint[curvePointIdx][1]) {
				currentPoint[2]++;
				currentPoint[2] %= 4;
				curvePointIdx++;
			}
		}

		System.out.println(total);
	}

	private static void blowSand(int[] point) {
		int row = point[0];
		int col = point[1];
		int dir = point[2];

		int sandWeight = map[row][col];
		int usedWeight = 0;
		for(int i=0; i<9; i++) {
			int mr = row + sandRule[dir][i][0];
			int mc = col + sandRule[dir][i][1];
			int rate = sandRule[dir][i][2];

			int subWeight = sandWeight * rate / 100;
			usedWeight += subWeight;

			if(mr>=0 && mc>=0 && mr<N && mc<N) {
				map[mr][mc] += subWeight;
			} else {
				total += subWeight;
			}
		}

		int ar = row + dy[dir];
		int ac = col + dx[dir];
		int alpha = sandWeight - usedWeight;
		if(ar>=0 && ac>=0 && ar<N && ac<N) {
			map[ar][ac] += alpha;
		} else {
			total += alpha;
		}

		map[row][col] = 0;
	}

	private static int[] getNextPoint(int[] currentPoint) {
		int row = currentPoint[0];
		int col = currentPoint[1];
		int dir = currentPoint[2];

		return new int[] {row + dy[dir], col + dx[dir], dir};
	}

	private static void initializeCurvePoint(int r, int c) {
		curvePoint = new int[(N / 2) * 4][2];
		int[][] base = { {r, c-1}, {r+1, c-1}, {r+1, c+1}, {r-1, c+1} };

		int idx = 0;
		for(int i=0; i<curvePoint.length; i+=4) {
			for(int j=0; j<4; j++) {
				curvePoint[idx][0] = base[j][0];
				curvePoint[idx][1] = base[j][1];
				idx++;
			}

			base[0][0] += -1;
			base[0][1] += -1;

			base[1][0] += 1;
			base[1][1] += -1;

			base[2][0] += 1;
			base[2][1] += 1;

			base[3][0] += -1;
			base[3][1] += 1;
		}
	}
}
