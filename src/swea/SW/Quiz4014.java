package swea.SW;

import java.util.*;
import java.io.*;

public class Quiz4014 {
	static int N, X;
	static int[][] map;
	public static void main(String args[]) throws Exception
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();

		for(int test_case = 1; test_case <= T; test_case++)
		{
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			X = Integer.parseInt(st.nextToken());
			int ans = 0;
			map = new int[N][N];
			for(int i=0; i<N; i++) {
				st = new StringTokenizer(br.readLine());
				for(int j=0; j<N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}

			// 가로 탐색
			for(int i=0; i<N; i++) {
				int height = map[i][0];
				int dist = 0;
				for(int j=0; j<N; j++) {
					//                    System.out.println("i, j = " + i + ", " + j);
					if(map[i][j] - height == 0) dist++;
					else if(map[i][j] - height == 1) { //올라가는 경우
						if(dist >= X) {
							dist = 1;
							height++;
						} else break;
					}
					else if(map[i][j] - height == -1) { // 내려가는 경우
						if(j+X-1 < N) { // x만큼 길이가 뒤에 있다면
							boolean flag = true;
							for(int k=j; k<j+X; k++) {
								if (map[i][k] != (height - 1)) {
									flag = false;
									break;
								}
							}
							if(flag) { // 뒤에 x만큼 길이가 모두 같은 숫자라면
								//                                System.out.println("i = " + i);
								dist = 0;
								j+=(X-1);
								height--;
							} else break;
						} else break;
					}
					else break;
					if(j==N-1) {
						//                        System.out.println("i = " + i);
						ans++;
					}
				}
			}

			// 세로 탐색
			for(int i=0; i<N; i++) {
				int height = map[0][i];
				int dist = 0;
				for(int j=0; j<N; j++) {
					//                    System.out.println("i, j = " + i + ", " + j);
					if(map[j][i] - height == 0) dist++;
					else if(map[j][i] - height == 1) {
						if(dist >= X) {
							dist = 1;
							height++;
						} else break;
					}
					else if(map[j][i] - height == -1) {
						if(j+X-1 < N) { // x만큼 길이가 뒤에 있다면
							boolean flag = true;
							for(int k=j; k<j+X; k++) {
								if (map[k][i] != (height - 1)) {
									flag = false;
									break;
								}
							}
							if(flag) { // 뒤에 x만큼 길이가 모두 같은 숫자라면
								dist = 0;
								j+=(X-1);
								height--;
							} else break;
						} else break;
					}
					else break;
					if(j==(N-1)) {
						//                        System.out.println("i = " + i);
						ans++;
					}
				}
			}
			// 출력
			sb.append("#").append(test_case).append(" ").append(ans).append("\n");
		}
		System.out.print(sb);
	}
}
