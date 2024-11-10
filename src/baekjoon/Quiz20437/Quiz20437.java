package baekjoon.Quiz20437;

import java.io.*;
import java.util.*;

public class Quiz20437 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();

		while(T-- > 0) {
			String str = br.readLine();
			int K = Integer.parseInt(br.readLine());
			int min = 10001;
			int max = -1;

			for(int i=0; i<27; i++) {
				List<Integer> list = new ArrayList<>();
				for(int j=0; j<str.length(); j++) {
					if(str.charAt(j) == 'a' + i) list.add(j);
				}

				if(list.size() < K) continue;

				for(int s=0, e=K-1; e<list.size(); s++, e++) {
					int l = list.get(e) - list.get(s) + 1;
					min = Math.min(min, l);
					max = Math.max(max, l);
				}
			}

			if(min < 0 || max < 0) sb.append(-1);
			else sb.append(min).append(" ").append(max);
			sb.append("\n");
		}

		System.out.print(sb);
	}
}
