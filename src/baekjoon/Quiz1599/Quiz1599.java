package baekjoon.Quiz1599;

import java.io.*;
import java.util.*;

public class Quiz1599 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		String[] words = new String[N];
		StringBuilder sb = new StringBuilder();

		for(int i=0; i<N; i++) {
			words[i] = br.readLine();
		}

		for(int i=0; i<N; i++) {
			words[i] = words[i].replace("k", "c").replace("ng", "nz");
		}

		Arrays.sort(words);


		for(int i=0; i<N; i++) {
			words[i] = words[i].replace("c", "k").replace("nz", "ng");
		}

		for(int i=0; i<N; i++) {
			sb.append(words[i]).append("\n");
		}
		System.out.print(sb);
	}
}
