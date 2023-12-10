package baekjoon.Quiz1018;

import java.util.Scanner;

// 4중 반복문으로 완전탐색 수행
public class Quiz1018 {
	static String[] board;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int M = sc.nextInt();
		board = new String[N];
		for(int i=0; i<N; i++) {
			board[i] = sc.next();
		}
		int result = 100;
		for(int i=0; i<N-7; i++) {
			for(int j=0; j<M-7; j++) {
				int temp = checkCnt(i,j);
				result = Math.min(temp, result);
			}
		}
		System.out.println(result);
	}
	private static int checkCnt(int baseN, int baseM) {
		int blackCnt = 0;
		int whiteCnt = 0;
		boolean isBlack = board[baseN].charAt(baseM) == 'B';
		for(int i=baseN; i<baseN+8; i++) {
			for(int j=baseM; j<baseM+8; j++) {
				//첫 칸이 검은색일 경우
				// 짝수줄
				if((i-baseN)%2==0 && (j-baseM)%2==0) {
					if(board[i].charAt(j) == 'W') blackCnt++;
				} else if((i-baseN)%2==0 && (j-baseM)%2==1) {
					if(board[i].charAt(j) == 'B') blackCnt++;
				}
				// 홀수줄
				if((i-baseN)%2==1 && (j-baseM)%2==0) {
					if(board[i].charAt(j) == 'B') blackCnt++;
				} else if((i-baseN)%2==1 && (j-baseM)%2==1) {
					if(board[i].charAt(j) == 'W') blackCnt++;
				}
				//첫 칸이 흰색일 경우
				// 짝수줄
				if((i-baseN)%2==0 && (j-baseM)%2==0) {
					if(board[i].charAt(j) == 'B') whiteCnt++;
				} else if((i-baseN)%2==0 && (j-baseM)%2==1) {
					if(board[i].charAt(j) == 'W') whiteCnt++;
				}
				// 홀수줄
				if((i-baseN)%2==1 && (j-baseM)%2==0) {
					if(board[i].charAt(j) == 'W') whiteCnt++;
				} else if((i-baseN)%2==1 && (j-baseM)%2==1) {
					if(board[i].charAt(j) == 'B') whiteCnt++;
				}
			}
		}
		return Math.min(blackCnt, whiteCnt);
	}
}
