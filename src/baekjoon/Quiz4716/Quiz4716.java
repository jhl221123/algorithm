package baekjoon.Quiz4716;

import java.util.*;
import java.io.*;

/*
1. 각 방의 차이가 큰 팀이 먼저 오도록 정렬한다.
2. 팀을 순회하면서 가까운 방부터 풍선을 가져온다.
2-1. '가까운 방의 남아있는 풍선의 개수'와 '현재 팀에 필요한 풍선의 개수' 중 작은 값이 가져올 수 있는 풍선의 개수가 된다.
2-2. 가까운 방에서 풍선을 가져온 후, 항상 남은 풍선의 개수만큼 거리가 먼 방에서도 가져온다.
2-2-1. 현재 팀에 필요한 모든 풍선을 가까운 방에서 가져오지 못한다면, 먼 방에서 나머지 풍선을 가져오게 된다.
2-2-2. 현재 팀에 필요한 풍선 모두 가까운 방에서 가져오지 못한다면, 먼 방에서 모두 가져오게 된다.
 */

public class Quiz4716 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PriorityQueue<Team> pq = new PriorityQueue<>((o1, o2) -> {
            int diff1 = Math.abs(o1.distA - o1.distB);
            int diff2 = Math.abs(o2.distA - o2.distB);
            return diff2 - diff1;
        });
        StringBuilder sb = new StringBuilder();

        while (true) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());

            if(N == 0 && A == 0 && B == 0) {
                break;
            }

            for(int i=0; i<N; i++) {
                st = new StringTokenizer(br.readLine());
                int K = Integer.parseInt(st.nextToken());
                int distA = Integer.parseInt(st.nextToken());
                int distB = Integer.parseInt(st.nextToken());
                pq.offer(new Team(K, distA, distB));
            }

            int totalDistance = 0;
            while(!pq.isEmpty()) {
                Team team = pq.poll();
                if(team.distA <= team.distB) {
                    int takeFromA = Math.min(A, team.K);
                    totalDistance += takeFromA * team.distA;
                    A -= takeFromA;

                    int takeFromB = team.K - takeFromA;
                    totalDistance += takeFromB * team.distB;
                    B -= takeFromB;
                } else {
                    int takeFromB = Math.min(B, team.K);
                    totalDistance += takeFromB * team.distB;
                    B -= takeFromB;

                    int takeFromA = team.K - takeFromB;
                    totalDistance += takeFromA * team.distA;
                    A -= takeFromA;
                }
            }

            sb.append(totalDistance).append("\n");
        }

        System.out.print(sb);
    }

    static class Team {
        int K, distA, distB;

        public Team(int K, int distA, int distB) {
            this.K = K;
            this.distA = distA;
            this.distB = distB;
        }
    }
}
