package baekjoon.Quiz20500;

import java.io.*;

/*
Gold5: Ezreal 여눈부터 가네 ㅈㅈ / [dp, math]
1. 3의 배수는 각 자리의 합이 3의 배수여야 한다.
2. 5의 배수는 1의 자리가 0 또는 5이어야 한다.
3. 따라서 1의 자리는 5로 고정하고, N - 1 자리의 합을 3으로 나누었을 때 나머지가 1이어야 한다.
*/
public class Quiz20500 {

    private static final int MOD = 1_000_000_007;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        if(N == 1) {
            System.out.println(0);
            return;
        }

        int[][] dp = new int[3][N + 1];
        dp[0][2] = 1;
        dp[1][2] = 1;

        for(int i=3; i<=N; i++) {
            dp[0][i] = (dp[1][i-1] + dp[2][i-1]) % MOD;
            dp[1][i] = (dp[0][i-1] + dp[2][i-1]) % MOD;
            dp[2][i] = (dp[0][i-1] + dp[1][i-1]) % MOD;
        }

        System.out.println(dp[0][N]);
    }
}
