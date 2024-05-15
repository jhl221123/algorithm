package swea.SW;

import java.util.*;
import java.io.*;
public class Quiz4012 {
	static int N;
	static int[][] arr;
	static boolean[] visit;
	static int min;
	static StringBuilder sb = new StringBuilder();
	public static void main(String[] args) throws Exception
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T= Integer.parseInt(br.readLine());

		for(int test_case = 1; test_case <= T; test_case++)
		{
			// 1. 모든 식재료 시너지 존재 : 인접 행렬(단방향)
			// 2. N/2개의 식재료를 조합하고 각 식재료가 메인일 때의 점수를 계산한다.
			// -> 식재료의 조합은 visit 배열로 두 가지 음식을 관리한다: true or false
			// -> 완성된 visit 배열의 각 원소는 순차적으로 메인음식이 된다: 메인음식은 그래프의 열로 정점에 해당: 가중치의 합을 구하면 해당 조합에서 나올 수 있는 음식 중 하나가 된다.
			// 2-1. 해당 조합에서 나올 수 있는 최소 차이를 기록한다.
			// -> 한 쪽 음식의 모든 경우의 수를 배열로 관리하고 다음 음식의 모든 경우의 수와 비교하며 최소값을 찾는다.
			// 2-2. 모든 조합에서 반복한다.
			min = Integer.MAX_VALUE;
			N = Integer.parseInt(br.readLine());
			arr = new int[N][N];
			visit = new boolean[N];
			//1
			for(int i=0; i<N; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				for(int j=0; j<N; j++) {
					arr[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			dfs(0, 0);
			sb.append("#").append(test_case).append(" ").append(min).append("\n");
		}
		// 출력
		System.out.println(sb);
	}
	private static void dfs(int srcIdx, int tgtIdx) {
		// 조합으로 재료 선정이 끝나면 나올 수 있는 음식 점수 탐색
		if(tgtIdx == N/2) {
			min = Math.min(min, getMinScore());
			return;
		}
		if(srcIdx == N) {
			return;
		}
		// N/2개의 재료 선정 과정
		visit[srcIdx] = true;
		dfs(srcIdx + 1, tgtIdx + 1);
		visit[srcIdx] = false;
		dfs(srcIdx + 1, tgtIdx);
	}
	private static int getMinScore() {
		// 2-1. 해당 조합에서 나올 수 있는 최소 차이를 기록한다.
		// -> 한 쪽 음식의 모든 경우의 수를 배열로 관리하고 다음 음식의 모든 경우의 수와 비교하며 최소값을 찾는다.

		// 식재료의 조합을 배열로 두 가지 음식을 관리한다: true or false
		int[] itemA = new int[N/2];
		int[] itemB = new int[N/2];
		int idxA = 0;
		int idxB = 0;
		for(int i=0; i<N; i++) {
			if(visit[i]) itemA[idxA++] = i;
			else itemB[idxB++] = i;
		}
		// 각 재료가 메인일 때의 점수를 계산한다. -> 메인음식은 그래프의 열로 정점에 해당: 가중치의 합을 구하면 해당 조합에서 나올 수 있는 음식 중 하나가 된다.
		int mealA = 0;
		int mealB = 0;
		for(int i=0; i<N/2; i++) {
			for(int j=0; j<N/2; j++) {
				if(i == j) continue;
				mealA+=arr[itemA[i]][itemA[j]];
				mealB+=arr[itemB[i]][itemB[j]];
			}
		}
		return Math.abs(mealA - mealB);
	}
}
