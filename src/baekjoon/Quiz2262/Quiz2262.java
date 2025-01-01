package baekjoon.Quiz2262;

import java.util.*;
import java.io.*;

/*
1. 가장 큰 선수부터 양 옆과 비교해 차이가 최소인 선수와 매치
2. 가장 큰 선수가 양 끝에 위치하는 케이스 처리 
 */

public class Quiz2262 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());

        LinkedList<Integer> list = new LinkedList<>();
        for(int i=0; i<N; i++) {
            list.add(Integer.parseInt(st.nextToken()));
        }

        int score = 0;
        while(list.size() > 1) {
            int idx = list.indexOf(N);

            if(idx == 0) {
                score += list.get(idx) - list.get(1);
            } else if(idx == list.size() - 1) {
                score += list.get(idx) - list.get(list.size()-2);
            } else {
                score += Math.min(Math.abs(list.get(idx) - list.get(idx-1)), Math.abs(list.get(idx) - list.get(idx+1)));
            }

            list.remove(idx);
            N--;
        }

        System.out.println(score);
    }
}
