package baekjoon.Quiz6593;

import java.util.*;
import java.io.*;

/*
Gold5: 상범빌딩 / [bfs, graph]
1. 6방향 탐색하며 도착지에 도달할 수 있는지 확인한다.
2. 도착지에 도달 여부에 따라 적합한 형식을 출력한다.
*/
public class Quiz6593 {

	private static int L, R, C;
	private static boolean[][][] building;
	private static boolean[][][] visited;
	private static int[] start;
	private static int[] end;
	private static int[] dz = {0, 0, 0, 0, 1, -1};
	private static int[] dy = {1, -1, 0, 0, 0, 0};
	private static int[] dx = {0, 0, -1, 1, 0, 0};

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder ans = new StringBuilder();

		while(true) {
			String[] lrc = br.readLine().split(" ");
			L = Integer.parseInt(lrc[0]);
			R = Integer.parseInt(lrc[1]);
			C = Integer.parseInt(lrc[2]);

			if(L==0 && R==0 && C==0) {
				break;
			}

			visited = new boolean[L][R][C];
			initializeBuilding(br);
			traverseBuilding(ans);
		}

		System.out.print(ans);
	}

	private static void traverseBuilding(StringBuilder ans) {
		ArrayDeque<int[]> ad = new ArrayDeque<>();
		ad.addLast(start);

		boolean isEscape = false;
		int escapeMinute = 0;
		while(!ad.isEmpty()) {
			int[] node = ad.removeFirst();
			int l = node[0];
			int r = node[1];
			int c = node[2];
			int minute = node[3];

			if(l==end[0] && r==end[1] && c==end[2]) {
				isEscape = true;
				escapeMinute = minute;
			}

			if(visited[l][r][c]) {
				continue;
			}
			visited[l][r][c] = true;

			for(int d=0; d<6; d++) {
				int ml = l + dz[d];
				int mr = r + dy[d];
				int mc = c + dx[d];

				if(ml>=0 && mr>=0 && mc>=0 && ml<L && mr<R && mc<C && !visited[ml][mr][mc] && building[ml][mr][mc]) {
					ad.addLast(new int[] {ml, mr, mc, minute + 1});
				}
			}
		}

		if(isEscape) {
			ans.append("Escaped in ").append(escapeMinute).append(" minute(s).").append("\n");
		} else {
			ans.append("Trapped!").append("\n");
		}
	}

	private static void initializeBuilding(BufferedReader br) throws IOException {
		building = new boolean[L][R][C];
		for(int l=0; l<L; l++) {
			for(int r=0; r<R; r++){
				String line = br.readLine();

				for(int c=0; c<C; c++) {
					if(line.charAt(c) == '.') {
						building[l][r][c] = true;
					}

					if(line.charAt(c) == 'S') {
						building[l][r][c] = true;
						start = new int[] {l, r, c, 0};
					}

					if(line.charAt(c) == 'E') {
						building[l][r][c] = true;
						end = new int[] {l, r, c};
					}
				}
			}

			br.readLine();
		}
	}
}
