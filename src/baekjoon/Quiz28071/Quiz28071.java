package baekjoon.Quiz28071;

import java.util.*;
import java.io.*;

/*
Gold3: 승현이의 사탕 사기 / [dp]
1. 구매한 사탕 박스 개수에 따라 나올수 있는 모든 나머지에 대해 최대값을 갱신한다.
2. 1 ~ M개의 사탕 박스를 순회하며 나머지가 0인 것 중 최대값을 출력한다.
*/
public class Quiz28071 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] NMK = br.readLine().split(" ");
		int N = Integer.parseInt(NMK[0]);
		int M = Integer.parseInt(NMK[1]);
		int K = Integer.parseInt(NMK[2]);

		int[] candyBoxes = Arrays.stream(br.readLine().split(" "))
			.mapToInt(Integer::parseInt).toArray();
		int[][] dp = new int[M + 1][K];
		for(int i=0; i<N; i++) {
			int mod = candyBoxes[i] % K;
			dp[1][mod] = Math.max(dp[1][mod], candyBoxes[i]);
		}

		for(int i=2; i<=M; i++) {
			for(int j=0; j<N; j++) {
				for(int k=0; k<K; k++) {
					int prior = Math.floorMod(k - candyBoxes[j], K);
					if(dp[i-1][prior] == 0) continue;
					dp[i][k] = Math.max(dp[i][k], dp[i-1][prior] + candyBoxes[j]);
				}
			}
		}

		int max = 0;
		for(int i=0; i<=M; i++) {
			max = Math.max(max, dp[i][0]);
		}
		System.out.println(max);
	}
}
