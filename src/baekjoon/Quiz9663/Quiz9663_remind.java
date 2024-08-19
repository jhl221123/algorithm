package baekjoon.Quiz9663;

import java.io.*;

public class Quiz9663_remind {
	static int N = 0;
	static int cnt = 0;
	static int[] visited;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		visited = new int[N];
		dfs(0);
		System.out.println(cnt);
	}

	private static void dfs(int col) {
		if(col == N) {
			cnt++;
			return;
		}

		for(int row=0; row<N; row++) {
			if(possible(col, row)) {
				visited[col] = row;
				dfs(col + 1);
			}
		}
	}

	private static boolean possible(int col, int row) {
		// left
		for(int i=col-1; i>=0; i--) {
			if(visited[i] == row) return false;
		}
		//left top
		for(int i=col-1, j=1; i>=0 && row-j>=0; i--, j++) {
			if(visited[i] == row - j) return false;
		}
		//left bottom
		for(int i=col-1, j=1; i>=0 && row+j<N; i--, j++) {
			if(visited[i] == row + j) return false;
		}
		return true;
	}
}
