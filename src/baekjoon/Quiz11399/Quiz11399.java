package baekjoon.Quiz11399;

import java.io.*;
import java.util.*;

/*
Silver4: ATM / [greedy]
*/
public class Quiz11399 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[] arr = Arrays.stream(br.readLine().split(" "))
			.mapToInt(Integer::parseInt)
			.toArray();
		Arrays.sort(arr);
		int sum = 0;
		int tmp = 0;
		for(int i=0; i<N; i++) {
			sum += tmp + arr[i];
			tmp += arr[i];
		}
		System.out.println(sum);
	}
}
