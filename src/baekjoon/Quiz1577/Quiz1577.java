package baekjoon.Quiz1577;

import java.util.*;
import java.io.*;

/*
Gold5: 도로의 개수 / [dp, implementation]
1. 시작 지점을 큐에 넣는다.
2. 큐에서 좌표를 하나 꺼낸다.
2-1. 이미 출발한 이력이 있는 좌표는 건너뛴다.
2-2. 아직 출발한 이력이 없다면 방문 체크하고 3번 과정을 진행한다.
2-3. 현재 좌표가 도착 지점이라면 경우의 수를 출력한다.
3. 현재 좌표에서 우, 하 방향으로 이동이 가능한지 확인한다.
3-1. 이동이 가능하다면, 현재 좌표의 경우의 수를 다음 좌표의 경우의 수에 더하고 다음 좌표를 큐에 넣는다.
3-2. 이동이 불가능하다면, 다음 좌표를 큐에 넣는다.
*/public class Quiz1577 {

    private static int[] dy = {0, 1};
    private static int[] dx = {1, 0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] nm = br.readLine().split(" ");
        int N = Integer.parseInt(nm[0]);
        int M = Integer.parseInt(nm[1]);
        int K = Integer.parseInt(br.readLine());
        Set<String> brokenPos = new HashSet<>();
        for(int i=0; i<K; i++) {
            brokenPos.add(br.readLine());
        }

        long[][] dp = new long[N + 1][M + 1];
        dp[0][0] = 1;

        ArrayDeque<int[]> ad = new ArrayDeque<>();
        ad.addLast(new int[] {0, 0});
        boolean[][] visited = new boolean[N + 1][M + 1];

        while(!ad.isEmpty()) {
            int[] pos = ad.removeFirst();
            int r = pos[0];
            int c = pos[1];
            if(r == N && c == M) {
                System.out.println(dp[r][c]);
                break;
            }

            if(visited[r][c]) {
                continue;
            }
            visited[r][c] = true;

            for(int d=0; d<2; d++) {
                int mr = r + dy[d];
                int mc = c + dx[d];
                if(mr>N || mc>M) {
                    continue;
                }

                if(!isBrokenPos(r, c, mr, mc, brokenPos)) {
                    dp[mr][mc] += dp[r][c];
                }
                ad.addLast(new int[] {mr, mc});
            }
        }
    }

    private static boolean isBrokenPos(int r, int c, int mr, int mc, Set<String> brokenPos) {
        return brokenPos.contains(r + " " + c + " " + mr + " " + mc) || brokenPos.contains(mr + " " + mc + " " + r + " " + c);
    }
}
