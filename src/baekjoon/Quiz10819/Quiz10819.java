package baekjoon.Quiz10819;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 순열
// 인접한 수의 차, 모든 차의 합의 최댓값
// 시간 복잡도: O(N!), 8!
public class Quiz10819 {
	static int N, max;
	static int[] arr;
	static int[] target;
	static boolean[] visit;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		arr = new int[N];
		target = new int[N];
		visit = new boolean[N];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i=0; i<N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		perm(0);
		System.out.println(max);
	}
	private static void perm(int idx) {
		if(idx == N) {
			int sum = getSum();
			max = Math.max(max, sum);
			return;
		}
		for(int i=0; i<N; i++) {
			if(visit[i]) continue;
			target[idx] = arr[i];
			visit[i] = true;
			perm(idx+1);
			visit[i] = false;
		}
	}
	private static int getSum() {
		int sum = 0;
		for(int i=1; i<N; i++) {
			sum += Math.abs(target[i-1] - target[i]);
		}
		return sum;
	}
}
