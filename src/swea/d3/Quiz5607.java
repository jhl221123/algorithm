package swea.d3;

import java.util.*;
import java.io.*;

public class Quiz5607 {

    static final long MOD = 1234567891;
    static long[] facMod = new long[1000001];

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        facMod[0] = 1;

        for(int i=1; i<=1000000; i++) {
            facMod[i] = (facMod[i-1] * i) % MOD;
        }

        for(int test_case = 1; test_case <= T; test_case++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int R = Integer.parseInt(st.nextToken());
            long c = facMod[N-R] * facMod[R] % MOD;
            long ra = fpow(c, MOD - 2);
            long ans = facMod[N] * ra % MOD;

            sb.append("#").append(test_case).append(" ").append(ans).append("\n");
        }

        System.out.println(sb);
    }

    static long fpow(long C, long n) {
        if(n==1) return C;
        long tmp = fpow(C, n/2);
        if(n%2==0) return (tmp % MOD * tmp % MOD) % MOD;
        else return (tmp % MOD * tmp % MOD * C % MOD) % MOD;
    }
}
