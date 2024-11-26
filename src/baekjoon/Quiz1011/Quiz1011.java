package baekjoon.Quiz1011;

import java.io.*;
import java.util.*;

public class Quiz1011 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();

		while(T-- > 0) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int s = Integer.parseInt(st.nextToken());
			int e = Integer.parseInt(st.nextToken());
			int dist = e - s;
			int max = (int)Math.sqrt(dist);

			if(max == Math.sqrt(dist)) {
				sb.append(max * 2 - 1);
			} else if(dist <= max * max + max) {
				sb.append(max * 2);
			} else {
				sb.append(max * 2 + 1);
			}
			sb.append("\n");
		}

		System.out.print(sb);
	}
}
