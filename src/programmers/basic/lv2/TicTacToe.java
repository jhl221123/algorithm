package programmers.basic.lv2;

public class TicTacToe {
	static int cntO, cntX;
	static char[][] map = new char[3][3];
	public static void main(String[] args) {
		// String[] arr = {"O.X", ".O.", "..X"}; // 1
		String[] arr = {"OOO", "...", "XXX"}; // 0
		int result = solution(arr);
		System.out.println(result);
	}
	public static int solution(String[] board) {
		// cond1. O의 개수는 X보다 항상 1만큼 크거나 같아야 한다.
		// cond2. O와 X 둘 중 하나만 승리한다.
		// cond3. 승리시 각 개수가 충족되어야 한다.
		init(board);
		boolean oWin = isWinner('O');
		boolean xWin = isWinner('X');
		if(oWin && xWin) return 0;
		if(oWin && cntO - cntX != 1) return 0;
		if(xWin && cntO - cntX != 0) return 0;
		if(cntO - cntX == 1 || cntO - cntX == 0) return 1;
		return 0;
	}
	private static void init(String[] board) {
		for(int r=0; r<3; r++) {
			String line = board[r];
			for(int c=0; c<3; c++) {
				map[r][c] = line.charAt(c);
				if(map[r][c] == 'O') cntO++;
				else if(map[r][c] == 'X') cntX++;
			}
		}
	}
	private static boolean isWinner(char player) {
		for (int i = 0; i < 3; i++) {
			if (map[i][0] == player && map[i][1] == player && map[i][2] == player) {
				return true;
			}
		}
		for (int i = 0; i < 3; i++) {
			if (map[0][i] == player && map[1][i] == player && map[2][i] == player) {
				return true;
			}
		}
		if (map[0][0] == player && map[1][1] == player && map[2][2] == player) {
			return true;
		}
		if (map[0][2] == player && map[1][1] == player && map[2][0] == player) {
			return true;
		}
		return false;
	}
}
