package baekjoon.Quiz1781;

import java.io.*;
import java.util.*;

public class Quiz1781 {

	static int[] parents;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		Task[] tasks = new Task[N];
		parents = new int[200001];

		for(int i=0; i<N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int d = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());
			tasks[i] = new Task(d, w);
		}

		for(int i=0; i<=200000; i++) {
			parents[i] = i;
		}

		Arrays.sort(tasks, (o1, o2) -> o2.w - o1.w);

		int sum = 0;
		for(int i=0; i<N; i++) {
			int cd = tasks[i].d;
			int cw = tasks[i].w;
			if(find(cd) == 0) continue;
			sum += cw;
			union(cd, find(cd) - 1);
		}

		System.out.println(sum);
	}

	private static void union(int a, int b) {
		a = find(a);
		b = find(b);
		parents[a] = b;
	}

	private static int find(int c) {
		if(parents[c] == c) return c;
		return parents[c] = find(parents[c]);
	}

	static class Task {
		int d, w;

		public Task(int d, int w) {
			this.d = d;
			this.w = w;
		}
	}
}
