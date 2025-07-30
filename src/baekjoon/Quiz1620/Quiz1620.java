package baekjoon.Quiz1620;

import java.io.*;
import java.util.*;

/*
Silver4: 나는야 포켓몬 마스터 이다솜 / [data-structure]
*/
public class Quiz1620 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] NM = br.readLine().split(" ");
		int N = Integer.parseInt(NM[0]);
		int M = Integer.parseInt(NM[1]);

		String[] names = new String[N + 1];
		Map<String, Integer> numbers = new HashMap<>();
		for(int i=1; i<=N; i++) {
			String name = br.readLine();
			names[i] = name;
			numbers.put(name, i);
		}

		StringBuilder sb = new StringBuilder();
		for(int i=0; i<M; i++) {
			String pair = br.readLine();
			if(numbers.containsKey(pair)) {
				sb.append(numbers.get(pair));
			} else {
				sb.append(names[Integer.parseInt(pair)]);
			}
			sb.append("\n");
		}

		System.out.print(sb);
	}
}
