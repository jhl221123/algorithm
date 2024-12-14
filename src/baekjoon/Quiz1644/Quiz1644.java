package baekjoon.Quiz1644;

import java.io.*;
import java.util.*;

public class Quiz1644 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		List<Integer> primes = getPrimesUnder(N);
		// 1. sum이 N 보다 크거나 같아질 때까지 r을 이동한다.
		// 1-1. N과 같아졌다면 count++
		// 1-2. r이 primes의 크기와 같아졌다면 종료
		// 2. sum이 N 보다 작거나 같아질 때까지 l을 이동한다.
		// 2-1. N과 같아졌다면 count++

		int totalPrimeCount = primes.size();
		int l = 0;
		int r = 0;
		int sum = 0;
		int ans = 0;
		while(true) {
			if(r == totalPrimeCount && sum < N) break;
			if(sum >= N) {
				if(sum == N) ans++;
				sum -= primes.get(l++);
			} else {
				sum += primes.get(r++);
			}
		}

		System.out.println(ans);
	}

	private static List<Integer> getPrimesUnder(int end) {
		boolean[] isPrime = new boolean[end + 1];
		Arrays.fill(isPrime, true);
		isPrime[0] = false;
		isPrime[1] = false;

		for(int i=2; i*i<=end; i++) {
			if(isPrime[i]) {
				for(int j=i*i; j<=end; j+=i) {
					isPrime[j] = false;
				}
			}
		}

		List<Integer> primes = new ArrayList<>();
		for(int i=0; i<=end; i++) {
			if(isPrime[i]) {
				primes.add(i);
			}
		}

		return primes;
	}
}
