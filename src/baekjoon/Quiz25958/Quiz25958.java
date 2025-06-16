package baekjoon.Quiz25958;

import java.io.*;
import java.util.*;

/*
Gold4: 예쁜수 / [math, dp]
*/
public class Quiz25958 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] MK = br.readLine().split(" ");
		int M = Integer.parseInt(MK[0]);
		int K = Integer.parseInt(MK[1]);
		List<Integer> prettyNumbers = new ArrayList<>();

		for(int number=1; number<=M; number++) {
			if(number % getSumOfDigit(number) == 0) {
				prettyNumbers.add(number);
			}
		}

		int[] dp = new int[M + 1];
		dp[0] = 1;
		for(int prettyNumber : prettyNumbers) {
			for(int i=prettyNumber; i<=M; i++) {
				dp[i] = (dp[i] + dp[i - prettyNumber]) % K;
			}
		}

		System.out.println(dp[M]);
	}

	private static int getSumOfDigit(int number) {
		int sum = 0;
		while(number > 0) {
			sum += number % 10;
			number /= 10;
		}
		return sum;
	}
}
