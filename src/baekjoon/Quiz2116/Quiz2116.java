package baekjoon.Quiz2116;

import java.io.*;

/*
1. 첫 번째 주사위 위치를 바꾸면서 탑을 쌓는다.
2-1. 탑을 쌓으면서 옆면의 최댓값을 더한다.
2-2. 탑을 모두 쌓았다면 최댓값을 갱신한다.
*/
public class Quiz2116 {

    private static int N, answer;
    private static int[][] dices;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        dices = new int[N][6];
        for(int i=0; i<N; i++) {
            String[] numbers = br.readLine().split(" ");

            for(int j=0; j<6; j++) {
                dices[i][j] = Integer.parseInt(numbers[j]);
            }
        }

        for(int i=0; i<6; i++) {
            dfs(0, i, 0);
        }

        System.out.println(answer);
    }

    private static void dfs(int diceNum, int topIdx, int sum) {
        int max = 0;
        for(int i=0; i<6; i++) {
            int bottomIdx = getPair(topIdx);
            if(i == topIdx || i == bottomIdx) {
                continue;
            }

            max = Math.max(max, dices[diceNum][i]);
        }
        sum += max;

        if(diceNum + 1 >= N) {
            answer = Math.max(answer, sum);
            return;
        }

        int nextTopIdx = 0;
        for(int i=0; i<6; i++) {
            if(dices[diceNum + 1][i] == dices[diceNum][topIdx]) {
                nextTopIdx = getPair(i);
                break;
            }
        }

        dfs(diceNum + 1, nextTopIdx, sum);
    }

    private static int getPair(int face) {
        if(face == 0) return 5;
        if(face == 1) return 3;
        if(face == 2) return 4;
        if(face == 3) return 1;
        if(face == 4) return 2;
        if(face == 5) return 0;
        throw new RuntimeException("Invalid face number!");
    }
}
