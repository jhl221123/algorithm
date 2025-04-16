package baekjoon.Quiz15661;

import java.util.*;
import java.io.*;

/*
Gold5: 링크와 스타트 / [brute-force, dfs]
1. 비트마스킹으로 팀을 구분한다.
2. 재귀로 각 팀의 능력치를 구한 후, 최소값을 갱신한다.
*/
public class Quiz15661_dfs {

	private static int N;
	private static int[][] map;
	private static int minScoreGap = 40000;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		map = new int[N + 1][N + 1];
		for(int row=1; row<=N; row++) {
			int[] info = Arrays.stream(br.readLine().split(" "))
				.mapToInt(Integer::parseInt)
				.toArray();

			for(int col=1; col<=N; col++) {
				map[row][col] = info[col - 1];
			}
		}

		for(int mask=0; mask<(1 << N); mask++) {
			updateMinScoreGap(mask);
		}

		System.out.println(minScoreGap);
	}

	private static void updateMinScoreGap(int mask) {
		List<Integer> link = new ArrayList<>();
		List<Integer> start = new ArrayList<>();

		for(int i=0; i<N; i++) {
			if((mask & (1 << i)) != 0) {
				link.add(i);
			} else {
				start.add(i);
			}
		}

		if(link.isEmpty() || start.isEmpty()) {
			return;
		}

		int linkScore = comb(0, link, 0, new int[2]);
		int startScore = comb(0, start, 0, new int[2]);

		minScoreGap = Math.min(minScoreGap, Math.abs(linkScore - startScore));
	}

	private static int comb(int srcIdx, List<Integer> src, int tgtIdx, int[] tgt) {
		if(tgtIdx >= tgt.length) {
			int player = src.get(tgt[0]) + 1;
			int pair = src.get(tgt[1]) + 1;
			return map[player][pair] + map[pair][player];
		}

		if(srcIdx >= src.size()) {
			return 0;
		}

		tgt[tgtIdx] = srcIdx;
		int tmp = comb(srcIdx + 1, src, tgtIdx + 1, tgt);
		return comb(srcIdx + 1, src, tgtIdx, tgt) + tmp;
	}
}
