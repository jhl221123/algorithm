package baekjoon.Quiz29768;

import java.io.*;
import java.util.*;

public class Quiz29768 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		StringBuilder sb = new StringBuilder();

		for(int i=0; i<N-K; i++) {
			sb.append("a");
		}

		char s = 'a';
		for(int i=0; i<K; i++) {
			sb.append(s++);
		}

		System.out.println(sb);
	}
}
