package baekjoon.Quiz2559;

import java.io.*;
import java.util.*;

/*
Silver3: 수열 / [math]
*/
public class Quiz2559 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] NK = br.readLine().split(" ");
		int N = Integer.parseInt(NK[0]);
		int K = Integer.parseInt(NK[1]);
		int subsum = 0;
		int[] degree = Arrays.stream(br.readLine().split(" "))
			.mapToInt(Integer::parseInt)
			.toArray();
		for(int i=0; i<K; i++) {
			subsum += degree[i];
		}

		int max = subsum;
		for(int i=K; i<N; i++) {
			subsum = subsum - degree[i - K] + degree[i];
			max = Math.max(max, subsum);
		}

		System.out.println(max);
	}
}
