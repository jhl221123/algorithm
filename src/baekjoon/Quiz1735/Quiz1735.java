package baekjoon.Quiz1735;

import java.io.*;

/*
Silver3: 분수 합 / [math]
*/
public class Quiz1735 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] ab = br.readLine().split(" ");
		String[] cd = br.readLine().split(" ");
		int a = Integer.parseInt(ab[0]);
		int b = Integer.parseInt(ab[1]);
		int c = Integer.parseInt(cd[0]);
		int d = Integer.parseInt(cd[1]);

		int top = a * d + c * b;
		int bottom = b * d;
		int gcd = gcd(top, bottom);
		System.out.println(top / gcd + " " + bottom / gcd);
	}

	private static int gcd(int a, int b) {
		if(a <= b) {
			int temp = a;
			a = b;
			b = temp;
		}

		if(b == 0) {
			return a;
		}

		return gcd(b, a % b);
	}
}
