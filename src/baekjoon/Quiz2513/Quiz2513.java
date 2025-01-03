package baekjoon.Quiz2513;

import java.util.*;
import java.io.*;

/*
1. 정원을 초과하기 전까지 학교를 기준으로 거리가 먼 곳부터 학생을 태운다
1-1. 버스 남은 자리수가 주택 학생수보다 많다면, 버스 남은 자리수를 줄이고 해당 주택을 제거한다.
1-2. 버스 남은 자리수보다 주택 학생수가 많다면, 주택의 학생수를 버스 자리수만큼 감소시킨다.
 */

public class Quiz2513 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        int S = Integer.parseInt(st.nextToken());
        LinkedList<House> left = new LinkedList<>();
        LinkedList<House> right = new LinkedList<>();

        for(int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            int pos = Integer.parseInt(st.nextToken());
            int students = Integer.parseInt(st.nextToken());
            if(S > pos) {
                left.add(new House(pos, students, S - pos));
            } else {
                right.add(new House(pos, students, pos - S));
            }
        }

        left.sort((o1, o2) -> o2.distFromSchool - o1.distFromSchool);
        right.sort((o1, o2) -> o2.distFromSchool - o1.distFromSchool);

        int totalDist = 0;
        while(!left.isEmpty()) {
            totalDist += pickUpStudents(left, K);
        }

        while(!right.isEmpty()) {
            totalDist += pickUpStudents(right, K);
        }

        System.out.println(totalDist);
    }

    private static int pickUpStudents(LinkedList<House> houses, int K) {
        int students = 0;
        int additionalDist =  houses.get(0).distFromSchool * 2;

        for(int i = 0; i< houses.size(); i++) {
            House house = houses.get(i);
            int next = students + house.students;

            if(next <= K) {
                students += house.students;
                houses.remove(i);
                i--;
            }

            if(next > K) {
                house.pickUp(K - students);
                students += K - students;
            }

            if(students >= K) break;
        }
        return additionalDist;
    }

    static class House {
        int pos, students, distFromSchool;

        public House(int pos, int students, int distFromSchool) {
            this.pos = pos;
            this.students = students;
            this.distFromSchool = distFromSchool;
        }

        public void pickUp(int someones) {
            students -= someones;
        }
    }
}
