package programmers.pccp;

import java.util.*;

// 석유 그룹핑
// flood fill을 활용해서 그룹별 idx, value를 map으로 관리
// 그룹 idx를 관리하는 map에서 각 열에 속한 그룹을 확인 -> 해당 그룹의 value 합을 도출
public class OilDrilling {
	static int N, M;
	static int[][] map;
	static boolean[][] visited;
	static int[][] group;
	static Map<Integer, Integer> valueByGroup = new HashMap<>();
	static int currentGroupIdx;
	static int currentGroupValue;
	static int[] dy = {-1, 1, 0, 0};
	static int[] dx = {0, 0, -1, 1};

	public static void main(String[] args) {
		// int[][] arr = {
		// 	{0, 0, 0, 1, 1, 1, 0, 0},
		// 	{0, 0, 0, 0, 1, 1, 0, 0},
		// 	{1, 1, 0, 0, 0, 1, 1, 0},
		// 	{1, 1, 1, 0, 0, 0, 0, 0},
		// 	{1, 1, 1, 0, 0, 0, 1, 1}
		// }; // 9
		int[][] arr = {
		{1, 0, 1, 0, 1, 1},
		{1, 0, 1, 0, 0, 0},
		{1, 0, 1, 0, 0, 1},
		{1, 0, 0, 1, 0, 0},
		{1, 0, 0, 1, 0, 1},
		{1, 0, 0, 0, 0, 0},
		{1, 1, 1, 1, 1, 1}
		}; // 16
		int result = solution(arr);
		System.out.println(result);
	}
	public static int solution(int[][] land) {
		init(land);
		doGrouping();
		int answer = getMaxValue();
		return answer;
	}
	private static void init(int[][] land) {
		map = land;
		N = land.length;
		M = land[0].length;
		visited = new boolean[N][M];
		group = new int[N][M];
	}
	private static void doGrouping() {
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				if(map[i][j] == 1 && !visited[i][j]) {
					currentGroupIdx++;
					currentGroupValue = 0;
					floodFill(i, j);
					valueByGroup.put(currentGroupIdx, currentGroupValue);
				}
			}
		}
	}
	private static void floodFill(int y, int x) {
		ArrayDeque<int[]> ad = new ArrayDeque<>();
		ad.addLast(new int[] {y, x});

		while(!ad.isEmpty()) {
			int[] point = ad.removeFirst();
			int r = point[0];
			int c = point[1];
			if(visited[r][c]) continue;
			visited[r][c] = true;
			group[r][c] = currentGroupIdx;
			currentGroupValue++;
			for(int d=0; d<4; d++) {
				int mr = r + dy[d];
				int mc = c + dx[d];
				if(mr < 0 || mc < 0 || mr >= N || mc >= M || visited[mr][mc] || map[mr][mc] == 0) continue;
				ad.addLast(new int[] {mr, mc});
			}
		}
	}
	private static int getMaxValue() {
		int max = 0;
		for(int i=0; i<M; i++) {
			boolean[] check = new boolean[currentGroupIdx+1];
			int sum = 0;
			for(int j=0; j<N; j++) {
				int targetGroup = group[j][i];
				if(!check[targetGroup] && targetGroup > 0) {
					check[targetGroup] = true;
					sum += valueByGroup.get(targetGroup);
				}
			}
			max = Math.max(max, sum);
		}
		return max;
	}
}
