package programmers.pcce;

public class NeighboringSpace {
	public static void main(String[] args) {
		String[][] board = {{"blue", "red", "orange", "red"}, {"red", "red", "blue", "orange"}, {"blue", "orange", "red", "red"}, {"orange", "orange", "red", "blue"}};
		long result = solution(board, 1, 1); // 2
		System.out.println(result);
	}
	public static int solution(String[][] board, int h, int w) {
		int n = board.length;
		int count = 0;
		int[] dh = {-1, 1, 0, 0};
		int[] dw = {0, 0, -1, 1};
		for(int d=0; d<4; d++) {
			int h_check = h + dh[d];
			int w_check = w + dw[d];
			if(isIn(h_check, w_check, n) && board[h][w].equals(board[h_check][w_check])) count++;
		}
		return count;
	}
	private static boolean isIn(int h, int w, int n) {
		return h >= 0 && w >= 0 && h < n && w < n;
	}
}
