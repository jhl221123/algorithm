package baekjoon.Quiz10775;

import java.io.*;

public class Quiz10775 {
	static int[] parents;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int G = Integer.parseInt(br.readLine());
		int P = Integer.parseInt(br.readLine());
		parents = new int[G+1];
		int[] airplanes = new int[P];
		for(int i=0; i<=G; i++) {
			parents[i] = i;
		}

		for(int i=0; i<P; i++) {
			airplanes[i] = Integer.parseInt(br.readLine());
		}

		int cnt = 0;
		for(int i=0; i<P; i++) {
			int num = airplanes[i];
			if(find(num) == 0) break;
			cnt++;
			union(num, find(num)-1);
		}

		System.out.println(cnt);
	}

	private static void union(int a, int b) {
		a = find(a);
		b = find(b);
		parents[a] = b;
	}

	private static int find(int c) {
		if(c == parents[c]) return c;
		return parents[c] = find(parents[c]);
	}
}
