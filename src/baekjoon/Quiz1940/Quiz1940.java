package baekjoon.Quiz1940;

import java.io.*;
import java.util.*;

/*
Silver4: 주몽 / [two pointer]
*/
public class Quiz1940 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int M = Integer.parseInt(br.readLine());
		int[] arr = Arrays.stream(br.readLine().split(" "))
			.mapToInt(Integer::parseInt)
			.toArray();
		Arrays.sort(arr);

		int l = 0;
		int r = N - 1;
		int count = 0;
		while(l < r) {
			int sum = arr[l] + arr[r];
			if(sum < M) {
				l++;
				continue;
			}

			if(sum > M) {
				r--;
				continue;
			}

			l++;
			r--;
			count++;
		}

		System.out.println(count);
	}
}
