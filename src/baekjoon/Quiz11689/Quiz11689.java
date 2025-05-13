package baekjoon.Quiz11689;

import java.io.*;

/*
Gold1: GCD(n, k) = 1 / [math]
*/
public class Quiz11689 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		long N = Long.parseLong(br.readLine());
		long ans = N;

		for(long i=2; i*i<=N; i++) {
			if(N % i == 0) {
				ans = ans / i * (i - 1);
			}
			while(N % i == 0) {
				N /= i;
			}
		}

		if(N!=1) {
			ans = ans / N * (N - 1);
		}

		System.out.println(ans);
	}
}
