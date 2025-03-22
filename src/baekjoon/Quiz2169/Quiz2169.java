package baekjoon.Quiz2169;

import java.util.*;
import java.io.*;

/*
Gold2: 로봇 조종하기 / [dp]
1. 첫 행은 오른쪽만 이동 가능하다.
2. 두 번째 행부터 양방향 모두 이동한 후 최대값을 구한다.
*/
public class Quiz2169 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int[][] map = new int[N][M];
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());

			for(int j=0; j<M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		int[][] temp = new int[2][M];
		int[][] dp = new int[N][M];
		dp[0][0] = map[0][0];
		for(int i=1; i<M; i++) {
			dp[0][i] = map[0][i] + dp[0][i-1];
		}

		for(int i=1; i<N; i++) {
			temp[0][0] = map[i][0] + dp[i-1][0];
			for(int j=1; j<M; j++) {
				temp[0][j] = Math.max(map[i][j] + dp[i-1][j], map[i][j] + temp[0][j-1]);
			}

			temp[1][M-1] = map[i][M-1] + dp[i-1][M-1];
			for(int j=M-2; j>=0; j--) {
				temp[1][j] = Math.max(map[i][j] + dp[i-1][j], map[i][j] + temp[1][j+1]);
			}

			for(int j=0; j<M; j++) {
				dp[i][j] = Math.max(temp[0][j], temp[1][j]);
			}
		}

		System.out.println(dp[N-1][M-1]);
	}
}
