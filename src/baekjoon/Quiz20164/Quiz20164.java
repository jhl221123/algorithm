package baekjoon.Quiz20164;

import java.io.*;

/*
Gold5: 홀수 홀릭 호석 / [implementation, dfs]
1. 각 자리를 순회하며 홀수의 개수를 구한다.
2. 수를 분할한다.
2-1. 수가 한 자리라면, 이제까지의 합을 최소, 최대와 비교한다.
2-2. 수가 두 자리라면, 다음 수는 각 자리수의 합이다.
2-3. 수가 세 자리 이상이라면, 3분할 가능한 모든 수를 확인한다.
*/
public class Quiz20164 {

	private static int min = 200_000_000;
	private static int max = 0;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String N = br.readLine();
		dfs(N, 0);
		System.out.println(min + " " + max);
	}

	private static void dfs(String num, int oddCount) {
		oddCount += calculateOddCount(num);

		if(num.length() <= 1) {
			min = Math.min(min, oddCount);
			max = Math.max(max, oddCount);
			return;
		}

		if(num.length() == 2) {
			dfs(String.valueOf(toInt(num, 0) + toInt(num, 1)), oddCount);
		}

		for(int i=1; i<num.length()-1; i++) {
			for(int j=i+1; j<num.length(); j++) {
				int sub1 = Integer.parseInt(num.substring(0, i));
				int sub2 = Integer.parseInt(num.substring(i, j));
				int sub3 = Integer.parseInt(num.substring(j));
				dfs(String.valueOf(sub1 + sub2 + sub3), oddCount);
			}
		}
	}

	private static int calculateOddCount(String num) {
		int count = 0;
		for(int i=0; i<num.length(); i++) {
			if((num.charAt(i) - '0') % 2 == 1) {
				count++;
			}
		}
		return count;
	}

	private static int toInt(String num, int digit) {
		return num.charAt(digit) - '0';
	}
}
