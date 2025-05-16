package baekjoon.Quiz1684;

import java.io.*;
import java.util.*;

/*
Gold5: 같은 나머지 / [math]
1. 각 원소 차이들의 최대 공약수를 구한다.
*/
public class Quiz1684 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] numbers = Arrays.stream(br.readLine().split(" "))
                              .mapToInt(Integer::parseInt)
                              .toArray();
        Arrays.sort(numbers);
        int min = numbers[0];
        int ans = 0;
        for(int i=1; i<n; i++) {
            ans = gcd(ans, numbers[i] - min);
        }
        System.out.println(ans);
    }

    private static int gcd(int a, int b) {
        while(b != 0) {
            int remain = a % b;
            a = b;
            b = remain;
        }
        return a;
    }
}
