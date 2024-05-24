package baekjoon.Quiz14889;

import java.util.*;
import java.io.*;

public class Quiz14889 {
	static int N;
	static int[][] map;
	static boolean[] team;
	static int min = Integer.MAX_VALUE;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		map = new int[N][N];
		for(int i=0; i<N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int j=0; j<N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		team = new boolean[N];
		comb(0, 0);
		System.out.println(min);
	}
	private static void comb(int s, int v) {
		if(v == (N/2)) {
			// getTeam
			List<Integer> teamA = getTeam(true);
			List<Integer> teamB = getTeam(false);
			// sum
			int score = getScore(teamA, teamB);
			min = Math.min(min, score);
			return;
		}
		if(s >= N) return;
		team[s] = true;
		comb(s+1, v+1);
		team[s] = false;
		comb(s+1, v);
	}
	private static List<Integer> getTeam(boolean target) {
		List<Integer> list = new ArrayList<>();
		for(int i=0; i<N; i++) {
			if(team[i] == target) list.add(i);
		}
		return list;
	}
	private static int getScore(List<Integer> teamA, List<Integer> teamB) {
		int sumA = getSum(teamA);
		int sumB = getSum(teamB);
		return Math.abs(sumA - sumB);
	}
	private static int getSum(List<Integer> team) {
		int teamSize = team.size();
		int sum = 0;
		for(int i=0; i<teamSize; i++) {
			for(int j=i+1; j<teamSize; j++) {
				sum += map[team.get(i)][team.get(j)];
				sum += map[team.get(j)][team.get(i)];
			}
		}
		return sum;
	}
}
