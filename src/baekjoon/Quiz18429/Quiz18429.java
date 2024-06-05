package baekjoon.Quiz18429;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//1. 키트 순서대로 dfs 수행
//2. 중량이 500 미만이라면 return
//3. 모든 키트를 사용했다면 경우의 수 + 1
public class Quiz18429 {
	static int N, K, count;
	static int base = 500;
	static int[] weight;
	static boolean[] used;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		weight = new int[N];
		used = new boolean[N];
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<N; i++) {
			weight[i] = Integer.parseInt(st.nextToken());
		}
		dfs(0);
		System.out.println(count);
	}
	private static void dfs(int v) {
		if(base < 500) return;
		if(v==N) {
			count++;
			return;
		}
		for(int i=0; i<N; i++) {
			if(used[i]) continue;
			used[i] = true;
			int num = weight[i] - K;
			base += num;
			dfs(v+1);
			used[i] = false;
			base -= num;
		}
	}
}
