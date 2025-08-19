package baekjoon.Quiz9375;

import java.io.*;
import java.util.*;

/*
Silver3: 패션왕 신해빈 / [math]
*/
public class Quiz9375 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();

		while(T-- > 0) {
			int N = Integer.parseInt(br.readLine());
			Map<String, Integer> map = new HashMap<>();

			for(int i=0; i<N; i++) {
				String[] info = br.readLine().split(" ");
				String item = info[1];
				if(!map.containsKey(item)) {
					map.put(item, 1);
				}

				map.put(item, map.get(item) + 1);
			}

			int ans = 1;
			for(String key : map.keySet()) {
				int count = map.get(key);
				ans *= count;
			}

			sb.append(ans - 1).append("\n");
		}

		System.out.print(sb);
	}
}
