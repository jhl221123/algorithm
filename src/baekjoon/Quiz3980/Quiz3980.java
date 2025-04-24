package baekjoon.Quiz3980;

import java.io.*;

/*
Gold5: 선발 명단 / [brute-force, dfs]
1. 깊이 우선 탐색을 통해 모든 플레이어가 담당 가능한 포지션을 맡아보며 최돼값을 갱신한다.
*/
public class Quiz3980 {

    private static int max;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();

        while(T-- > 0) {
            int[][] scores = new int[11][11];
            for(int i=0; i<11; i++) {
                String[] playerScore = br.readLine().split(" ");

                for(int j=0; j<11; j++) {
                    scores[i][j] = Integer.parseInt(playerScore[j]);
                }
            }

            boolean[] visited = new boolean[11];
            max = 0;
            dfs(0, scores, visited, 0);
            sb.append(max).append("\n");
        }

        System.out.print(sb);
    }

    private static void dfs(int idx, int[][] scores, boolean[] visited, int sum) {
        if(idx == 11) {
            max = Math.max(max, sum);
            return;
        }

        for(int i=0; i<11; i++) {
            if(visited[i] || scores[idx][i] == 0) {
                continue;
            }
            visited[i] = true;
            dfs(idx + 1, scores, visited, sum + scores[idx][i]);
            visited[i] = false;
        }
    }
}
