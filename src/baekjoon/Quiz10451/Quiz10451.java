package baekjoon.Quiz10451;

import java.util.*;
import java.io.*;

public class Quiz10451 {
	// 1. 입력
	// 2. 1부터 dfs
	// 2-1. 방문한 요소는 check 처리
	// 2-2. 시작한 지점으로 돌아온다면 종료 후 개수 증가
	static int T, N, count;
	static int[] arr;
	static boolean[] visit;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		T = Integer.parseInt(br.readLine());
		while(T-- > 0) {
			N = Integer.parseInt(br.readLine());
			arr = new int[N+1];
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int i=1; i<=N; i++) {
				arr[i] = Integer.parseInt(st.nextToken());
			}
			visit = new boolean[N+1];
			count = 0;
			for(int i=1; i<=N; i++) {
				if(visit[i]) continue;
				dfs(i);
				count++;
			}
			sb.append(count).append("\n");
		}
		System.out.print(sb);
	}
	static void dfs(int start) {
		visit[start] = true;
		int target = arr[start];
		while(target != start) {
			visit[target] = true;
			target = arr[target];
		}
	}
}
