package baekjoon.Quiz1269;

import java.io.*;
import java.util.*;

/*
Silver4: 대칭 차집합 / [data structure]
*/
public class Quiz1269 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] nm = br.readLine().split(" ");
		int N = Integer.parseInt(nm[0]);
		int M = Integer.parseInt(nm[1]);

		Set<Integer> set = new HashSet<>();
		String[] ns = br.readLine().split(" ");
		for(int i=0; i<N; i++) {
			set.add(Integer.parseInt(ns[i]));
		}

		String[] ms = br.readLine().split(" ");
		for(int i=0; i<M; i++) {
			int m = Integer.parseInt(ms[i]);
			if(set.contains(m)) {
				set.remove(m);
				continue;
			}
			set.add(m);
		}

		System.out.println(set.size());
	}
}
