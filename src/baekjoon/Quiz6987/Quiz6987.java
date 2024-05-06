package baekjoon.Quiz6987;

import java.util.*;
import java.io.*;

public class Quiz6987 {
	// 게임 가능한 모든 대진표 세팅
	// 각 게임별 승, 무, 패 갱신 -> 모든 게임이 종료되면 1 아니면 0

	static int[] win;
	static int[] loose;
	static int[] draw;
	static int[][] games;
	static int result;
	static StringBuilder sb = new StringBuilder();
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		// 대진표 입력
		games = new int[15][2];
		int idx = 0;
		for(int i=0; i<5; i++) {
			for(int j=i+1; j<6; j++) {
				games[idx][0]=i;
				games[idx][1]=j;
				idx++;
			}
		}
		for(int t=0; t<4; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			result = 0;
			int sum = 0;
			//승, 무, 패 횟수 입력
			win = new int[6];
			loose = new int[6];
			draw = new int[6];
			for(int i=0; i<6; i++) {
				sum += win[i] = Integer.parseInt(st.nextToken());
				sum += draw[i] = Integer.parseInt(st.nextToken());
				sum += loose[i] = Integer.parseInt(st.nextToken());
			}
			if(sum != 30) {
				sb.append(result).append(" ");
				continue;
			}
			dfs(0);
			sb.append(result).append(" ");
		}
		System.out.println(sb);
	}
	private static void dfs(int idx) {
		if(idx == 15) {
			result = 1;
			return;
		}
		int a = games[idx][0];
		int b = games[idx][1];
		if(win[a]>0 && loose[b]>0) {
			win[a]--;
			loose[b]--;
			dfs(idx+1);
			win[a]++;
			loose[b]++;
		}
		if(win[b]>0 && loose[a]>0) {
			win[b]--;
			loose[a]--;
			dfs(idx+1);
			win[b]++;
			loose[a]++;
		}
		if(draw[a]>0 && draw[b]>0) {
			draw[b]--;
			draw[a]--;
			dfs(idx+1);
			draw[b]++;
			draw[a]++;
		}
	}
}
