package baekjoon.Quiz5427;

import java.util.*;
import java.io.*;

public class Quiz5427 {
	// 벽, 불은 이미 방문한 것으로 처리 -> 상근이와 불은 다른 큐로 관리
	// 불은 지속적으로 방문 처리 -> 불은 상근이와 곂칠 수 있지만 상근이는 곂칠 수 없다.
	// 상근이 큐가 비었다면 마지막 상근이 위치로 결과 도출
	static int W;
	static int H;
	public static void main(String[] args) throws IOException {
		int[] dy = {-1, 1, 0, 0};
		int[] dx = {0, 0, -1, 1};
		StringBuilder sb = new StringBuilder();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());

		while(T --> 0) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			W = Integer.parseInt(st.nextToken());
			H = Integer.parseInt(st.nextToken());
			char[][] map = new char[H][W];
			ArrayDeque<int[]> person = new ArrayDeque<>();
			ArrayDeque<int[]> fire = new ArrayDeque<>();
			int[][] personVisit = new int[H][W];
			int[][] fireVisit = new int[H][W];
			int[][] wall = new int[H][W];
			for(int i=0; i<H; i++) {
				String str = br.readLine();
				for(int j=0; j<W; j++) {
					map[i][j] = str.charAt(j);
					if(map[i][j] == '@') {
						person.addLast(new int[] {i, j});
						personVisit[i][j] = 1;
					}
					if(map[i][j] == '*') {
						fire.addLast(new int[] {i, j});
						fireVisit[i][j] = 1;
					}
					if(map[i][j] == '#') wall[i][j] = 1;
				}
			}

			// 불 먼저 이동
			while (!fire.isEmpty()) {
				int[] firePoint = fire.removeFirst();
				int fireR = firePoint[0];
				int fireC = firePoint[1];
				for(int i=0; i<4; i++) {
					int mFireR = fireR + dy[i];
					int mFireC = fireC + dx[i];
					if(isNotRange(mFireR, mFireC)) continue;
					if(wall[mFireR][mFireC] == 0 && fireVisit[mFireR][mFireC]==0) {
						fireVisit[mFireR][mFireC] = fireVisit[fireR][fireC]+1;
						fire.addLast(new int[] {mFireR, mFireC});
					}
				}
			}
			//            for(int i=0; i<H; i++) {
			//                for(int j=0; j<W; j++) {
			//                    System.out.print(fireVisit[i][j]+ " ");
			//                }
			//                System.out.println();
			//            }
			//            System.out.println();
			boolean isEscape = false;
			bk: while(!person.isEmpty()) {
				int[] personPoint = person.removeFirst();
				int personR = personPoint[0];
				int personC = personPoint[1];
				for(int i=0; i<4; i++) {
					int mPersonR = personR + dy[i];
					int mPersonC = personC + dx[i];
					if(isNotRange(mPersonR, mPersonC)) {
						sb.append(personVisit[personR][personC]).append("\n");
						isEscape = true;
						break bk;
					}
					if(personVisit[mPersonR][mPersonC] == 0 && (fireVisit[mPersonR][mPersonC] > personVisit[personR][personC]+1 || fireVisit[mPersonR][mPersonC] ==0) && wall[mPersonR][mPersonC] == 0) {
						personVisit[mPersonR][mPersonC] = personVisit[personR][personC] + 1;
						person.addLast(new int[] {mPersonR, mPersonC});
					}
				}
			}
			//            for(int i=0; i<H; i++) {
			//                for(int j=0; j<W; j++) {
			//                    System.out.print(personVisit[i][j]+ " ");
			//                }
			//                System.out.println();
			//            }
			if(!isEscape) sb.append("IMPOSSIBLE").append("\n");
		}
		System.out.println(sb);
	}
	private static boolean isNotRange(int r, int c) {
		return r<0 || r>=H || c<0 || c>=W;
	}
}
