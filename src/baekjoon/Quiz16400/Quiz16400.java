package baekjoon.Quiz16400;

import java.io.*;
import java.util.*;

/*
Gold5: 소수 화폐 / [math]
*/
public class Quiz16400 {
	static final int MOD = 123456789;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());

		List<Integer> primes = getPrimesUpTo(N);

		int[] dp = new int[N + 1];
		dp[0] = 1;
		for (int prime : primes) {
			for (int i = prime; i <= N; i++) {
				dp[i] = (dp[i] + dp[i - prime]) % MOD;
			}
		}
		System.out.println(dp[N]);
	}

	private static List<Integer> getPrimesUpTo(int n) {
		boolean[] isNotPrime = new boolean[n + 1];
		isNotPrime[0] = isNotPrime[1] = true;

		for (int i = 2; i * i <= n; i++) {
			if (!isNotPrime[i]) {
				for (int j = i * i; j <= n; j += i) {
					isNotPrime[j] = true;
				}
			}
		}

		List<Integer> primes = new ArrayList<>();
		for (int i = 2; i <= n; i++) {
			if (!isNotPrime[i]) {
				primes.add(i);
			}
		}
		return primes;
	}
}
