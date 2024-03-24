package baekjoon.Quiz1780;

import java.util.*;
import java.io.*;

public class Quiz1780 {
	// N/3 지점을 활용해서 9칸 탐색
	// 모두 같다면 base를 활용해서 개수 관리
	// 다른 것이 있다면 N/=3 후 재귀 호출
	static int N;
	static int[][] arr;
	static int[] counts;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		arr = new int[N][N];
		for(int i=0; i<N;i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int j=0; j<N; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		counts = new int[3];
		dfs(N, 0, 0);
		for(int i=0; i<3; i++) {
			System.out.println(counts[i]);
		}
	}
	private static void dfs(int size, int r, int c) {
		if(size == 0) return;
		if(isSame(size, r, c)) {
			counts[arr[r][c] + 1]++;
			return;
		}
		size/=3;
		// 1행
		dfs(size, r, c);
		dfs(size, r, c+size);
		dfs(size, r, c+(size*2));
		// 2행
		dfs(size, r+size, c);
		dfs(size, r+size, c+size);
		dfs(size, r+size, c+(size*2));
		// 3행
		dfs(size, r+(size*2), c);
		dfs(size, r+(size*2), c+size);
		dfs(size, r+(size*2), c+(size*2));
	}
	private static boolean isSame(int size, int r, int c) {
		if(size == 1) return true;
		int base = arr[r][c];
		for(int i=r; i<r+size; i++) {
			for(int j=c; j<c+size; j++) {
				if(arr[i][j] != base) return false;
			}
		}
		return true;
	}
}
