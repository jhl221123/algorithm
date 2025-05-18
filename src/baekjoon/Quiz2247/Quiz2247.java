package baekjoon.Quiz2247;

import java.io.*;

/*
Gold5: 실질적 약수 / [math]
1. n까지 순회하며 약수로 사용되는 개수를 구한 후 합계를 구한다.
*/
public class Quiz2247 {

    private static final int MOD = 1_000_000;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        long sum = 0;

        for(int i=2; i<n; i++) {
            sum += (long) (n / i - 1) * i;
        }

        System.out.println(sum % MOD);
    }
}
