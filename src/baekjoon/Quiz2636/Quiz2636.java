package baekjoon.Quiz2636;

import java.util.*;
import java.io.*;

public class Quiz2636 {
	// 공기를 먼저 구한다.
	// 공기와 연결되지 않은 0은 구멍으로 다른 배열에 관리한다.
	// 매번 공기가된 구멍이 있는지 확인한다.
	// 구멍이 공기와 닿으면 해당 구멍 좌표들을 공기배열에 갱신해준다.
	static int N;
	static int M;
	static int[][] arr;
	static int[][] temp;
	static boolean[][] air;
	static boolean[][] hall;
	static int[] dy = {-1, 1, 0, 0};
	static int[] dx = {0, 0, -1, 1};
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		arr = new int[N][M];
		temp = new int[N][M];
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<M; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		air = new boolean[N][M];
		hall = new boolean[N][M];
		setAir();
		setHall();
		int dayCount = 0;
		int pieceCount = 0;
		while(isExist()) {
			pieceCount = getCheeseCount();
			removeCheese();
			changeHallToAir();
			dayCount++;
		}
		System.out.println(dayCount);
		System.out.println(pieceCount);
	}
	private static void setAir() {
		ArrayDeque<int[]> ad = new ArrayDeque<>();
		ad.addLast(new int[] {0, 0});
		air[0][0] = true;
		while(!ad.isEmpty()) {
			int[] point = ad.removeFirst();
			int r = point[0];
			int c = point[1];
			for(int i=0; i<4; i++) {
				int mr = r + dy[i];
				int mc = c + dx[i];
				if(mr<0 || mr>=N || mc<0 || mc>=M) continue;
				if(!air[mr][mc] && arr[mr][mc] == 0) {
					air[mr][mc] = true;
					ad.addLast(new int[] {mr, mc});
				}
			}
		}
	}
	private static void setHall() {
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				if(arr[i][j] == 0 && !air[i][j]) hall[i][j] = true;
			}
		}
	}
	private static boolean isExist() {
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				if(!air[i][j]) return true;
			}
		}
		return false;
	}
	private static void removeCheese() {
		// 공기랑 닿아있는 치즈 제거
		ArrayDeque<int[]> removeC = new ArrayDeque<>();
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				if(arr[i][j] == 1) {
					for(int k=0; k<4; k++) {
						int mr = i + dy[k];
						int mc = j + dx[k];
						if(mr<0 || mr>=N || mc<0 || mc>=M) continue;
						if(air[mr][mc]) {
							removeC.addLast(new int[] {i, j});
							break;
						}
					}
				}
			}
		}
		while(!removeC.isEmpty()) {
			int[] removeP = removeC.removeFirst();
			air[removeP[0]][removeP[1]] = true;
			arr[removeP[0]][removeP[1]] = 0;
		}
	}
	private static void changeHallToAir() {
		// 공기와 닿은 구멍들 큐에 저장 -> 공기로 갱신
		ArrayDeque<int[]> hallToAir = new ArrayDeque<>();
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				if(hall[i][j]) {
					for(int k=0; k<4; k++) {
						int mr = i + dy[k];
						int mc = j + dx[k];
						if(mr<0 || mr>=N || mc<0 || mc>=M) continue;
						if(air[mr][mc]) {
							hall[i][j] = false;
							air[i][j] = true;
							hallToAir.addLast(new int[] {i, j});
							break;
						}
					}
				}
			}
		}
		// 큐에 있는 구멍과 연결된 모든 구멍 공기로 갱신
		while(!hallToAir.isEmpty()) {
			int[] hallPoint = hallToAir.removeFirst();
			int hr = hallPoint[0];
			int hc = hallPoint[1];
			for(int i=0; i<4; i++) {
				int mhr = hr + dy[i];
				int mhc = hc + dx[i];
				if(mhr<0 || mhr>=N || mhc<0 || mhc>=M) continue;
				if(hall[mhr][mhc]) {
					hall[mhr][mhc] = false;
					air[mhr][mhc] = true;
					hallToAir.addLast(new int[] {mhr, mhc});
				}
			}
		}
	}
	private static int getCheeseCount() {
		int count = 0;
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				if(arr[i][j] == 1) count++;
			}
		}
		return count;
	}
}
