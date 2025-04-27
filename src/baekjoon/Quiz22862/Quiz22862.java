package baekjoon.Quiz22862;

import java.util.*;
import java.io.*;

/*
Gold5: 가장 긴 짝수 연속한 부분 수열 (large) / [two-pointer]
1. 짝수를 만날때까지 l, r 함께 이동
2. 짝수를 만나면 r만 이동
2-1. 이동한 r이 짝수라면 len++;
2-2. 이동한 r이 홀수라면
2-2-1. K > 0 이라면 K--
2-2-2. K <= 0 이라면 len-- 이후 l 이동
2-2-2-1. l이 이동한 원소가 홀수라면 K++
2-2-2-2. l이 이동한 원소가 짝수라면 continue
3. r >= N 이라면 종료
*/
public class Quiz22862 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] nk = br.readLine().split(" ");
        int N = Integer.parseInt(nk[0]);
        int K = Integer.parseInt(nk[1]);
        int[] S = Arrays.stream(br.readLine().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();

        int l = 0;
        int r = 0;
        int len = 0;
        for(int i=0; i<N; i++) {
            if(S[i] % 2 == 0) {
                l = i;
                r = i;
                len++;
                break;
            }
        }

        int max = len;
        while(true) {
            r++;
            if(r >= N) break;
            if(S[r] % 2 == 0) {
                len++;
                max = Math.max(max, len);
            } else {
                if(K > 0) {
                    K--;
                } else {
                    r--;
                    if(S[l] % 2 == 0) {
                        len--;
                        l++;
                    } else {
                        l++;
                        K++;
                    }
                }
            }
        }

        System.out.println(max);
    }
}
