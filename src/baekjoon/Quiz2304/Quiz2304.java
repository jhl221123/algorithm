package baekjoon.Quiz2304;

import java.util.*;
import java.io.*;

public class Quiz2304 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		Bridge[] bridges = new Bridge[N];

		for(int i=0; i<N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			bridges[i] = new Bridge(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
		}

		Arrays.sort(bridges, Comparator.comparingInt(o -> o.x));

		int answer = 0;
		int from = bridges[0].x;
		int fromH = bridges[0].h;
		int end = 0;
		for(int i=1; i<N; i++) {
			if(fromH < bridges[i].h) {
				answer += (bridges[i].x - from) * fromH;
				from = bridges[i].x;
				fromH = bridges[i].h;
				end = i;
			}
		}

		answer += fromH;

		int reverseFrom = bridges[N-1].x + 1;
		int reverseFromH = bridges[N-1].h;
		for(int i=N-2; i>=end; i--) {
			if(reverseFromH <= bridges[i].h) {
				answer += (reverseFrom - (bridges[i].x + 1)) * reverseFromH;
				reverseFrom = bridges[i].x + 1;
				reverseFromH = bridges[i].h;
			}
		}

		System.out.println(answer);
	}

	static class Bridge {
		int x;
		int h;

		public Bridge(int x, int h) {
			this.x = x;
			this.h = h;
		}
	}
}
