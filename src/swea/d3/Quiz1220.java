package swea.d3;

import java.util.*;

public class Quiz1220 {
	public static void main(String[] args) throws Exception {
		Scanner sc = new Scanner(System.in);
		int T = 10;

		for(int test_case = 1; test_case <= T; test_case++) {
			int N = sc.nextInt();
			int[][] board = new int[N][N];
			int count = 0;
			for(int i=0; i<N; i++) {
				for(int j=0; j<N; j++) {
					board[i][j] = sc.nextInt();
				}
			}
			// 1. 각 열의 0행 부터 탐색
			// 2. S(2)가 먼저 나오면 무시
			// 3-1. N(1)이 나오면 스택에 저장 -> 마지막 행까지 S(2)가 나오지 않으면 스택에서 제거
			// 3-2. N(1)이 나온 후 S(2)가 나오면 S(2)도 스택에 저장 후 카운트 + 1
			for(int i=0; i<N; i++) {
				ArrayDeque<Integer> ad = new ArrayDeque<>();
				for(int j=0; j<N; j++) {
					if(board[j][i] == 2) { // S(2) 가 나온 경우
						// N(1)이 먼저 나온 경우
						if(!ad.isEmpty() && ad.getFirst()==1) {
							ad.addFirst(2);
							count++;
						} // S(2)가 먼저 나온 경우는 무시
					} else if(board[j][i] ==1) ad.addFirst(1); // N(1)이 나온 경우
				}
			}
			System.out.println("#" + test_case + " " + count);
		}
	}
}
