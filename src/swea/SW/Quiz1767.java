package swea.SW;

import java.util.*;
import java.io.*;
public class Quiz1767 {
	static int N;
	static int[][] map;
	static List<int[]> list;
	static int max;
	static int length;
	public static void main(String[] args) throws Exception
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		for(int test_case = 1; test_case <= T; test_case++)
		{
			N = Integer.parseInt(br.readLine());
			map = new int[N][N];
			list = new ArrayList<>();
			max = 0;
			length = Integer.MAX_VALUE;
			for(int i=0; i<N; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				for(int j=0; j<N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
					if(map[i][j] == 1 && !(i==0 || i==N-1 || j==0 || j==N-1)) list.add(new int[] {i, j});
				}
			}
			dfs(0, 0, 0);
			sb.append("#").append(test_case).append(" ").append(length).append("\n");
		}
		System.out.print(sb);
	}
	static int[] dy = {-1, 1, 0, 0, 0};
	static int[] dx = {0, 0, -1, 1, 0};
	static boolean isPossible(int[] point, int dir) {
		if(dir == 4) return true;
		int mr = point[0];
		int mc = point[1];
		while(true) {
			mr += dy[dir];
			mc += dx[dir];
			if(map[mr][mc] == 2 || map[mr][mc] == 1) return false;
			if(mr==0 || mr==N-1 || mc==0 || mc==N-1) return true;
		}
	}
	static int setStatus(int[] point, int dir, int type) {
		if(dir == 4) return 0;
		int mr = point[0];
		int mc = point[1];
		int currentLength = 0;
		while(true) {
			mr += dy[dir];
			mc += dx[dir];
			if(mr<0 || mr>=N || mc<0 || mc>=N) break;
			map[mr][mc] = type;
			currentLength++;
		}
		return currentLength;
	}
	static void dfs(int coreIdx, int coreCnt, int currentLength) {
		if(coreIdx == list.size()) {
			if(max < coreCnt) {
				max = coreCnt;
				length = currentLength;
			}
			else if(max == coreCnt) length = Math.min(length, currentLength);
			return;
		}
		for(int i=0; i<4; i++) {
			int[] point = list.get(coreIdx);
			if(isPossible(point, i)) {
				int length = setStatus(point, i, 2);
				dfs(coreIdx+1, coreCnt+1, currentLength+length);
				setStatus(point, i, 0);
			}
		}
		dfs(coreIdx+1, coreCnt, currentLength);
	}
}
