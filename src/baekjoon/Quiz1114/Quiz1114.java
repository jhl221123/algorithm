package baekjoon.Quiz1114;

import java.io.*;
import java.util.*;

public class Quiz1114 {
	static int[] loads;
	static int init;
	static int L, K, C;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		L = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());

		int[] spots = new int[K];
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<K; i++) {
			spots[i] = Integer.parseInt(st.nextToken());
		}

		Arrays.sort(spots);

		loads = new int[K + 1];
		loads[0] = spots[0];
		for(int i=1; i<K; i++) {
			loads[i] = spots[i] - spots[i-1];
		}
		loads[K] = L - spots[K-1];

		int l = 0;
		int r = L+1;
		int max = 0;
		int start = 0;
		while(l <= r) {
			int m = (l + r) / 2;
			if(isDivideBy(m)) {
				max = m;
				start = init;
				r = m - 1;
			} else {
				l = m + 1;
			}
		}

		System.out.println(max + " " + start);
	}

	private static boolean isDivideBy(int limit) {
		for(int i=0; i<=K; i++) {
			if(loads[i] > limit) return false;
		}

		int sum = 0;
		int cnt = 0;
		int last = 0;
		for(int i=K; i>=0; i--) {
			if(sum + loads[i] > limit) {
				sum = loads[i];
				cnt++;
			} else {
				sum += loads[i];
			}
			last = loads[i];
		}

		init = sum;
		if(cnt < C) init = last;
		return cnt <= C;
	}
}
