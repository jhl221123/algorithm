package baekjoon.Quiz11559;

import java.util.*;
import java.io.*;

/*
Gold4: Puyo Puyo / [implementation, simulation]
1. 4개 이상의 뿌요를 없애는 기능
1-1. 방문 배열과 제거 배열을 활용해 제거해야할 뿌요를 마킹한다.
1-2. 맵을 순차적으로 탐색하며, 방문하지 않은 뿌요를 발견하면 주변 뿌요를 탐색한다.
1-3. 주변 뿌요 개수가 4개 이상일 경우, 모두 제거 배열에 마킹한다.
2. 뿌요를 빈 공간으로 이동시키는 기능
2-1. col -> row 순으로 순회하며 뿌요를 아래로 이동시킨다.
2-2. 뿌요를 처음 만나는 지점은 ts, 아래가 빈 공간 혹은 경계를 벗어나게 되는 뿌요 위치를 te
2-2-1. te 아래가 경계 밖이라면 다음 col 탐색
2-3. te 아래가 빈 공간이라면 tt, 다음 뿌요 혹은 경계까지 tt를 갱신
2-4. for 0 -> ts - te : map[col][tt] = map[col][te] / tt++, te++
2-5. row = tt, for tt -> 0 : map[col][tt] = 0
*/
public class Quiz11559 {

	private static char[][] map = new char[12][6];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		for(int row=0; row<12; row++) {
			String line = br.readLine();

			for(int col=0; col<6; col++) {
				map[row][col] = line.charAt(col);
			}
		}

		int combo = 0;
		while(true) {
			boolean isRemoved = removePuyo();
			if(!isRemoved) {
				break;
			}

			combo++;
			adjustMap();
		}

		System.out.println(combo);
	}

	private static boolean removePuyo() {
		boolean[][] visited = new boolean[12][6];
		boolean[][] canRemove = new boolean[12][6];

		for(int row=0; row<map.length; row++) {
			for(int col=0; col<map[0].length; col++) {
				if(map[row][col] != '.' && !visited[row][col]) {
					marking(row, col, visited, canRemove);
				}
			}
		}

		boolean isRemoved = false;
		for(int row=0; row<map.length; row++) {
			for(int col=0; col<map[0].length; col++) {
				if(canRemove[row][col]) {
					isRemoved = true;
					map[row][col] = '.';
				}
			}
		}

		return isRemoved;
	}

	private static void marking(int sr, int sc, boolean[][] visited, boolean[][] canRemove) {
		int[] dy = {-1, 1, 0, 0};
		int[] dx = {0, 0, -1, 1};

		char color = map[sr][sc];
		List<int[]> puyos = new ArrayList<>();

		ArrayDeque<int[]> ad = new ArrayDeque<>();
		ad.addLast(new int[] {sr, sc});
		while(!ad.isEmpty()) {
			int[] v = ad.removeFirst();
			int r = v[0];
			int c = v[1];

			if(visited[r][c]) {
				continue;
			}
			visited[r][c] = true;
			puyos.add(new int[] {r, c});

			for(int d=0; d<4; d++) {
				int mr = r + dy[d];
				int mc = c + dx[d];

				if(mr>=0 && mr<12 && mc>=0 && mc<6 && map[mr][mc] == color && !visited[mr][mc]) {
					ad.addLast(new int[] {mr, mc});
				}
			}
		}

		if(puyos.size() >= 4) {
			for(int i=0; i<puyos.size(); i++) {
				int[] puyo = puyos.get(i);
				canRemove[puyo[0]][puyo[1]] = true;
			}
		}
	}

	private static void adjustMap() {
		for(int col=0; col<map[0].length; col++) {
			bk: for(int row=0; row<map.length; row++) {
				if(map[row][col] != '.') {
					int ts = row;

					int te;
					for(te = row; te<map.length; te++) {
						if(te + 1 == 12) {
							break bk;
						}

						if(map[te + 1][col] == '.') {
							break;
						}
					}

					int tt = te + 1;
					while(true) {
						if(tt + 1 == 12) {
							break;
						}

						if(map[tt + 1][col] != '.') {
							break;
						}

						tt++;
					}

					int l = te - ts;
					for(int i=0; i<=l; i++) {
						map[tt][col] = map[te][col];
						tt--;
						te--;
					}

					row = tt;
					for(int i=tt; i>=0; i--) {
						map[i][col] = '.';
					}
				}
			}
		}
	}
}
