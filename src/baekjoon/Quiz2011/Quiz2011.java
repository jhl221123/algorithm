package baekjoon.Quiz2011;

import java.io.*;

/*
Gold5: 암호코드 / [DP]
1. 한 자리만 만들 수 있는 경우와 두 자리를 만들 수 있는 경우를 구분해 점화식을 구한다.
2. 0이 입력되었다면, 10 or 20을 제외하고 불가능한 경우라 판단할 수 있도록 처리한다.
 */
public class Quiz2011 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String code = br.readLine();
		int length = code.length();

		long[] dp = new long[length + 1];
		dp[length] = 1;
		dp[length - 1] = 1;
		if(code.charAt(length - 1) - '0' == 0) {
			dp[length - 1] = 0;
		}

		for(int i = length - 2; i >= 0; i--) {
			int a = code.charAt(i) - '0';
			int b = code.charAt(i + 1) - '0';

			if((a == 0 && b == 0) || (a > 2 && b == 0)) {
				System.out.println(0);
				return;
			}

			dp[i] = dp[i + 1];
			if(a == 0) {
				dp[i] = 0;
			}

			if(a > 0 && a * 10 + b <= 26) {
				dp[i] = (dp[i] + dp[i + 2]) % 1000000;
			}
		}

		System.out.println(dp[0]);
	}
}
