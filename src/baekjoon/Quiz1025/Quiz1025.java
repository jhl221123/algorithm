package baekjoon.Quiz1025;

import java.util.*;
import java.io.*;

/*
Gold5: 제곱수 찾기 / [brute-force, dfs]
1. map의 모든 좌표를 순회한다.
1-1. 좌표의 수가 제곱수이면 최댓값을 갱신한다.
2. 현재 좌표를 제외한 다른 좌표를 모두 순회한다.
2-1. 두 좌표의 차이를 구하고 깊이 우선 탐색을 진행한다.
2-2. 이전 수 * 10 + 현재수를 계산한 후, 제곱수인지 확인하고 갱신한다.
2-3. map 경계를 벗어나면 탐색을 종료한다.
3. 갱신된 최댓값을 출력한다.
*/
public class Quiz1025 {

    private static int N, M;
    private static int[][] map;
    private static int answer = -1;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] nm = br.readLine().split(" ");
        N = Integer.parseInt(nm[0]);
        M = Integer.parseInt(nm[1]);
        map = new int[N][M];
        for(int row=0; row<N; row++) {
            String line = br.readLine();

            for(int col=0; col<M; col++) {
                map[row][col] = line.charAt(col) - '0';
            }
        }

        for(int row=0; row<N; row++) {
            for(int col=0; col<M; col++) {
                int num = map[row][col];

                if(isPerfectSquare(num)) {
                    answer = Math.max(answer, num);
                }

                for(int nr=0; nr<N; nr++) {
                    for(int nc=0; nc<M; nc++) {
                        if(row == nr && col == nc) continue;

                        Set<String> visited = new HashSet<>();
                        visited.add(row + " " + col);
                        dfs(nr, nc, nr - row, nc - col, num, visited);
                    }
                }
            }
        }

        System.out.println(answer);
    }

    private static void dfs(int r, int c, int mr, int mc, int prev, Set<String> visited) {
        if(!isIn(r, c)) {
            return;
        }

        int num = prev * 10 + map[r][c];

        if(isPerfectSquare(num)) {
            answer = Math.max(answer, num);
        }

        visited.add(r + " " + c);
        dfs(r + mr, c + mc, mr, mc, num, visited);
    }

    private static boolean isIn(int r, int c) {
        return r >= 0 && c >= 0 && r < N && c < M;
    }

    private static boolean isPerfectSquare(int num) {
        int sqrt = (int) Math.sqrt(num);
        return sqrt * sqrt == num;
    }
}
