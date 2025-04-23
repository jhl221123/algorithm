package baekjoon.Quiz3067;

import java.io.*;
import java.util.*;

/*
Gold5: Coins / [dp, knapsack]
1. 동전을 순회하며 현재 동전이 추가되었을 떄 가능한 방법수를 갱신한다.
*/
public class Quiz3067 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T  = Integer.parseInt(br.readLine());
        StringBuilder ans = new StringBuilder();

        while(T-- > 0) {
            int N = Integer.parseInt(br.readLine());
            int[] coins = Arrays.stream(br.readLine().split(" "))
                    .mapToInt(Integer::parseInt)
                    .toArray();
            int M = Integer.parseInt(br.readLine());
            ans.append(calculateCount(coins, M)).append("\n");
        }

        System.out.print(ans);
    }

    private static int calculateCount(int[] coins, int M) {
        int[] dp = new int[M + 1];
        dp[0] = 1;

        for(int i=0; i<coins.length; i++) {
            for(int j=coins[i]; j<=M; j++) {
                dp[j] += dp[j - coins[i]];
            }
        }

        return dp[M];
    }
}
