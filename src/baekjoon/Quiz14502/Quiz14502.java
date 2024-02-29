package baekjoon.Quiz14502;

import java.util.*;
import java.io.*;

public class Quiz14502 {
	static int N, M, max;
	static int[][] map;
	static boolean[] tgt;
	static List<Node> blank = new ArrayList<>();
	static List<Node> originBirus = new ArrayList<>();
	static ArrayDeque<Node> ad = new ArrayDeque<>();
	static boolean[][] visit;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if(map[i][j] == 0) blank.add(new Node(i, j));
				if(map[i][j] == 2) originBirus.add(new Node(i, j));
			}
		}
		tgt = new boolean[blank.size()];
		comb(0, 0);
		System.out.println(max);
	}
	static void comb(int srcIdx, int tgtIdx) {
		if(tgtIdx==3) {
			// 세 개의 벽을 추가한 새로운 map 생성
			int[][] updatedMap = updateMap();
			// 이전 자원 초기화
			visit = new boolean[N][M];
			for(Node birus : originBirus) {
				ad.addLast(birus);
				visit[birus.r][birus.c] = true;
			}
			// 새로운 map으로 바이러스 전염 시작
			spreadBirus(updatedMap);
			// 안전지대 개수 갱신
			int currentCnt = countSafeRegion(updatedMap);
			max = Math.max(max, currentCnt);
			return;
		}
		if(srcIdx==blank.size()) return;
		tgt[srcIdx] = true;
		comb(srcIdx+1, tgtIdx+1);
		tgt[srcIdx] = false;
		comb(srcIdx+1, tgtIdx);
	}
	static int[][] updateMap() {
		// 기존 배열에 새로운 벽 추가된 복사본을 반환
		int[][] newMap = new int[N][M];
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				newMap[i][j] = map[i][j];
			}
		}
		// 3개의 벽 추가
		for(int i=0; i<blank.size(); i++) {
			if(tgt[i]) {
				Node wall = blank.get(i);
				newMap[wall.r][wall.c] = 1;
			}
		}
		return newMap;
	}
	static int[] dy = {-1, 1, 0, 0};
	static int[] dx = {0, 0, -1, 1};
	static void spreadBirus(int[][] map) {
		while(!ad.isEmpty()) {
			Node birus = ad.removeFirst();
			int r = birus.r;
			int c = birus.c;
			for(int i=0; i<4; i++) {
				int mr = r + dy[i];
				int mc = c + dx[i];
				if(mr<0 || mc<0 || mr>=N || mc>=M || visit[mr][mc] || map[mr][mc] == 1) continue;
				visit[mr][mc] = true;
				ad.addLast(new Node(mr, mc));
			}
		}
	}
	static int countSafeRegion(int[][] map) {
		int cnt = 0;
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				if(!visit[i][j] && map[i][j] != 1) cnt++;
			}
		}
		return cnt;
	}
	static class Node {
		int r, c;
		public Node(int r, int c) {
			this.r = r;
			this.c = c;
		}
	}
}
