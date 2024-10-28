package baekjoon.Quiz2879;

import java.io.*;
import java.util.*;

public class Quiz2879 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[] before = new int[N];
		int[] after = new int[N];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i=0; i<N; i++) {
			before[i] = Integer.parseInt(st.nextToken());
		}
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<N; i++) {
			after[i] = Integer.parseInt(st.nextToken());
		}

		Diff[] diffs = new Diff[N];
		for(int i=0; i<N; i++) {
			if(before[i] < after[i]) diffs[i] = new Diff(after[i] - before[i], Type.PLUS);
			else if(before[i] == after[i]) diffs[i] = new Diff(0, Type.COMP);
			else diffs[i] = new Diff(before[i] - after[i], Type.MINUS);
		}

		int s = 0;
		Type t = diffs[s].t;
		int min = diffs[s].v;
		int e = 0;
		int sum = 0;
		while(true) {
			if(allComp(diffs)) break;
			// e가 idx 넘기면
			if(e >= diffs.length - 1) {
				// 이전까지 변경 반영
				sum += min;
				for(int i=s; i<=e; i++) {
					diffs[i].v -= min;
					if(diffs[i].v == 0) diffs[i].t = Type.COMP;
				}

				// 첫 노드부터 다시 시작
				s = 0;
				t = diffs[s].t;
				min = diffs[s].v;
				e = 0;
			}

			// 다음 노드가 같은 타입일 경우
			if(t.equals(diffs[e + 1].t)) {
				min = Math.min(min, diffs[e + 1].v);
				e++;
			}

			// 다음 노드가 다른 타입일 경우
			else {
				// 이전까지 변경 반영
				sum += min;
				for(int i=s; i<=e; i++) {
					diffs[i].v -= min;
					if(diffs[i].v == 0) diffs[i].t = Type.COMP;
				}

				// 다음 노드로 시작
				s = e + 1;
				t = diffs[s].t;
				min = diffs[s].v;
				e = e + 1;
			}
		}

		System.out.println(sum);
	}

	private static boolean allComp(Diff[] diffs) {
		for(int i=0; i<diffs.length; i++) {
			if(!Type.COMP.equals(diffs[i].t)) return false;
		}
		return true;
	}

	static class Diff {
		int v;
		Type t;

		public Diff(int v, Type t) {
			this.v = v;
			this.t = t;
		}
	}

	enum Type {
		PLUS, MINUS, COMP
	}
}
