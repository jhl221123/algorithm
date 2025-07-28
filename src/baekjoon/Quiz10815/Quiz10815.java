package baekjoon.Quiz10815;

import java.io.*;
import java.util.*;

/*
Silver5: 숫자 카드 / [data-structure]
*/
public class Quiz10815 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		String[] ns = br.readLine().split(" ");
		Set<Integer> set = new HashSet<>();
		for(int i=0; i<N; i++) {
			set.add(Integer.parseInt(ns[i]));
		}

		int M = Integer.parseInt(br.readLine());
		int[] targets = Arrays.stream(br.readLine().split(" "))
			.mapToInt(Integer::parseInt)
			.toArray();

		StringBuilder sb = new StringBuilder();
		for(int i=0; i<M; i++) {
			if(set.contains(targets[i])) sb.append(1).append(" ");
			else sb.append(0).append(" ");
		}
		System.out.println(sb);
	}
}
