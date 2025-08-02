package baekjoon.Quiz2217;

import java.io.*;
import java.util.*;

/*
Silver4: 로프 / [greedy]
*/
public class Quiz2217 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		Integer[] arr = new Integer[N];
		for(int i=0; i<N; i++) {
			arr[i] = Integer.parseInt(br.readLine());
		}

		Arrays.sort(arr, (o1, o2) -> o2 - o1);
		int max = 0;
		for(int i=0; i<N; i++) {
			max = Math.max(max, arr[i] * (i + 1));
		}

		System.out.println(max);
	}
}
