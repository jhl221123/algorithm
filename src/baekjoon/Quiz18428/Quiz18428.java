package baekjoon.Quiz18428;

import java.util.*;
import java.io.*;

/*
Gold5: 감시 피하기 / [brute-force, dfs]
1. 장애물이 위치할 수 있는 공간을 조합으로 구한다.
2. 현재 장애물 배치로 감시를 피할 수 있는지 확인한다.
3. 감시를 피할 수 있는 장애물 구성을 발견하면 YES, 발견하지 못하면 NO를 출력한다.
*/
public class Quiz18428 {

	private static final int T = 2;
	private static final int S = 1;
	private static final int O = 0;
	private static final int X = -1;

	private static int N;
	private static int[][] map;
	private static List<int[]> ts;
	private static List<int[]> bs;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		map = new int[N+1][N+1];
		ts = new ArrayList<>();
		bs = new ArrayList<>();

		for(int row=1; row<=N; row++) {
			String[] line = br.readLine().split(" ");

			for(int col=1; col<=N; col++) {
				int no = stringToInt(line[col-1]);
				if(no == T) {
					ts.add(new int[] {row, col});
				} else if(no == -1) {
					bs.add(new int[] {row, col});
				}

				map[row][col] = no;
			}
		}

		int[] tgt = new int[3];
		boolean result = comb(0, 0, tgt);
		System.out.println(result ? "YES" : "NO");
	}

	private static boolean comb(int srcIdx, int tgtIdx, int[] tgt) {
		if(tgtIdx == 3) {
			return canAvoid(tgt);
		}

		if(srcIdx >= bs.size()) {
			return false;
		}

		tgt[tgtIdx] = srcIdx;
		if(comb(srcIdx + 1, tgtIdx + 1, tgt)) {
			return true;
		}

		return comb(srcIdx + 1, tgtIdx, tgt);
	}

	private static boolean canAvoid(int[] tgt) {
		int[] dy = {-1, 1, 0, 0};
		int[] dx = {0, 0, -1, 1};
		boolean result = true;

		for(int i=0; i<3; i++) {
			int[] o = bs.get(tgt[i]);
			map[o[0]][o[1]] = O;
		}

		bk: for(int[] tp : ts) {
			int tr = tp[0];
			int tc = tp[1];

			for(int d=0; d<4; d++) {
				int tmr = tr;
				int tmc = tc;

				while(true) {
					tmr += dy[d];
					tmc += dx[d];

					if(tmr<1 || tmr>N || tmc<1 || tmc>N || map[tmr][tmc] == O) {
						break;
					}

					if(map[tmr][tmc] == S) {
						result = false;
						break bk;
					}
				}
			}
		}

		for(int i=0; i<3; i++) {
			int[] o = bs.get(tgt[i]);
			map[o[0]][o[1]] = X;
		}

		return result;
	}

	private static int stringToInt(String s) {
		if("T".equals(s)) return T;
		if("S".equals(s)) return S;
		if("X".equals(s)) return X;
		else return -100;
	}
}
