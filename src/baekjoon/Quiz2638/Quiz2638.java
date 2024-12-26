package baekjoon.Quiz2638;

import java.util.*;
import java.io.*;

/*
1. (0, 0) 부터 탐색해서 공기 좌표를 관리한다.
2. 공기와 2면 이상 맞닿아 있는 치즈 좌표를 구한다.
3. 사라지는 치즈 좌표와 공기가 아닌 빈 공간이 맞닿아 있다면 해당 구역을 모두 공기로 바꾼다.
4. 치즈가 남아있다면 이 2번부터 반복한다.
*/

public class Quiz2638 {
    private static int N, M;
    private static int[][] map;
    private static boolean[][] air;
    private static int[] dy = {-1, 1, 0, 0};
    private static int[] dx = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        air = new boolean[N][M];

        for(int row=0; row<N; row++) {
            st = new StringTokenizer(br.readLine());
            for(int col=0; col<M; col++) {
                map[row][col] = Integer.parseInt(st.nextToken());
            }
        }

        initAir();
        int hour = 0;
        while (!isMeltAllCheese()) {
            boolean[][] meltingCheese = markMeltingCheese();
            meltCheese(meltingCheese);
            hour++;
        }

        System.out.println(hour);
    }

    private static boolean isMeltAllCheese() {
        for(int row=0; row<N; row++) {
            for(int col=0; col<M; col++) {
                if(map[row][col] == 1) {
                    return false;
                }
            }
        }
        return true;
    }

    private static void meltCheese(boolean[][] meltingCheese) {
        for(int row=0; row<N; row++) {
            for(int col=0; col<M; col++) {
                if(meltingCheese[row][col]) {
                    air[row][col] = true;
                    map[row][col] = 0;
                    for(int d=0; d<4; d++) {
                        int mr = row + dy[d];
                        int mc = col + dx[d];
                        if(isNotIn(mr, mc)) continue;
                        if(!air[mr][mc] && map[mr][mc] == 0) {
                            markAir(mr, mc);
                        }
                    }
                }
            }
        }
    }

    private static void markAir(int row, int col) {
        ArrayDeque<int[]> ad = new ArrayDeque<>();
        ad.addLast(new int[]{row, col});
        boolean[][] visited = new boolean[N][M];

        while(!ad.isEmpty()) {
            int[] point = ad.removeLast();
            int r = point[0];
            int c = point[1];
            if(visited[r][c]) continue;
            visited[r][c] = true;
            air[r][c] = true;

            for(int d=0; d<4; d++) {
                int mr = r + dy[d];
                int mc = c + dx[d];
                if(isNotIn(mr, mc) || visited[mr][mc]) continue;
                if(map[mr][mc] != 0 || air[mr][mc]) continue;
                ad.addLast(new int[]{mr, mc});
            }
        }
    }

    private static boolean[][] markMeltingCheese() {
        boolean[][] meltingCheese = new boolean[N][M];

        for(int row=0; row<N; row++) {
            for(int col=0; col<M; col++) {
                if(map[row][col] == 1) {
                    int nearbyAir = 0;
                    for(int d=0; d<4; d++) {
                        int mr = row + dy[d];
                        int mc = col + dx[d];
                        if(isNotIn(mr, mc)) continue;
                        if(air[mr][mc]) nearbyAir++;
                    }
                    if(nearbyAir >= 2) meltingCheese[row][col] = true;
                }
            }
        }

        return meltingCheese;
    }

    private static void initAir() {
        ArrayDeque<int[]> ad = new ArrayDeque<>();
        ad.addLast(new int[]{0, 0});
        boolean[][] visited = new boolean[N][M];

        while(!ad.isEmpty()) {
            int[] point = ad.removeFirst();
            int r = point[0];
            int c = point[1];
            if(visited[r][c]) continue;
            visited[r][c] = true;
            air[r][c] = true;

            for(int d=0; d<4; d++) {
                int mr = r + dy[d];
                int mc = c + dx[d];
                if(isNotIn(mr, mc)) continue;
                if(visited[mr][mc] || map[mr][mc] == 1) continue;
                ad.addLast(new int[]{mr, mc});
            }
        }
    }

    private static boolean isNotIn(int row, int col) {
        return row < 0 || col < 0 || row >= N || col >= M;
    }
}
