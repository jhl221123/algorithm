package baekjoon.Quiz2141;

import java.io.*;
import java.util.*;

public class Quiz2141 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		Village[] villages = new Village[N];
		long total = 0;
		for(int i=0; i<N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int a = Integer.parseInt(st.nextToken());
			total += a;
			villages[i] = new Village(x, a);
		}

		Arrays.sort(villages, Comparator.comparingInt(o -> o.pos));

		long mid = (total + 1) / 2;
		long sum = 0;
		for(int i=0; i<N; i++) {
			sum += villages[i].peo;
			if(sum >= mid) {
				System.out.println(villages[i].pos);
				break;
			}
		}
	}

	static class Village {
		int pos, peo;

		public Village(int pos, int peo) {
			this.pos = pos;
			this.peo = peo;
		}
	}
}
