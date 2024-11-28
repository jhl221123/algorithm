package baekjoon.Quiz1334;

import java.math.BigInteger;
import java.io.*;

public class Quiz1334 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String num = br.readLine();
		int length = num.length();
		String result;

		if (new BigInteger(num).compareTo(BigInteger.TEN) < 0) {
			if (num.equals("9")) {
				result = "11";
			} else {
				result = new BigInteger(num).add(BigInteger.ONE).toString();
			}
		} else {
			String left = num.substring(0, length / 2);
			String right = num.substring((length + 1) / 2);
			String reversedLeft = new StringBuilder(left).reverse().toString();

			if (new BigInteger(reversedLeft).compareTo(new BigInteger(right)) > 0) {
				result = num.substring(0, (length + 1) / 2) + reversedLeft;
			} else {
				String newLeft = new BigInteger(num.substring(0, (length + 1) / 2)).add(BigInteger.ONE).toString();
				String newRight = new StringBuilder(newLeft.substring(0, length / 2)).reverse().toString();
				result = newLeft + newRight;
			}
		}

		System.out.println(result);
	}
}
