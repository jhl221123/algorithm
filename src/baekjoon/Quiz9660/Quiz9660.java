package baekjoon.Quiz9660;

import java.io.*;
import java.math.BigInteger;

/*
Gold5: 돌 게임 6 / [game theory]
1. 게임 진행 횟수를 7로 나누었을 때, 나머지가 0 혹은 2 일 경우 상대편이 이긴다.
*/
public class Quiz9660 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        long N = Long.parseLong(br.readLine());
        BigInteger n = BigInteger.valueOf(N);

        BigInteger mod = n.mod(BigInteger.valueOf(7L));
        if(mod.equals(BigInteger.ZERO) || mod.equals(BigInteger.TWO)) {
            System.out.println("CY");
        } else {
            System.out.println("SK");
        }
    }
}
