package baekjoon.Quiz1790;

import java.io.*;

/*
Gold5: 수 이어 쓰기 2 / [math, implementation]
1. 1~8 자리 수에 해당하는지 확인
2. K - (이전 자리수의 총 개수) - 1 / 현재 자리수 > 몫 만큼 건너뛰고, 나머지 만큼 자리 이동
2-1. 몫 만큼 건너뛴 수가 N 보다 크다면 -1 출력
*/
public class Quiz1790 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] nk = br.readLine().split(" ");
        int N = Integer.parseInt(nk[0]);
        long K = Long.parseLong(nk[1]);

        long digit = 1;
        long count = 9;
        while(K > count * digit) {
            K -= (count * digit);
            digit++;
            count *= 10;
        }

        long quotient = (K - 1) / digit;
        long mod = (K - 1) % digit;
        long number = (long) Math.pow(10, digit - 1) + quotient;

        if(number > N) {
            System.out.println(-1);
        } else {
            System.out.println(String.valueOf(number).charAt((int) mod));
        }
    }
}
