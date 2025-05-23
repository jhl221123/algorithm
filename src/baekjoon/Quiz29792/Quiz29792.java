package baekjoon.Quiz29792;

import java.io.*;
import java.util.*;

/*
Gold5: 규칙적인 보스돌이 / [dp]
*/
public class Quiz29792 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] NMK = br.readLine().split(" ");
		int N = Integer.parseInt(NMK[0]);
		int M = Integer.parseInt(NMK[1]);
		int K = Integer.parseInt(NMK[2]);
		PriorityQueue<Long> damages = new PriorityQueue<>(Comparator.reverseOrder());
		for(int i=0; i<N; i++) {
			damages.offer(Long.parseLong(br.readLine()));
		}
		long[][] monsters = new long[K][2];
		for(int i=0; i<K; i++) {
			String[] PQ = br.readLine().split(" ");
			monsters[i][0] = Long.parseLong(PQ[0]);
			monsters[i][1] = Long.parseLong(PQ[1]);
		}

		int max = 0;
		for(int i=0; i<M; i++) {
			int[] dp = new int[901];
			long damage = damages.poll();

			for(int j=0; j<K; j++) {
				long huntingTime = monsters[j][0] / damage;
				if(monsters[j][0] % damage > 0) huntingTime += 1;
				if(huntingTime > 900) continue;
				for(int l=900; l>=huntingTime; l--) {
					dp[l] = Math.max(dp[l], dp[(int)(l-huntingTime)] + (int)monsters[j][1]);
				}
			}

			max += dp[900];
		}

		System.out.println(max);
	}
}
