package baekjoon.Quiz2285;

import java.util.*;
import java.io.*;

/*
1. 총 사람수의 중간 값을 구한다.
2. 마을을 순차적으로 순회하며 중간 값을 넘는 지점을 출력한다.
 */

public class Quiz2285 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        Village[] villages = new Village[N];

        int totalPeople = 0;
        for(int i=0; i<N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int pos = Integer.parseInt(st.nextToken());
            int people = Integer.parseInt(st.nextToken());
            villages[i] = new Village(pos, people);
            totalPeople += people;
        }

        Arrays.sort(villages, Comparator.comparingInt(Village::getPos));

        long mid = (totalPeople + 1) / 2;
        long pivot = 0;
        for(int i=0; i<N; i++) {
            pivot += villages[i].getPeople();
            if(pivot >= mid) {
                System.out.println(villages[i].getPos());
                return;
            }
        }
    }

    static class Village {
        private int pos;
        private int people;

        public Village(int pos, int people) {
            this.pos = pos;
            this.people = people;
        }

        public int getPos() {
            return pos;
        }

        public int getPeople() {
            return this.people;
        }
    }
}
