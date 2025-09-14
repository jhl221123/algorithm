package baekjoon.Quiz13241;

import java.io.*;

/*
Silver5: 최소공배수 / [math]
*/
public class Quiz13241 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] ab = br.readLine().split(" ");
		long a = Long.parseLong(ab[0]);
		long b = Long.parseLong(ab[1]);

		System.out.println((a * b) / gcd(a, b));
	}

	private static long gcd(long a, long b) {
		if(b == 0) {
			return a;
		}
		return gcd(b, a % b);
	}
}
