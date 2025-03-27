package baekjoon.Quiz20058;

import java.util.*;
import java.io.*;

/*
Gold3: 마법사 상어와 파이어스톰 / [implementation, simulation]
1. 2^L 을 입력받고 배열을 회전시킨다.
1-1. 4중 반복문. r -> 2^N, r+=2^L / c -> 2^N, c+=2^L / pr=0 -> 2^L, ++ / pc=0 -> 2^L, ++
1-2. (r + pr, c + pc) => (r + pc, c + division - pr - 1)
2. 모든 칸을 순회하며 주변에 얼음이 2개 이하인 칸은 얼음양 1 감소한다.
2-1. 방문 배열을 활용해서 제거 대상인 칸을 미리 구한 후, 한 번에 제거한다.
3. 모든 칸을 순회하며 덩어리를 구분하고, 덩어리 당 칸 수와 얼음양 합계를 계산한다.
3-1. 정수형 이차원 배열 두 개로 방문 배열과 정보를 각각 관리한다.
4. 전체 얼음양 합계와 가장 큰 덩어리 칸 수를 출력한다.
*/
public class Quiz20058 {

	private static final int[] dy = {-1, 1, 0, 0};
	private static final int[] dx = {0, 0, -1, 1};

	private static int N, Q, length;
	private static int[][] map;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] nq = br.readLine().split(" ");
		N = Integer.parseInt(nq[0]);
		Q = Integer.parseInt(nq[1]);
		length = (int)Math.pow(2, N);
		map = new int[length][length];
		for(int i=0; i<length; i++) {
			String[] line = br.readLine().split(" ");

			for(int j=0; j<length; j++) {
				map[i][j] = Integer.parseInt(line[j]);
			}
		}

		String[] Ls = br.readLine().split(" ");
		for(int i=0; i<Q; i++) {
			int L = Integer.parseInt(Ls[i]);

			rotate((int)Math.pow(2, L));
			meltIce();
		}

		List<int[]> iceBlocks = traverseIceBlock();
		int totalIceAmount = 0;
		int maxIceBlockSize = 0;
		for(int i=0; i<iceBlocks.size(); i++) {
			int[] iceBlock = iceBlocks.get(i);
			totalIceAmount += iceBlock[0];
			maxIceBlockSize = Math.max(maxIceBlockSize, iceBlock[1]);
		}

		System.out.println(totalIceAmount);
		System.out.println(maxIceBlockSize);
	}

	private static void rotate(int division) {
		int[][] temp = new int[length][length];

		for(int r=0; r<length; r+=division) {
			for(int c=0; c<length; c+=division) {
				for(int pr=0; pr<division; pr++) {
					for(int pc=0; pc<division; pc++) {
						temp[r + pc][c + division - pr - 1] = map[r + pr][c + pc];
					}
				}
			}
		}

		map = temp;
	}

	private static void meltIce() {
		boolean[][] melted = new boolean[length][length];

		for(int row=0; row<length; row++) {
			for(int col=0; col<length; col++) {
				int adjustIceCount = 0;

				for(int d=0; d<4; d++) {
					int mr = row + dy[d];
					int mc = col + dx[d];

					if(isIn(mr, mc) && map[mr][mc] > 0) {
						adjustIceCount++;
					}
				}

				if(adjustIceCount < 3) {
					melted[row][col] = true;
				}
			}
		}

		for(int row=0; row<length; row++) {
			for(int col=0; col<length; col++) {
				if(melted[row][col]) {
					map[row][col]--;
				}
			}
		}
	}

	private static List<int[]> traverseIceBlock() {
		List<int[]> iceBlock = new ArrayList<>();
		int blockNumber = 1;
		int[][] visited = new int[length][length];

		for(int row=0; row<length; row++) {
			for(int col=0; col<length; col++) {
				if(map[row][col] > 0 && visited[row][col] == 0) {
					iceBlock.add(floodFill(blockNumber, row, col, visited));
				}
			}
		}

		return iceBlock;
	}

	private static int[] floodFill(int number, int row, int col, int[][] visited) {
		int blockCount = 0;
		int iceAmount = 0;
		ArrayDeque<int[]> ad = new ArrayDeque<>();
		ad.addLast(new int[] {row, col});

		while(!ad.isEmpty()) {
			int[] point = ad.removeFirst();
			int r = point[0];
			int c = point[1];

			if(visited[r][c] > 0) {
				continue;
			}
			visited[r][c] = number;
			blockCount++;
			iceAmount += map[r][c];

			for(int d=0; d<4; d++) {
				int mr = r + dy[d];
				int mc = c + dx[d];

				if(isIn(mr, mc) && map[mr][mc] > 0 && visited[mr][mc] == 0) {
					ad.addLast(new int[] {mr, mc});
				}
			}
		}

		return new int[] {iceAmount, blockCount};
	}

	private static boolean isIn(int row, int col) {
		return row>=0 && col>=0 && row<length && col<length;
	}
}
