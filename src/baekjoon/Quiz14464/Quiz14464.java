package baekjoon.Quiz14464;

import java.util.*;
import java.io.*;

/*
1. 소, 닭 오름차순
2. 닭을 순회하면서 도움 받을 수 있는 최적의 소를 구한다.
2-1. 닭보다 시작 지점이 빠른 소들의 끝 지점을 우선순위 큐에 넣는다.
2-2. 우선순위 큐를 순회하면서 닭보다 끝 지점이 작은 소들을 제거한다.
2-3. 우선순위 큐에 소가 남아 있다면 가장 첫 번째 소가 최적의 소임으로 제거한다.
*/

public class Quiz14464 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int C = Integer.parseInt(st.nextToken());
        int N = Integer.parseInt(st.nextToken());
        Integer[] chickens = new Integer[C];
        Cow[] cows = new Cow[N];

        for(int i=0; i<C; i++) {
            chickens[i] = Integer.valueOf(br.readLine());
        }
        Arrays.sort(chickens);

        for(int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            cows[i] = new Cow(start, end);
        }
        Arrays.sort(cows, Comparator.comparingInt(Cow::getStart));


        int cowHelped = crossTheRoad(chickens, cows);
        System.out.println(cowHelped);
    }

    private static int crossTheRoad(Integer[] chickens, Cow[] cows) {
        int cowSize = cows.length;
        int cowHelped = 0;
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        int cowIdx = 0;

        for (int chicken : chickens) {
            while (cowIdx < cowSize && cows[cowIdx].getStart() <= chicken) {
                pq.offer(cows[cowIdx++].getEnd());
            }

            while (!pq.isEmpty() && pq.peek() < chicken) {
                pq.poll();
            }

            if (!pq.isEmpty()) {
                cowHelped++;
                pq.poll();
            }
        }
        return cowHelped;
    }

    static class Cow {
        private int start;
        private int end;

        public Cow(int start, int end) {
            this.start = start;
            this.end = end;
        }

        public int getStart() {
            return this.start;
        }

        public int getEnd() {
            return this.end;
        }
    }
}
