package baekjoon.Quiz17951;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Quiz17951 {
	static int N, K;
	static int[] scores;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		scores = new int[N];
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<N; i++) {
			scores[i] = Integer.parseInt(st.nextToken());
		}

		int l = 1;
		int r = 2000000;
		while(l <= r) {
			int mid = (l + r) / 2;
			int count = divideBy(mid);
			if(count >= K) l = mid + 1;
			else r = mid - 1;
		}

		System.out.println(r);
	}

	private static int divideBy(int target) {
		int sum = 0;
		int cnt = 0;
		for(int i=0; i<N; i++) {
			sum += scores[i];
			if(sum >= target) {
				sum = 0;
				cnt++;
			}
		}
		return cnt;
	}
}
