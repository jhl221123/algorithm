package baekjoon.Quiz17845;

import java.io.*;

/*
Gold5: 수강 과목 / [dp, knapsack]
1. 공부시간을 사용할 수 있는 부분을 갱신하며 최대 중요도를 구한다.
*/
public class Quiz17845 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] nk = br.readLine().split(" ");
        int N = Integer.parseInt(nk[0]);
        int K = Integer.parseInt(nk[1]);
        int[][] subjects = new int[K][2];
        for(int i=0; i<K; i++) {
            String[] it = br.readLine().split(" ");
            int I = Integer.parseInt(it[0]);
            int T = Integer.parseInt(it[1]);
            subjects[i][0] = I;
            subjects[i][1] = T;
        }

        int[] dp = new int[N + 1];
        for(int i=0; i<K; i++) {
            int I = subjects[i][0];
            int T = subjects[i][1];

            for(int j=N; j>=T; j--) {
                dp[j] = Math.max(dp[j], dp[j - T] + I);
            }
        }

        System.out.println(dp[N]);
    }
}
