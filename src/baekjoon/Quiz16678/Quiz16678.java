package baekjoon.Quiz16678;

import java.util.*;
import java.io.*;

/*
1. 국회의원들의 점수를 오름차순으로 정렬한다.
2. 국회의원 점수를 순회하며, 1부터 시작하는 등차가 1인 등차수열을 만든다.
2-1. 현재 국회의원 점수가 현재 등차수열 원소보다 크다면, 차이만큼 해커 공격횟수를 증가시키고 등차수열 원소를 1 증가 시킨다.
2-2. 현재 국회의원 점수가 현재 등차수열 원소와 같다면, 등차수열 원소를 1 증가 시킨다.
 */

public class Quiz16678 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        Integer[] honors = new Integer[N];

        for(int i=0; i<N; i++) {
            honors[i] = Integer.parseInt(br.readLine());
        }
        Arrays.sort(honors);

        int maxHonor = 1;
        long hackerAttack = 0;
        for(int i=0; i<N; i++) {
            int honor = honors[i];

            if(honor > maxHonor) {
                hackerAttack += (honor - maxHonor);
                maxHonor++;
                continue;
            }

            if(honor == maxHonor) {
                maxHonor++;
            }
        }

        System.out.println(hackerAttack);
    }
}
