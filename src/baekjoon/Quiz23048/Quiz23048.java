package baekjoon.Quiz23048;

import java.util.*;
import java.io.*;
import java.util.stream.Collectors;

/*
Gold5: 자연수 색칠하기 / [math, sieve of eratosthenes]
*/
public class Quiz23048 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[] arr = new int[N + 1];
		int color = 1;
		arr[1] = color;

		for(int i=2; i<=N; i++) {
			if(arr[i] > 0) continue;
			color++;

			for(int j=1; i*j<=N; j++) {
				arr[i * j] = color;
			}
		}

		StringBuilder sb = new StringBuilder();
		sb.append(color).append("\n");
		sb.append(Arrays.stream(arr).filter(n->n!=0).mapToObj(String::valueOf).collect(Collectors.joining(" ")));
		System.out.println(sb);
	}
}
