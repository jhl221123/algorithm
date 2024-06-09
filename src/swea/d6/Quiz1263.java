package swea.d6;

import java.util.*;
import java.io.*;

public class Quiz1263 {

	public static void main(String args[]) throws Exception
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		for(int test_case = 1; test_case <= T; test_case++)
		{
			StringTokenizer st = new StringTokenizer(br.readLine());
			int n = Integer.parseInt(st.nextToken());
			int[][] arr = new int[n][n];
			for(int i=0; i<n; i++) {
				for(int j=0; j<n; j++) {
					arr[i][j] = Integer.parseInt(st.nextToken());
					if(arr[i][j] == 0) arr[i][j] = 1001;
				}
			}
			for(int k=0; k<n; k++) {
				for(int i=0; i<n; i++) {
					for(int j=0; j<n; j++) {
						arr[i][j] = Math.min(arr[i][j], arr[i][k] + arr[k][j]);
					}
				}
			}
			int ans = Integer.MAX_VALUE;
			for(int i=0; i<n; i++) {
				int sum = 0;
				for(int j=0; j<n; j++) {
					if(arr[i][j] > 1000) continue;
					if(i==j) continue;
					sum += arr[i][j];
				}
				ans = Math.min(ans, sum);
			}
			sb.append("#").append(test_case).append(" ").append(ans).append("\n");
		}
		System.out.println(sb);
	}
}
