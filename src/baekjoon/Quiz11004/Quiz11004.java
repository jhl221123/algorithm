package baekjoon.Quiz11004;

import java.io.*;
import java.util.*;

/*
Silver5: K번째 수 / [sort]
*/
public class Quiz11004 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] NK = br.readLine().split(" ");
		int N = Integer.parseInt(NK[0]);
		int K = Integer.parseInt(NK[1]);
		int[] arr = Arrays.stream(br.readLine().split(" "))
			.mapToInt(Integer::parseInt)
			.toArray();
		Arrays.sort(arr);
		System.out.println(arr[K-1]);
	}
}
