package baekjoon.Quiz13305;

import java.io.*;
import java.util.*;

/*
Silver3: 주유소 / [greedy]
*/
public class Quiz13305 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		long[] dists = Arrays.stream(br.readLine().split(" "))
			.mapToLong(Long::parseLong)
			.toArray();
		long[] costs = Arrays.stream(br.readLine().split(" "))
			.mapToLong(Long::parseLong)
			.toArray();
		long cost = 1_000_000_001;
		long min = 0;
		for(int i=0; i<N-1; i++) {
			cost = Math.min(cost, costs[i]);
			min += (dists[i] * cost);
		}

		System.out.println(min);
	}
}
