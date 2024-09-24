package baekjoon.Quiz2109;

import java.io.*;
import java.util.*;

public class Quiz2109 {
	static int[] parents;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		Lecture[] lectures = new Lecture[N];
		parents = new int[10001];
		for(int i=0; i<N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int p = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken());
			lectures[i] = new Lecture(p, d);
		}

		Arrays.sort(lectures, (o1, o2) -> o2.p - o1.p);

		for(int i=0; i<=10000; i++) {
			parents[i] = i;
		}

		int sum = 0;
		for(int i=0; i<N; i++) {
			Lecture lecture = lectures[i];
			if(find(lecture.d) == 0) continue;
			sum += lecture.p;
			union(lecture.d, find(lecture.d) - 1);
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

	static class Lecture {
		int p, d;

		public Lecture(int p, int d) {
			this.p = p;
			this.d = d;
		}
	}
}
