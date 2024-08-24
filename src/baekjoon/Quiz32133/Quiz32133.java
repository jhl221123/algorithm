package baekjoon.Quiz32133;

import java.util.*;
import java.io.*;

// 백트래킹
public class Quiz32133 {
	static final int ROCK = 0;
	static final int SCISSORS = 1;
	static final int PAPER = 2;
	static int N, M, K;
	static char[][] map;
	static int playRound = Integer.MAX_VALUE;
	static StringBuilder matchHistory;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		map = new char[N][M];
		for(int i=0; i<N; i++) {
			String str = br.readLine();
			for(int j=0; j<M; j++) {
				map[i][j] = str.charAt(j);
			}
		}

		dfs(0, new boolean[N], new StringBuilder());
		if(playRound == Integer.MAX_VALUE) playRound = -1;
		System.out.println(playRound);
		if(playRound != -1) System.out.println(matchHistory);
	}

	private static void dfs(int round, boolean[] losers, StringBuilder sb) {
		if(playRound > 0 && round > playRound) return;
		if(round == M) return;
		for(int type=0; type<3; type++) {
			boolean[] check = copyOf(losers);
			int winner = countWinner(round, type, check);
			if(winner == 0) continue;
			if(winner <= K) {
				if(playRound > round + 1) {
					playRound = round + 1;
					matchHistory = sb.append(intToType(type));
				}
				return;
			}
			dfs(round+1, check, new StringBuilder().append(sb).append(intToType(type)));
		}
	}

	private static int countWinner(int round, int type, boolean[] losers) {
		int winner = 0;
		for(int people=0; people<N; people++) {
			if(losers[people]) continue;
			if(isWin(type, map[people][round])) winner++;
			else losers[people] = true;
		}
		return winner;
	}

	private static boolean isWin(int me, char other) {
		if(me == ROCK && other == 'P') return true;
		if(me == SCISSORS && other == 'R') return true;
		return me == PAPER && other == 'S';
	}

	private static char intToType(int type) {
		return switch(type) {
			case ROCK -> 'R';
			case SCISSORS -> 'S';
			case PAPER -> 'P';
			default -> 'E';
		};
	}

	private static boolean[] copyOf(boolean[] arr) {
		boolean[] copyArr = new boolean[arr.length];
		for(int i=0; i<arr.length; i++) {
			copyArr[i] = arr[i];
		}
		return copyArr;
	}
}
