package baekjoon.Quiz25381;

import java.util.*;
import java.io.*;

/*
1. 문자 A, B, C 위치 순서를 각각 나누어 관리한다.
2. A의 마지막 원소와 B의 마지막 원소를 비교한다.
2-1. A의 마지막 원소 위치보다 B의 마지막 원소 위치가 더 크다면 쌍을 이룬다.
3. B의 마지막 원소와 C의 마지막 원소를 비교한다.
3-1. B의 마지막 원소 위치보다 C의 마지막 원소 위치가 더 크다면 쌍을 이룬다.
 */

public class Quiz25381 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String S = br.readLine();
        LinkedList<Integer> orderA = new LinkedList<>();
        LinkedList<Integer> orderB = new LinkedList<>();
        LinkedList<Integer> orderC = new LinkedList<>();
        int count = 0;

        for(int i=0; i<S.length(); i++) {
            if(S.charAt(i) == 'A') orderA.add(i);
            if(S.charAt(i) == 'B') orderB.add(i);
            if(S.charAt(i) == 'C') orderC.add(i);
        }

        while(!orderA.isEmpty() && !orderB.isEmpty()) {
            int aIdx = orderA.removeLast();
            if(orderB.getLast() > aIdx) {
                orderB.removeLast();
                count++;
            }
        }

        while(!orderB.isEmpty() && !orderC.isEmpty()) {
            int bIdx = orderB.removeLast();
            if(orderC.getLast() > bIdx) {
                orderC.removeLast();
                count++;
            }
        }

        System.out.println(count);
    }
}
