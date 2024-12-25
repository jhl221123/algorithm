package baekjoon.Quiz15685;

import java.util.*;
import java.io.*;

/*
1. 입력받은 드래곤 커브를 하나씩 그린다.
1-1. 초기 드래곤 커브에서 역방향으로 좌표를 탐색하며 회전시킨다.
1-1. 시작 좌표를 회전시킨 좌표가 해당 드래곤 커브의 마지막 좌표가 된다.
2. 모든 드래곤 커브가 반영된 맵에서 4개의 꼭지점이 모두 드래곤 커브에 속하는 정사각형의 개수를 구한다.
 */

public class Quiz15685 {
    private static int[] dy = {0, -1, 0, 1};
    private static int[] dx = {1, 0, -1, 0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        boolean[][] map = new boolean[101][101];

        for(int i=0; i<N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int c = Integer.parseInt(st.nextToken());
            int r = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            int g = Integer.parseInt(st.nextToken());
            createDragonCurve(map, r, c, d, g);
        }

        int count = countAllEdgeCheckSquare(map);
        System.out.println(count);
    }

    private static void createDragonCurve(boolean[][] map, int sr, int sc, int d, int gen) {
        LinkedList<int[]> spots = new LinkedList<>();
        spots.addLast(new int[]{sr, sc});
        spots.addLast(new int[]{sr + dy[d], sc + dx[d]});
        int g = 0;

        while(g < gen) {
            int[] base = spots.get(spots.size()-1);
            for(int i=spots.size()-2; i>=0; i--) {
                int[] spot = spots.get(i);
                int[] rotate = rotate(spot, base);
                spots.addLast(rotate);
            }
            g++;
        }

        for(int i=0; i<spots.size(); i++) {
            int[] spot = spots.get(i);
            map[spot[0]][spot[1]] = true;
        }
    }

    private static int[] rotate(int[] spot, int[] base) {
        int rr = base[0] + (spot[1] - base[1]);
        int rc = base[1] - (spot[0] - base[0]);
        return new int[]{rr, rc};
    }

    private static int countAllEdgeCheckSquare(boolean[][] map) {
        int count = 0;

        for(int row=0; row<map.length-1; row++) {
            for(int col=0; col<map[0].length-1; col++) {
                if(map[row][col] && map[row+1][col] && map[row][col+1] && map[row+1][col+1]) {
                    count++;
                }
            }
        }

        return count;
    }
}
