package baekjoon.Quiz2036;

import java.util.*;
import java.io.*;

/*
 1. 단 0을 다빼
 2. 제 0이 있었다면 플래그를 true로 둬
 3. left는 양수만 내림차순으로 정렬, right는 음수만 내림차순으로 정렬
 4. left에서 두 개씩 꺼내서 처리한다.
 4-1. 두 개 모두 1보다 크다면, 곱하고 더한다.
 4-2. 하나라도 1이 있다면, 두 수 모두 더한다.
 5. right의 총 개수가 짝수라면, 두 개씩 꺼내서 처리한다.
 6.right의 총 개수가 홀수라면, 첫 원소를 별도로 처리한다.
 6-1. 0 플래그가 true라면, 두 번째 원소부터 두 개씩 곱하고 더한다.
 6-2. 0 플래그가 false라면, 첫 번째 원소는 더하고 나머지 원소들은 두 개씩 곱하고 더한다.
 7. 최대 점수는 long 타입이어야 한다.
 */

public class Quiz2036 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        LinkedList<Long> left = new LinkedList<>();
        LinkedList<Long> right = new LinkedList<>();
        boolean existZero = false;
        long totalScore = 0;

        for(int i=0; i<N; i++) {
            long num = Long.parseLong(br.readLine());
            if(num > 0) {
                left.add(num);
                continue;
            }

            if(num < 0) {
                right.add(num);
                continue;
            }

            existZero = true;
        }

        left.sort((o1, o2) -> Long.compare(o2, o1));
        right.sort((o1, o2) -> Long.compare(o2, o1));

        while(!left.isEmpty()) {
            long first = left.poll();

            if(left.isEmpty()) {
                totalScore += first;
                break;
            }

            long second = left.poll();

            if(first == 1 || second == 1) {
                totalScore += first + second;
                continue;
            }

            totalScore += first * second;
        }

        if(right.size() % 2 != 0) {
            long unpairedNumber = right.poll();
            if(!existZero) {
                totalScore += unpairedNumber;
            }
        }

        while(!right.isEmpty()) {
            long first = right.poll();
            long second = right.poll();
            totalScore += first * second;
        }

        System.out.println(totalScore);
    }
}
