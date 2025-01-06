package baekjoon.Quiz2140;

import java.util.*;
import java.io.*;

/*
1. 닫혀있는 칸에 Tile을 채운다.
1-1. Tile은 지뢰 여부, 확정 여부 필드를 가진다.
2. 좌측 하단 테두리부터 주변 Tile을 확정한다.
2-1. 위, 오른쪽 방향으로 이동하면서 주변 Tile을 확정한다.
2-2. 주변 Tile은 다음 세 가지 경우 중 하나다.
2-2-1. Tile 개수 : 확정 : 미확정
2-2-2. #1. 1    :   0  :   1
2-2-3. #2. 2    :   1  :   1
2-2-4. #3. 3    :   2  :   1
2-3. 확정된 Tile로 현재 지뢰 개수를 구한다.
2-4. 현재 지뢰 개수가 테두리 개수보다 작다면 미확정 타일의 지뢰 여부와 확정 여부를 true로 변경한다.
2-5. 테두리 개수와 지뢰 개수가 같다면 확정 여부만 true로 변경한다.
3. 우측 상단 테두리도 2번 과정을 거친다.
4. 총 지뢰 개수를 구한다.
4-1. Tile을 순회하며 지뢰 여부가 true인 것을 구한다.
4-2. 확정되지 않은 타일은 모두 지뢰로 간주한다. (N-4)^2
*/

public class Quiz2140 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        Tile[][] board = new Tile[N][N];
        initTiles(board, br);
        checkAllTiles(board);
        int totalLandmines = findTotalLandmines(board);

        System.out.println(totalLandmines);
    }

    private static void initTiles(Tile[][] board, BufferedReader br) throws IOException {
        int length = board.length;

        for(int row = 0; row< length; row++) {
            String rowInput = br.readLine();
            for(int col = 0; col< length; col++) {
                if(rowInput.charAt(col) != '#') {
                    board[row][col] = Tile.create(rowInput.charAt(col) - '0', true);
                    continue;
                }

                board[row][col] = Tile.create(-1, false);
            }
        }
    }

    private static void checkAllTiles(Tile[][] board) {
        int length = board.length;

        for(int row = 0; row< length; row++) {
            int col = 0;
            checkLandmine(board, row, col);
        }

        for(int col = 0; col< length; col++) {
            int row = 0;
            checkLandmine(board, row, col);
        }

        for(int row = length -1; row>=0; row--) {
            int col = length -1;
            checkLandmine(board, row, col);
        }

        for(int col = length -1; col>=0; col--) {
            int row = length -1;
            checkLandmine(board, row, col);
        }
    }

    private static void checkLandmine(Tile[][] board, int row, int col) {
        List<Tile> nearbyTiles = findNearbyTile(board, row, col);
        int nearbyLandmines = countLandmineFrom(nearbyTiles);

        if(board[row][col].getNearbyLandmines() > nearbyLandmines) {
            for(Tile tile : nearbyTiles) {
                if(!tile.isConfirm()) {
                    tile.landmine();
                }
            }
        }

        for(Tile tile : nearbyTiles) {
            if(!tile.isConfirm()) {
                tile.confirm();
            }
        }
    }

    private static List<Tile> findNearbyTile(Tile[][] board, int row, int col) {
        int length = board.length;
        int[] dy = {-1, 1, 0, 0, -1, -1, 1, 1};
        int[] dx = {0, 0, -1, 1, -1, 1, -1, 1};
        List<Tile> list = new ArrayList<>();

        for(int d=0; d<8; d++) {
            int mr = row + dy[d];
            int mc = col + dx[d];

            if(mr < 0 || mc < 0 || mr >= length || mc >= length) continue;
            if(board[mr][mc].getNearbyLandmines() >= 0) continue;
            list.add(board[mr][mc]);
        }

        return list;
    }

    private static int countLandmineFrom(List<Tile> tiles) {
        int landmines = 0;

        for(Tile tile : tiles) {
            if(tile.isLandmine()) {
                landmines++;
            }
        }

        return landmines;
    }

    private static int findTotalLandmines(Tile[][] board) {
        int length = board.length;
        int totalLandmines = 0;

        for(int row = 0; row< length; row++) {
            for(int col = 0; col< length; col++) {
                Tile tile = board[row][col];
                if(tile.isLandmine() || !tile.isConfirm()) {
                    totalLandmines++;
                }
            }
        }
        return totalLandmines;
    }

    static class Tile {
        private int nearbyLandmines;
        private boolean isLandmine;
        private boolean isConfirm;

        private Tile(int nearbyLandmines, boolean isLandmine, boolean isConfirm) {
            this.nearbyLandmines = nearbyLandmines;
            this.isLandmine = isLandmine;
            this.isConfirm = isConfirm;
        }

        public static Tile create(int nearbyLandmines, boolean isConfirm) {
            return new Tile(nearbyLandmines, false, isConfirm);
        }

        public int getNearbyLandmines() {
            return this.nearbyLandmines;
        }

        public boolean isLandmine() {
            return this.isLandmine;
        }

        public boolean isConfirm() {
            return this.isConfirm;
        }

        public void landmine() {
            this.isLandmine = true;
        }

        public void confirm() {
            this.isConfirm = true;
        }
    }
}
