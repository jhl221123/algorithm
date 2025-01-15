package baekjoon.Quiz2831;

import java.util.*;
import java.io.*;

/*
1. 남, 여 각각 부호에 따라 분리한다.
1-1. 남자 음수 오름차순, 여자 양수 내림차순
1-2. 남자 양수 내림차순, 여자 음수 오름차순
2. 합이 음수가 되는 쌍의 개수를 구한다.
2-1. 남자 음수와 여자 양수 각 첫 번째 원소를 합한다.
2-1-1. 결과가 0보다 크거나 같다면 여자 양수 제거
2-1-2. 결과가 0보다 작으면 둘 다 제거
2-2. 남자 양수와 여자 음수 각 첫 번째 원소를 합한다.
2-2-1. 결과가 0보다 크거나 같다면 남자 양수 제거
2-2-2. 결과가 0보다 작으면 둘 다 제거
*/

public class Quiz2831 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        PriorityQueue<Integer> menWantedSmaller = new PriorityQueue<>();
        PriorityQueue<Integer> menWantedBigger = new PriorityQueue<>((o1, o2) -> o2 - o1);
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i=0; i<N; i++) {
            int height = Integer.parseInt(st.nextToken());
            if(height > 0) menWantedBigger.offer(height);
            else menWantedSmaller.offer(height);
        }

        PriorityQueue<Integer> womenWantedSmaller = new PriorityQueue<>();
        PriorityQueue<Integer> womenWantedBigger = new PriorityQueue<>((o1, o2) -> o2 - o1);
        st = new StringTokenizer(br.readLine());
        for(int i=0; i<N; i++) {
            int height = Integer.parseInt(st.nextToken());
            if(height > 0) womenWantedBigger.offer(height);
            else womenWantedSmaller.offer(height);
        }

        int totalPairs = 0;
        totalPairs += countPairs(menWantedSmaller, womenWantedBigger);
        totalPairs += countPairs(womenWantedSmaller, menWantedBigger);

        System.out.println(totalPairs);
    }

    private static int countPairs(PriorityQueue<Integer> wantedSmaller, PriorityQueue<Integer> wantedBigger) {
        int pairs = 0;

        while(!wantedSmaller.isEmpty() && !wantedBigger.isEmpty()) {
            if(wantedBigger.peek() + wantedSmaller.peek() >= 0) {
                wantedBigger.poll();
                continue;
            }

            pairs++;
            wantedBigger.poll();
            wantedSmaller.poll();
        }

        return pairs;
    }
}
