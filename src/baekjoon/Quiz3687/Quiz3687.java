package baekjoon.Quiz3687;

import java.io.*;
import java.math.BigInteger;

public class Quiz3687 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();

		int[] counts = getMatchStickCountArr();
		BigInteger[][] max = getMaxDPArr();
		BigInteger[][] min = getMinDPArr();

		calculateMaxValues(counts, max);
		calculateMinValues(counts, min);

		while(T-- > 0) {
			int N = Integer.parseInt(br.readLine());
			sb.append(min[N][0]).append(" ").append(max[N][0]).append("\n");
		}

		System.out.print(sb);
	}

	private static void calculateMaxValues(int[] counts, BigInteger[][] max) {
		for(int i=2; i<=100; i++) {
			for(int j=0; j<10; j++) {
				if(i - counts[j] < 0) continue;
				BigInteger next1 = max[i - counts[j]][0].add(max[i - counts[j]][1].multiply(BigInteger.valueOf(j)));
				BigInteger next2 = max[i - counts[j]][0].multiply(BigInteger.TEN).add(BigInteger.valueOf(j));

				BigInteger next = next1.compareTo(next2) > 0 ? next1 : next2;

				if(next.compareTo(max[i][0]) > 0) {
					max[i][0] = next;
					max[i][1] = max[i - counts[j]][1].multiply(BigInteger.TEN);
				}
			}
		}
	}

	private static void calculateMinValues(int[] counts, BigInteger[][] min) {
		for(int i=2; i<=100; i++) {
			for(int j=0; j<10; j++) {
				if(i - counts[j] < 0) continue;
				BigInteger next1 = min[i - counts[j]][0].add(min[i - counts[j]][1].multiply(BigInteger.valueOf(j)));
				BigInteger next2 = min[i - counts[j]][0].multiply(BigInteger.TEN).add(BigInteger.valueOf(j));

				BigInteger next = (j == 0) ? next2 : next1.compareTo(next2) < 0 ? next1 : next2;

				if(next.compareTo(min[i][0]) < 0 || min[i][0].equals(BigInteger.ZERO)) {
					min[i][0] = next;
					min[i][1] = min[i - counts[j]][1].multiply(BigInteger.TEN);
				}
			}
		}
	}

	private static BigInteger[][] getMaxDPArr() {
		BigInteger[][] dp = new BigInteger[101][2];
		for(int i=0; i<=100; i++) {
			dp[i][0] = BigInteger.ZERO;
		}
		for(int i=0; i<=100; i++) {
			dp[i][1] = BigInteger.ONE;
		}
		return dp;
	}

	private static BigInteger[][] getMinDPArr() {
		BigInteger[][] dp = new BigInteger[101][2];
		for(int i=0; i<=100; i++) {
			dp[i][0] = BigInteger.valueOf(Long.MAX_VALUE);
		}
		for(int i=0; i<=100; i++) {
			dp[i][1] = BigInteger.TEN;
		}
		dp[2][0] = BigInteger.ONE;
		dp[3][0] = BigInteger.valueOf(7L);
		dp[4][0] = BigInteger.valueOf(4L);
		dp[5][0] = BigInteger.valueOf(2L);
		dp[6][0] = BigInteger.valueOf(6L);
		dp[7][0] = BigInteger.valueOf(8L);
		return dp;
	}

	private static int[] getMatchStickCountArr() {
		int[] arr = new int[10];
		arr[0] = 6;
		arr[1] = 2;
		arr[2] = 5;
		arr[3] = 5;
		arr[4] = 4;
		arr[5] = 5;
		arr[6] = 6;
		arr[7] = 3;
		arr[8] = 7;
		arr[9] = 6;
		return arr;
	}
}
