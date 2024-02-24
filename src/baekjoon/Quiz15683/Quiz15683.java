package baekjoon.Quiz15683;

import java.util.*;
import java.io.*;

public class Quiz15683 {
	// CCTV 클래스로 좌표와 타입을 관리
	// dfs로 각 타입별로 감시 -> -1, if 6 break
	// 감시 후 상태 원복 -> +1

	static int N;
	static int M;
	static int[][] site;
	static List<CCTV> cctvs;
	static int min = Integer.MAX_VALUE;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		site = new int[N][M];
		cctvs = new ArrayList<>();
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<M; j++) {
				site[i][j] = Integer.parseInt(st.nextToken());
				if(site[i][j] >=1 && site[i][j] <= 5) cctvs.add(new CCTV(i, j, site[i][j]));
			}
		}
		dfs(0);
		System.out.println(min);
	}
	static void dfs(int cctvIdx) {
		if(cctvIdx==cctvs.size()) {
			setMin();
			return;
		}
		CCTV cctv = cctvs.get(cctvIdx);
		int r = cctv.r;
		int c = cctv.c;
		switch(cctv.type) {
			case 1: {
				for(int i=0; i<4; i++) {
					search(r, c, i);
					dfs(cctvIdx+1);
					unsearch(r, c, i);
				}
			} break;
			case 2: {
				for(int i=0; i<2; i++) {
					search(r, c, i);
					search(r, c, i+2);
					dfs(cctvIdx+1);
					unsearch(r, c, i);
					unsearch(r, c, i+2);
				}
			} break;
			case 3: {
				for(int i=0; i<4; i++) {
					search(r, c, i);
					search(r, c, (i+1)%4);
					dfs(cctvIdx+1);
					unsearch(r, c, i);
					unsearch(r, c, (i+1)%4);
				}
			} break;
			case 4: {
				for(int i=0; i<4; i++) {
					search(r, c, i);
					search(r, c, (i+1)%4);
					search(r, c, (i+2)%4);
					dfs(cctvIdx+1);
					unsearch(r, c, i);
					unsearch(r, c, (i+1)%4);
					unsearch(r, c, (i+2)%4);
				}
			} break;
			case 5: {
				search(r, c, 0);
				search(r, c, 1);
				search(r, c, 2);
				search(r, c, 3);
				dfs(cctvIdx + 1);
			} break;
		}
	}
	static void setMin() {
		int cnt = 0;
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				if(site[i][j] == 0) cnt++;
			}
		}
		min = Math.min(min, cnt);
	}
	static void search(int r, int c, int dir) {
		switch(dir) {
			case 0: {
				while(true) {
					r--;
					if(r<0 || site[r][c] == 6) break;
					if(site[r][c] <= 0) site[r][c]--;
				}
			} break;
			case 1: {
				while(true) {
					c++;
					if(c>=M || site[r][c] == 6) break;
					if(site[r][c] <= 0) site[r][c]--;
				}
			} break;
			case 2: {
				while(true) {
					r++;
					if(r>=N || site[r][c] == 6) break;
					if(site[r][c] <= 0) site[r][c]--;
				}
			} break;
			case 3: {
				while(true) {
					c--;
					if(c<0 || site[r][c] == 6) break;
					if(site[r][c] <= 0) site[r][c]--;
				}
			} break;
		}
	}
	static void unsearch(int r, int c, int dir) {
		switch(dir) {
			case 0: {
				while(true) {
					r--;
					if(r<0 || site[r][c] == 6) break;
					if(site[r][c] <= 0) site[r][c]++;
				}
			} break;
			case 1: {
				while(true) {
					c++;
					if(c>=M || site[r][c] == 6) break;
					if(site[r][c] <= 0) site[r][c]++;
				}
			} break;
			case 2: {
				while(true) {
					r++;
					if(r>=N || site[r][c] == 6) break;
					if(site[r][c] <= 0) site[r][c]++;
				}
			} break;
			case 3: {
				while(true) {
					c--;
					if(c<0 || site[r][c] == 6) break;
					if(site[r][c] <= 0) site[r][c]++;
				}
			} break;
		}
	}
	static class CCTV {
		int r, c, type;
		public CCTV(int r, int c, int type) {
			this.r = r;
			this.c = c;
			this.type = type;
		}
	}
}
