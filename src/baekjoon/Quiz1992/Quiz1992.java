package baekjoon.Quiz1992;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Quiz1992 {

	static int N;
	static int[][] arr;
	static StringBuilder sb = new StringBuilder();
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		arr = new int[N][N];
		for(int i=0; i<N; i++) {
			String str = br.readLine();
			for(int j=0; j<N; j++) {
				arr[i][j] = str.charAt(j)-'0';
			}
		}
		dfs(0, 0);
		System.out.println(sb);
	}
	private static void dfs(int r, int c) {
		if(isSame(r, c)) {
			sb.append(arr[r][c]);
			return;
		}
		sb.append("(");
		N/=2;
		dfs(r, c);
		dfs(r, c+N);
		dfs(r+N, c);
		dfs(r+N, c+N);
		sb.append(")");
		N*=2;
	}
	private static boolean isSame(int r, int c) {
		boolean result = true;
		int base = arr[r][c];
		for(int i=r; i<r+N; i++) {
			for(int j=c; j<c+N; j++) {
				if (arr[i][j] != base) {
					result = false;
					break;
				}
			}
		}
		return result;
	}
}
