package baekjoon.Quiz1149;

import java.util.*;
import java.io.*;

public class Quiz1149 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[][] arr = new int[N+1][3];
		for(int i=1; i<=N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int j=0; j<3; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		for(int i=1; i<=N; i++) {
			for(int j=0; j<3; j++) {
				int min = Integer.MAX_VALUE;
				for(int k=0; k<3; k++) {
					if(j==k) continue;
					min = Math.min(min, arr[i][j] + arr[i-1][k]);
				}
				arr[i][j] = min;
			}
		}
		int ans = Integer.MAX_VALUE;
		for(int i=0; i<3; i++) {
			ans = Math.min(ans, arr[N][i]);
		}
		System.out.println(ans);
	}
}
