package baekjoon.Quiz11401;

import java.io.*;
import java.util.*;

public class Quiz11401 {

	private static final long PRIME = 1_000_000_007L;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		long N = Long.parseLong(st.nextToken());
		long K = Long.parseLong(st.nextToken());

		long numerator = factorial(N);
		long denominator = factorial(K) * factorial(N - K) % PRIME;
		long result = numerator * pow(denominator, PRIME - 2L) % PRIME;

		System.out.println(result);
	}

	private static long factorial(long number) {
		long factorial = 1L;

		while(number > 1L) {
			factorial = factorial * number % PRIME;
			number--;
		}

		return factorial;
	}

	private static long pow(long base, long exponent) {
		if(exponent == 1L) {
			return base % PRIME;
		}

		long halfPower = pow(base, exponent / 2);
		if(exponent % 2 == 1) {
			return (halfPower * halfPower % PRIME) * base % PRIME;
		}
		return halfPower * halfPower % PRIME;
	}
}
