package baekjoon.Quiz3671;

import java.io.*;
import java.util.*;

/*
Gold5: 산업 스파이의 편지 / [math]
1. 각 테스트케이스에서 나올 수 있는 모든 수를 구하고, 소수의 개수를 확인한다.
*/
public class Quiz3671 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        StringBuilder ans = new StringBuilder();

        while(T-- > 0) {
            String numbers = br.readLine();
            HashSet<Integer> targets = new HashSet<>();
            int count = 0;
            for(int targetLength=1; targetLength<=numbers.length(); targetLength++) {
                char[] tgt = new char[targetLength];
                boolean[] visited = new boolean[numbers.length()];
                perm(numbers, 0, tgt, visited, targets);
            }
            for(int target : targets) {
                if(isPrime(target)) count++;
            }
            ans.append(count).append("\n");
        }

        System.out.print(ans);
    }

    private static void perm(String numbers, int tgtIdx, char[] tgt, boolean[] visited, HashSet<Integer> targets) {
        if(tgtIdx == tgt.length) {
            StringBuilder target = new StringBuilder();
            for(char c : tgt) {
                target.append(c);
            }
            targets.add(Integer.parseInt(target.toString()));
            return;
        }

        for(int i=0; i<numbers.length(); i++) {
            if(visited[i]) continue;
            visited[i] = true;
            tgt[tgtIdx] = numbers.charAt(i);
            perm(numbers, tgtIdx+1, tgt, visited, targets);
            visited[i] = false;
        }
    }

    private static boolean isPrime(int number) {
        if(number <= 1) return false;
        for(int i=2; i*i<=number; i++) {
            if(number % i == 0) return false;
        }
        return true;
    }
}
