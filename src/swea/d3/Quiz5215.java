package swea.d3;

import java.util.Scanner;

public class Quiz5215 {
	public static void main(String[] args) throws Exception {
		Scanner sc = new Scanner(System.in);
		int T;
		T=sc.nextInt();
		for(int test_case = 1; test_case <= T; test_case++)
		{
			int N = sc.nextInt();
			int L = sc.nextInt();
			int[] score = new int[N];
			int[] kcal = new int[N];
			int[] max = new int[1];
			for(int i=0; i<N; i++) {
				score[i] = sc.nextInt();
				kcal[i] = sc.nextInt();
			}
			recur(score, kcal, 0, 0, 0, L, max);
			System.out.println("#" + test_case + " "+max[0]);
		}
	}
	private static void recur(int[] score, int[] kcal, int scoreSum, int kcalSum, int start, int limit, int[] max) {
		// 한계 넘으면 종료
		if(kcalSum > limit) return;
		// 한계 넘지 않으면 현재 값이 최대인지 확인 후 갱신
		max[0] = Math.max(max[0], scoreSum);
		for(int i=start; i<score.length; i++) {
			recur(score, kcal, scoreSum+score[i], kcalSum+kcal[i], i+1, limit, max);
		}
	}
}
