package baekjoon.Quiz9328;

import java.util.*;
import java.io.*;

public class Quiz9328 {
	static int T, h, w, key, ans;
	static char[][] map;
	static boolean[][] visit;
	static int[] dy = {-1, 1, 0, 0};
	static int[] dx = {0, 0, -1, 1};
	static ArrayDeque<int[]> left = new ArrayDeque<>();
	static ArrayDeque<int[]> right = new ArrayDeque<>();
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		T = Integer.parseInt(br.readLine());
		while(T-- > 0) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			h = Integer.parseInt(st.nextToken());
			w = Integer.parseInt(st.nextToken());
			ans = 0;
			key = 0;
			map = new char[h][w];
			visit = new boolean[h][w];
			for(int i=0; i<h; i++) {
				String str = br.readLine();
				for(int j=0; j<w; j++) {
					map[i][j] = str.charAt(j);
				}
			}
			String initKey = br.readLine();
			for(int i=0; i<initKey.length(); i++) {
				char target = initKey.charAt(i);
				if(target == '0') continue;
				key = key | (1 << (target - 'a'));
			}
			initStartPoint();
			while(!right.isEmpty()) {
				int priorKey = key;
				movePoint();
				bfs();
				if(noChangeKey(priorKey)) break;
			}
			left.clear();
			right.clear();
			sb.append(ans).append("\n");
		}
		System.out.print(sb);
	}
	private static void bfs() {
		while(!left.isEmpty()) {
			int[] target = left.removeFirst();
			int r = target[0];
			int c = target[1];
			if(visit[r][c]) continue;
			// 열쇠가 없는 문
			if(map[r][c] >= 'A' && map[r][c] <= 'Z' && !haveKey(map[r][c])) {
				right.addLast(new int[] {r, c});
				continue;
			}
			// 열쇠
			if(map[r][c] >= 'a' && map[r][c] <= 'z') key = key | (1 << (map[r][c] - 'a'));
			visit[r][c] = true;
			if(map[r][c] == '$') ans++;
			for(int d=0; d<4; d++) {
				int mr = r + dy[d];
				int mc = c + dx[d];
				if(!isIn(mr, mc) || map[mr][mc] == '*' || visit[mr][mc]) continue;
				left.addLast(new int[] {mr, mc});
			}
		}
	}
	private static boolean noChangeKey(int priorKey) {
		return priorKey == key;
	}
	private static void movePoint() {
		while (!right.isEmpty()) {
			left.addLast(right.removeFirst());
		}
	}
	private static void initStartPoint() {
		for(int i=0; i<h; i++) {
			for(int j=0; j<w; j++) {
				if(map[i][j] != '*' && isEdge(i, j)) right.addLast(new int[] {i, j});
			}
		}
	}
	private static boolean isEdge(int r, int c) {
		return r==0 || r==h-1 || c==0 || c==w-1;
	}
	private static boolean isIn(int r, int c) {
		return r >= 0 && c >= 0 && r < h && c < w;
	}
	private static boolean haveKey(char door) {
		return (key & (1 << door - 'A')) != 0;
	}
}
