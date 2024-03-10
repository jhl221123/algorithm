package baekjoon.Quiz9466;

import java.util.*;
import java.io.*;

public class Quiz9466 {
	static int T, N, count;
	static int[] arr;
	static int[] depth;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		T = Integer.parseInt(br.readLine());
		while(T-- > 0) {
			N = Integer.parseInt(br.readLine());
			arr = new int[N+1];
			depth = new int[N+1];
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int i=1; i<=N; i++) {
				arr[i] = Integer.parseInt(st.nextToken());
				depth[i] = 0;
			}
			count = N;
			for(int i=1; i<=N; i++) {
				if(depth[i] != 0) continue;
				count -= dfs(i);
			}
			sb.append(count).append("\n");
		}
		System.out.print(sb);
	}
	static int dfs(int start) {
		int ne = arr[start];
		int cnt = 0;
		if(depth[ne] == 0) {
			depth[ne] = depth[start] + 1;
			cnt += dfs(ne);
		}
		else {
			cnt = depth[start] - depth[ne] + 1;
		}
		depth[start] = 100001;
		return Math.max(cnt, 0);
	}
}
