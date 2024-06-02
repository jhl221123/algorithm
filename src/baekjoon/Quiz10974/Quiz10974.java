package baekjoon.Quiz10974;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Quiz10974 {
	static int N;
	static int[] nums;
	static boolean[] visit;
	static StringBuilder sb = new StringBuilder();
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		nums = new int[N];
		visit = new boolean[N];
		perm(0);
		System.out.print(sb);
	}
	private static void perm(int v) {
		if(v == N) {
			for(int num : nums) {
				sb.append(num).append(" ");
			}
			sb.append("\n");
			return;
		}
		for(int i=0; i<N; i++) {
			if(visit[i]) continue;
			visit[i] = true;
			nums[v] = i+1;
			perm(v+1);
			visit[i] = false;
		}
	}
}
