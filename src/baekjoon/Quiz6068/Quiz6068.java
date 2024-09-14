package baekjoon.Quiz6068;

import java.io.*;
import java.util.*;

public class Quiz6068 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		Task[] tasks = new Task[N];
		for(int i=0; i<N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int t = Integer.parseInt(st.nextToken());
			int s = Integer.parseInt(st.nextToken());
			tasks[i] = new Task(t, s);
		}

		Arrays.sort(tasks, ((o1, o2) -> o2.s - o1.s));

		int ans = tasks[0].s;
		for(int i=0; i<N; i++) {
			if(tasks[i].s < ans) ans = tasks[i].s;
			ans -= tasks[i].t;
		}

		ans = ans < 0 ? -1 : ans;
		System.out.println(ans);
	}

	static class Task {
		int t, s;

		public Task(int t, int s) {
			this.t = t;
			this.s = s;
		}
	}
}
