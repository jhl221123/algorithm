package baekjoon.Quiz2436;

import java.io.*;

/*
Gold5: 공약수 / [math, euclidean]
1. LCM / GCD 값을 계산한다.
2. 이 값을 두 수 x, y의 곱으로 분해하는데, x와 y가 서로소여야 한다.
3. x, y의 곱이 LCM / GCD를 만족하고 서로소인 경우, A = x * GCD, B = y * GCD를 만족한다.
4. A + B가 최소인 경우를 찾는다.
*/
public class Quiz2436 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] gl = br.readLine().split(" ");
		int gcd = Integer.parseInt(gl[0]);
		int lcm = Integer.parseInt(gl[1]);
		int lcmDividedByGcd = lcm / gcd;

		int number = 0;
		int pair = 0;
		for(int i=1; i*i<=lcmDividedByGcd; i++) {
			if(lcmDividedByGcd % i == 0) {
				if(calculateGCD(i, lcmDividedByGcd / i) == 1) {
					number = i * gcd;
					pair = lcmDividedByGcd / i * gcd;
				}
			}
		}

		System.out.println(number + " " + pair);
	}

	private static int calculateGCD(int number, int pair) {
		return number % pair == 0 ? pair : calculateGCD(pair, number % pair);
	}
}
