package baekjoon.Quiz10026;

import java.util.*;
import java.io.*;

public class Quiz10026 {
	static int N;
	static char[][] arr;
	static boolean[][] visitA;
	static boolean[][] visitB;
	static int[] dy = {-1, 1, 0, 0};
	static int[] dx = {0, 0, -1, 1};
	static int[] ans = new int[2];
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		arr = new char[N][N];
		visitA = new boolean[N][N];
		visitB = new boolean[N][N];
		for(int i=0; i<N; i++) {
			String str = br.readLine();
			for(int j=0; j<N; j++) {
				arr[i][j] = str.charAt(j);
			}
		}
		int[] startA = new int[] {0, 0};
		while(!isFull(visitA)) {
			bfsA(startA);
			ans[0]++;
			startA = getNext(visitA);
			if(startA == null) break;
		}

		int[] startB = new int[] {0, 0};
		while(!isFull(visitB)) {
			bfsB(startB);
			ans[1]++;
			startB = getNext(visitB);
			if(startB == null) break;
		}
		System.out.println(ans[0] + " " + ans[1]);
	}
	private static void bfsA(int[] start) {
		ArrayDeque<int[]> ad = new ArrayDeque<>();
		ad.addLast(start);
		visitA[start[0]][start[1]] = true;
		int base = arr[start[0]][start[1]];
		while(!ad.isEmpty()) {
			int[] point = ad.removeFirst();
			int r = point[0];
			int c = point[1];
			for(int i=0; i<4; i++) {
				int mr = r + dy[i];
				int mc = c + dx[i];
				if(mr < 0 || mr >= N || mc < 0 || mc >= N) continue;
				if(arr[mr][mc] == base && !visitA[mr][mc]) {
					visitA[mr][mc] = true;
					ad.addLast(new int[] {mr, mc});
				}
			}
		}
	}
	private static void bfsB(int[] start) {
		ArrayDeque<int[]> ad = new ArrayDeque<>();
		ad.addLast(start);
		visitB[start[0]][start[1]] = true;
		int base = arr[start[0]][start[1]];
		while(!ad.isEmpty()) {
			int[] point = ad.removeFirst();
			int r = point[0];
			int c = point[1];
			for(int i=0; i<4; i++) {
				int mr = r + dy[i];
				int mc = c + dx[i];
				if(mr < 0 || mr >= N || mc < 0 || mc >= N) continue;
				if(!visitB[mr][mc]) {
					if (arr[mr][mc] == base ||
						(arr[mr][mc] == 'R' && base == 'G') ||
						(arr[mr][mc] == 'G' && base == 'R')){
						visitB[mr][mc] = true;
						ad.addLast(new int[] {mr, mc});
					}
				}
			}
		}
	}
	private static boolean isFull(boolean[][] visit) {
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				if(!visit[i][j]) return false;
			}
		}
		return true;
	}
	private static int[] getNext(boolean[][] visit) {
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				if(!visit[i][j]) return new int[] {i, j};
			}
		}
		return null;
	}
}
