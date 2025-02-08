package baekjoon.Quiz12738;

import java.io.*;
import java.util.*;

public class Quiz12738 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[] sequence = new int[N];

		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i=0; i<N; i++) {
			sequence[i] = Integer.parseInt(st.nextToken());
		}

		List<Integer> subSequence = new ArrayList<>();
		subSequence.add(sequence[0]);

		for(int i=1; i<N; i++) {
			if(sequence[i] > subSequence.get(subSequence.size() - 1)) {
				subSequence.add(sequence[i]);
				continue;
			}

			int l = 0;
			int r = subSequence.size();
			int idx = 0;
			while(l <= r) {
				int m = (l + r) / 2;
				if(sequence[i] > subSequence.get(m)) {
					l = m + 1;
				} else {
					idx = m;
					r = m - 1;
				}
			}

			subSequence.set(idx, sequence[i]);
		}

		System.out.println(subSequence.size());
	}
}
