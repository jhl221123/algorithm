package baekjoon.Quiz15663;

import java.util.*;
import java.io.*;

// 1. 입력
// 2. 정렬
// 3. 깊이 별로 방문 배열을 관리
// 시간 복잡도: < O(N!)
public class Quiz15663 {
	static int N, M;
	static int[] arr;
	static boolean[] visit;
	static int[] temp;
	static Set<String> set = new HashSet<>();
	static StringBuilder sb = new StringBuilder();
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		arr = new int[N];
		temp = new int[M];
		visit = new boolean[N];
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		Arrays.sort(arr);
		dfs(0);
		System.out.print(sb);
	}
	private static void dfs(int v) {
		if(v == M) {
			StringBuilder tempSb = new StringBuilder();
			for(int num : temp) {
				tempSb.append(num).append(" ");
			}
			if (!set.contains(String.valueOf(tempSb))) {
				set.add(String.valueOf(tempSb));
				sb.append(tempSb).append("\n");
			}
			return;
		}
		for(int i=0; i<N; i++) {
			if(visit[i]) continue;
			visit[i] = true;
			temp[v] = arr[i];
			dfs(v+1);
			visit[i] = false;
		}
	}
}
