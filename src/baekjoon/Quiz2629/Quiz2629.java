package baekjoon.Quiz2629;

import java.util.*;
import java.io.*;

/*
Gold3: 양팔저울 / [DP, dfs]
1. 추를 선택하지 않는 경우, 더하는 경우, 빼는 경우 총 세 가지로 구분하여 dfs 탐색한다.
2. 동일한 조합으로 이미 구한 수라면 백트래킹한다.
3. 마지막 추를 탐색했다면 마지막 추 + 1 인덱스에 기록까지 해야한다.
4. 마지막 추 + 1에 기록된 결과를 바탕으로 출력한다.
 */
public class Quiz2629 {

	private static int cubeCount, beadCount;
	private static int[] cubes, beads;
	private static boolean[][] dp;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		cubeCount = Integer.parseInt(br.readLine());
		cubes = new int[cubeCount];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i=0; i<cubeCount; i++) {
			cubes[i] = Integer.parseInt(st.nextToken());
		}

		beadCount = Integer.parseInt(br.readLine());
		beads = new int[beadCount];
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<beadCount; i++) {
			beads[i] = Integer.parseInt(st.nextToken());
		}

		dp = new boolean[cubeCount + 1][40001];
		dfs(0, 0);

		StringBuilder sb = new StringBuilder();
		for(int i=0; i<beadCount; i++) {
			if(dp[cubeCount][beads[i]]) {
				sb.append("Y").append(" ");
				continue;
			}
			sb.append("N").append(" ");
		}

		System.out.println(sb);
	}

	private static void dfs(int idx, int number) {
		if(dp[idx][number]) return;
		dp[idx][number] = true;

		if(idx == cubeCount) return;

		dfs(idx + 1, number);
		dfs(idx + 1, number + cubes[idx]);
		dfs(idx + 1, Math.abs(number - cubes[idx]));
	}
}
