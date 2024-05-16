package baekjoon.Quiz14501;

import java.util.*;
import java.io.*;

public class Quiz14501_BruteForcing {
	static int N;
	static int MAX = 0;
	static int[][] arr;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		arr = new int[N+1][2];
		for(int i=1; i<=N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			arr[i][0] = Integer.parseInt(st.nextToken());
			arr[i][1] = Integer.parseInt(st.nextToken());
		}
		dfs(1, 0);
		System.out.println(MAX);
	}
	static void dfs(int s, int v) {
		if(s>N) {
			MAX = Math.max(MAX,v);
		}
		for(int i=s; i<=N; i++) {
			int next = i + arr[i][0];
			dfs(next, next-1 <=N ? v + arr[i][1] : v);
		}
	}
}
