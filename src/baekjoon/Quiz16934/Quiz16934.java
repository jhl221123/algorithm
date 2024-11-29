package baekjoon.Quiz16934;

import java.io.*;
import java.util.*;

public class Quiz16934 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		Set<String> ch = new HashSet<>();
		ArrayList<String> result = new ArrayList<>();
		Map<String, Integer> map = new HashMap<>();
		for(int i=0; i<N; i++) {
			String str = br.readLine();
			int l = str.length();
			boolean prefix = false;

			for(int j=0; j<l; j++) {
				String sub = str.substring(0, j+1);
				if(!ch.contains(sub)) {
					result.add(sub);
					prefix = true;
					break;
				}
			}

			// 앞순서 문자열에서 만들 수 있는 모든 prefix 저장
			for(int j=0; j<l; j++) {
				String sub = str.substring(0, j+1);
				ch.add(sub);
			}

			int v = map.getOrDefault(str, 0);
			v += 1;
			if(!prefix) {
				if(v == 1) result.add(str);
				else result.add(str + v);
			}

			map.put(str, v);
		}

		StringBuilder sb = new StringBuilder();
		for(String node : result) {
			sb.append(node).append("\n");
		}
		System.out.print(sb);
	}
}
