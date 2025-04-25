package baekjoon.Quiz7682;

import java.io.*;

/*
Gold5: 틱택토 / [implementation, many branches]
1. X == O + 1
1-1. X만 3칸을 이었다면(valid)
1-2. 빈칸(.)이 없다면(valid)
1-3. 나머지 invalid
2. X == O
2-1. O만 3칸을 이었다면(valid)
2-2 나머지 invalid
3. 나머지 invalid
*/
public class Quiz7682 {

    private static final String VALID = "valid";
    private static final String INVALID = "invalid";

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        while(true) {
            String game = br.readLine();
            if("end".equals(game)) {
                break;
            }

            sb.append(isValid(game)).append("\n");
        }

        System.out.print(sb);
    }

    private static String isValid(String game) {
        char[][] map = new char[3][3];
        int idx = 0;
        int xCount = 0;
        int oCount = 0;

        for(int row=0; row<3; row++) {
            for(int col=0; col<3; col++) {
                map[row][col] = game.charAt(idx++);
                if(map[row][col] == 'X') {
                    xCount++;
                } else if(map[row][col] == 'O') {
                    oCount++;
                }
            }
        }

        if(xCount == oCount + 1) {
            if(isWin('O', map)) {
                return INVALID;
            } else if(isWin('X', map) || xCount + oCount == 9) {
                return VALID;
            } else {
                return INVALID;
            }
        } else if(xCount == oCount) {
            if(isWin('X', map)) {
                return INVALID;
            } else if(isWin('O', map)) {
                return VALID;
            }
        }

        return INVALID;
    }

    private static boolean isWin(char player, char[][] map) {
        boolean isWin;

        for(int row=0; row<3; row++) {
            isWin = true;

            for(int col=0; col<3; col++) {
                if(map[row][col] != player) {
                    isWin = false;
                    break;
                }
            }

            if(isWin) return true;
        }

        for(int col=0; col<3; col++) {
            isWin = true;
            for(int row=0; row<3; row++) {
                if(map[row][col] != player) {
                    isWin = false;
                    break;
                }
            }

            if(isWin) return true;
        }

        isWin = true;
        for(int row=0, col=0; row<3; row++, col++) {
            if(map[row][col] != player) {
                isWin = false;
                break;
            }
        }
        if(isWin) return true;

        isWin = true;
        for(int row=0, col=2; row<3; row++, col--) {
            if(map[row][col] != player) {
                isWin = false;
                break;
            }
        }
        return isWin;
    }
}
