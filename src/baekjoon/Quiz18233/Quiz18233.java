package baekjoon.Quiz18233;

import java.io.*;

/*
Gold5: 러버덕을 사랑하는 모임 / [greedy, brute force]
1. P명의 사람을 조합으로 구한다.
2. 그룹의 최소, 최대를 구하고 E개가 포함되어 있는지 확인한다.
2-1. 포함되어 있지 않다면 계속해서 탐색한다.
2-2. 포함되어 있다면, 최소에서 한 명씩 최대치로 올리며 E를 충족하는 개수를 구한다.
*/
public class Quiz18233 {

	private static int N, P, E;
	private static int[] tgt;
	private static int[][] range;
	private static boolean isPossible = false;
	private static int[] ans;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] npe = br.readLine().split(" ");
		N = Integer.parseInt(npe[0]);
		P = Integer.parseInt(npe[1]);
		E = Integer.parseInt(npe[2]);
		range = new int[N + 1][2];
		for(int i=1; i<=N; i++) {
			String[] r = br.readLine().split(" ");
			range[i][0] = Integer.parseInt(r[0]);
			range[i][1] = Integer.parseInt(r[1]);
		}

		if(P > N) {
			System.out.println(-1);
			return;
		}

		tgt = new int[P];
		combination(1, 0);

		if(isPossible) {
			StringBuilder sb = new StringBuilder();
			for(int i=1; i<=N; i++) {
				sb.append(ans[i]).append(" ");
			}
			System.out.println(sb);
		} else {
			System.out.println(-1);
		}
	}

	private static void combination(int num, int tgtIdx) {
		if(tgtIdx == P) {
			if(isPossibleRange()) {
				calculate();
			}

			return;
		}

		if(num > N) {
			return;
		}

		tgt[tgtIdx] = num;
		combination(num + 1, tgtIdx + 1);
		combination(num + 1, tgtIdx);
	}

	private static void calculate() {
		isPossible = true;

		ans = new int[N + 1];
		int min = 0;
		for(int i=0; i<P; i++) {
			ans[tgt[i]] = range[tgt[i]][0];
			min += range[tgt[i]][0];
		}

		int pivot = 0;
		int remain = E - min;
		while(remain > 0) {
			if (ans[tgt[pivot]] >= range[tgt[pivot]][1]) {
				pivot++;
				continue;
			}

			int delta = Math.min(remain, range[tgt[pivot]][1] - ans[tgt[pivot]]);
			ans[tgt[pivot]] += delta;
			remain -= delta;
		}
	}

	private static boolean isPossibleRange() {
		int left = 0;
		int right = 0;

		for(int i=0; i<P; i++) {
			int num = tgt[i];
			left += range[num][0];
			right += range[num][1];
		}

		return E >= left && E <= right;
	}
}
