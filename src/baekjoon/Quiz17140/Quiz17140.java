package baekjoon.Quiz17140;

import java.util.*;
import java.io.*;

/*
Gold4: 이차원 배열과 연산 / [implementation, sort, simulation]
1. 현재 행의 개수가 열의 개수보다 크거나 같다면 R 연산을 수행한다.
1-1. 각 행의 숫자와 개수로 정렬하고, 다음 행 길이중 가장 긴 길이를 현재 행으로 갱신한다.
2. 현재 열의 개수가 행의 개수보다 크다면 C 연산을 수행한다.
2-1. 각 열의 숫자와 개수로 정렬하고, 다음 열 길이중 가장 긴 길이를 현재 열로 갱신한다.
 */
public class Quiz17140 {

	private static int r, c, k;
	private static int curRow = 3;
	private static int curCol = 3;
	private static int[][] map = new int[101][101];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		r = Integer.parseInt(st.nextToken());
		c = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());

		for(int row=1; row<=3; row++) {
			st = new StringTokenizer(br.readLine());

			for(int col=1; col<=3; col++) {
				map[row][col] = Integer.parseInt(st.nextToken());
			}
		}

		int time = 0;
		while(time <= 100) {
			if(map[r][c] == k) {
				System.out.println(time);
				return;
			}

			time++;

			if(curRow >= curCol) {
				calculateR();
			} else {
				calculateC();
			}
		}

		System.out.println(-1);
	}

	private static void calculateR() {
		int maxCol = 0;

		for(int row=1; row<=curRow; row++) {
			int[] count = new int[101];
			List<Node> nodes = new ArrayList<>();

			for(int col=1; col<=curCol; col++) {
				if(map[row][col] == 0) continue;
				count[map[row][col]]++;
			}

			for(int i=1; i<=100; i++) {
				if(count[i] == 0) continue;
				nodes.add(new Node(i, count[i]));
			}

			int nextCol = nodes.size() * 2;
			if(nextCol > 100) nextCol = 100;
			maxCol = Math.max(maxCol, nextCol);

			nodes.sort((o1, o2) -> {
				if(o1.count == o2.count) {
					return o1.number - o2.number;
				};
				return o1.count - o2.count;
			});

			for(int col=1; col<=nextCol; col += 2) {
				Node node = nodes.get(col / 2);
				map[row][col] = node.number;
				map[row][col + 1] = node.count;
			}

			for(int col=nextCol + 1; col<=curCol; col++) {
				map[row][col] = 0;
			}
		}

		curCol = maxCol;
	}

	private static void calculateC() {
		int maxRow = 0;

		for(int col=1; col<=curCol; col++) {
			int[] count = new int[101];
			List<Node> nodes = new ArrayList<>();

			for(int row=1; row<=curRow; row++) {
				if(map[row][col] == 0) continue;
				count[map[row][col]]++;
			}

			for(int i=1; i<=100; i++) {
				if(count[i] == 0) continue;
				nodes.add(new Node(i, count[i]));
			}

			int nextRow = nodes.size() * 2;
			if(nextRow > 100) nextRow = 100;
			maxRow = Math.max(maxRow, nextRow);

			nodes.sort((o1, o2) -> {
				if(o1.count == o2.count) {
					return o1.number - o2.number;
				};
				return o1.count - o2.count;
			});

			for(int row=1; row<=nextRow; row += 2) {
				Node node = nodes.get(row / 2);
				map[row][col] = node.number;
				map[row + 1][col] = node.count;
			}

			for(int row=nextRow + 1; row<=curRow; row++) {
				map[row][col] = 0;
			}
		}

		curRow = maxRow;
	}

	static class Node {
		int number;
		int count;

		public Node(int number, int count) {
			this.number = number;
			this.count = count;
		}
	}
}
