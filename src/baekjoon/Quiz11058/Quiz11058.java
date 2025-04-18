package baekjoon.Quiz11058;

import java.io.*;

/*
Gold5: 크리보드 / [dp]
1. 복사 붙이기가 가능한 지점부터 불가능한 지점까지 순회하며 최대값을 갱신한다.
*/
public class Quiz11058 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        long[] dp = new long[N + 1];
        if(N < 6) {
            System.out.println(N);
            return;
        }

        for(int i=0; i<=6; i++) {
            dp[i] = i;
        }

        for(int i=7; i<=N; i++) {
            for(int j=3; j<=i-3; j++) {
                dp[i] = Math.max(dp[i], dp[i - j] * (long)(j - 1));
            }
        }

        System.out.println(dp[N]);
    }
}
