package baekjoon.Quiz15989;

import java.io.*;

/*
Gold5: 1, 2, 3 더하기 4 / [dp]
1. 현재 구하고자 하는 숫자를 n이라고 했을 때,
1-1. n-3을 만들 수 있는 모든 경우의 수에 3을 더한 것 +
1-2. n-2을 만들 수 있는 모든 경우에서 3을 포함한 것을 제외한 것들에 2를 더한 것 +
1-3. n-1을 만들 수 있는 경우의 수 중, 1만 포함된 것에 1을 더한 것
2. 위 세 가지 과정을 통해 n을 구할 수 있다.
3. 점화식으로 표현하면, dp[n] = dp[n - 3] + (dp[n - 2] - dp[n - 5]) + 1
3-1. 현재 숫자를 표현할 수 있는 모든 경우의 수 중, 3을 포함하는 경우의 수는 현재 숫자 - 3의 경우의 수와 동일하다.
3-2. 현재 숫자를 표현할 수 있는 모든 경우의 수 중, 1만 포함하는 경우는 항상 하나다.
*/
public class Quiz15989 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		int[] dp = buildDP();

		StringBuilder ans = new StringBuilder();
		while(T-- > 0) {
			int n = Integer.parseInt(br.readLine());
			ans.append(dp[n]).append("\n");
		}

		System.out.println(ans);
	}

	private static int[] buildDP() {
		int[] dp = new int[10001];
		dp[0] = 1;
		dp[1] = 1;
		dp[2] = 2;
		dp[3] = 3;
		dp[4] = 4;

		for(int i=5; i<=10000; i++) {
			dp[i] = dp[i - 3] + (dp[i - 2] - dp[i - 5]) + 1;
		}

		return dp;
	}
}