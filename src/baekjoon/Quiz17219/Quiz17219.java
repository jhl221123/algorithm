package baekjoon.Quiz17219;

import java.io.*;
import java.util.*;

/*
Silver4: 비밀번호 찾기 / [data structure]
*/
public class Quiz17219 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] NM = br.readLine().split(" ");
		int N = Integer.parseInt(NM[0]);
		int M = Integer.parseInt(NM[1]);

		Map<String, String> map = new HashMap<>();
		for(int i=0; i<N; i++) {
			String[] info = br.readLine().split(" ");
			map.put(info[0], info[1]);
		}

		StringBuilder sb = new StringBuilder();
		for(int i=0; i<M; i++) {
			String key = br.readLine();
			sb.append(map.get(key)).append("\n");
		}
		System.out.print(sb);
	}
}
