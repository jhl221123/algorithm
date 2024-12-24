package baekjoon.Quiz16234;

import java.util.*;
import java.io.*;

/*
1. 모든 좌표를 대상으로 4방향의 차이가 L~R인 칸을 탐색한다.
2. 플루드필을 이용해서 단위를 구분한다.
2-1. 각 단위는 식별자를 부여한다.
2-2. 각 단위의 나라수, 총 인구수를 구한다.
3. 2과정에서 만든 맵을 활용해서 인구수를 조정한다.
4. 연합이 일어나지 않았다면 종료한다.
5. 연합이 일어났다면 일수를 증가시키고 이를 반복한다.
*/

public class Quiz16234 {
    private static int N, L, R;
    private static int[][] map;
    private static boolean[][] visited;
    private static int[][] units;
    private static Map<Integer, AlliedCountry> unitMap;
    private static int[] dy = {-1, 1, 0, 0};
    private static int[] dx = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        init(br);
        int days = 0;

        while(true) {
            boolean existAlliedCountry = checkAlliedCountries();
            if(!existAlliedCountry) break;
            divideAlliedCountries();
            changePeopleCount();
            days++;
        }

        System.out.println(days);
    }

    private static void init(BufferedReader br) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());
        map = new int[N][N];
        for(int row=0; row<N; row++) {
            st = new StringTokenizer(br.readLine());
            for(int col=0; col<N; col++) {
                map[row][col] = Integer.parseInt(st.nextToken());
            }
        }
    }

    private static boolean checkAlliedCountries() {
        visited = new boolean[N][N];
        boolean existAlliedCountry = false;
        for(int row=0; row<N; row++) {
            for(int col=0; col<N; col++) {
                for(int d=0; d<4; d++) {
                    int mr = dy[d] + row;
                    int mc = dx[d] + col;
                    if(mr<0 || mc<0 || mr>=N || mc>=N) continue;

                    int diffPeople = Math.abs(map[row][col] - map[mr][mc]);
                    if(diffPeople < L || diffPeople > R) continue;
                    visited[row][col] = true;
                    visited[mr][mc] = true;
                    existAlliedCountry = true;
                }
            }
        }
        return existAlliedCountry;
    }

    private static void divideAlliedCountries() {
        units = new int[N][N];
        unitMap = new HashMap<>();
        ArrayDeque<int[]> ad = new ArrayDeque<>();
        int num = 1;
        for(int row=0; row<N; row++) {
            for(int col=0; col<N; col++) {
                if(visited[row][col] && units[row][col] == 0) {
                    int count = 0;
                    int sum = 0;
                    ad.addLast(new int[] {row, col});
                    while(!ad.isEmpty()) {
                        int[] cur = ad.removeFirst();
                        int cr = cur[0];
                        int cc = cur[1];
                        if(units[cr][cc] > 0) continue;
                        units[cr][cc] = num;
                        count++;
                        sum += map[cr][cc];
                        for(int d=0; d<4; d++) {
                            int mr = dy[d] + cr;
                            int mc = dx[d] + cc;
                            if(mr<0 || mc<0 || mr>=N || mc>=N) continue;
                            if(units[mr][mc] > 0 || !visited[mr][mc]) continue;

                            int diffPeople = Math.abs(map[cr][cc] - map[mr][mc]);
                            if(diffPeople < L || diffPeople > R) continue;
                            ad.addLast(new int[] {mr, mc});
                        }
                    }
                    unitMap.put(num, new AlliedCountry(count, sum));
                    num++;
                }
            }
        }
    }

    private static void changePeopleCount() {
        for(int row=0; row<N; row++) {
            for(int col=0; col<N; col++) {
                if(units[row][col] > 0) {
                    AlliedCountry alliedCountry = unitMap.get(units[row][col]);
                    map[row][col] = alliedCountry.getPeopleCountByCountry();
                }
            }
        }
    }

    static class AlliedCountry {
        private int countryCount;
        private int peopleSum;
        private int peopleCountByCountry;

        public AlliedCountry(int countryCount, int peopleSum) {
            this.countryCount = countryCount;
            this.peopleSum = peopleSum;
            this.peopleCountByCountry = peopleSum / countryCount;
        }

        public int getPeopleCountByCountry() {
            return this.peopleCountByCountry;
        }
    }
}
