package baekjoon.Quiz12015;

import java.util.*;
import java.io.*;

public class Quiz12015 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[] numberSequence = new int[N];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i=0; i<N; i++) {
			numberSequence[i] = Integer.parseInt(st.nextToken());
		}

		List<Integer> lis = new ArrayList<>();
		lis.add(numberSequence[0]);
		for(int i=1; i<N; i++) {
			if(lis.get(lis.size() - 1) < numberSequence[i]) {
				lis.add(numberSequence[i]);
			} else {
				calculateLIS(numberSequence[i], lis);
			}
		}

		System.out.println(lis.size());
	}

	private static void calculateLIS(int number, List<Integer> lis) {
		int l = 0;
		int r = lis.size();
		int pos = 0;

		while(l <= r) {
			int m = (l + r) / 2;
			if(lis.get(m) < number) {
				l = m + 1;
			} else {
				pos = m;
				r = m -1;
			}
		}

		lis.set(pos, number);
	}
}
