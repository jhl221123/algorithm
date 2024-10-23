package baekjoon.Quiz18234;

import java.io.*;
import java.util.*;

public class Quiz18234 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int T = Integer.parseInt(st.nextToken());
		Carrot[] carrots = new Carrot[N];
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			int w = Integer.parseInt(st.nextToken());
			int p = Integer.parseInt(st.nextToken());
			carrots[i] = new Carrot(w, p);
		}

		Arrays.sort(carrots, Comparator.comparingInt(o -> o.p));

		long sum = 0;
		for(int i=0; i<N; i++) {
			sum += ((long)(T - N + i) * carrots[i].p + carrots[i].w);
		}

		System.out.println(sum);
	}

	static class Carrot {
		int w, p;

		public Carrot(int w, int p) {
			this.w = w;
			this.p = p;
		}
	}
}
