package baekjoon.Quiz1026;

import java.io.*;
import java.util.*;

/*
Silver4: 보물 / [dp]
*/
public class Quiz1026 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		String[] strA = br.readLine().split(" ");
		String[] strB = br.readLine().split(" ");
		Integer[] A = toIntegerArr(strA);
		Integer[] B = toIntegerArr(strB);
		Arrays.sort(A);
		Arrays.sort(B, (o1, o2) -> o2 - o1);

		int sum = 0;
		for(int i=0; i<N; i++) {
			sum += (A[i] * B[i]);
		}
		System.out.println(sum);
	}

	private static Integer[] toIntegerArr(String[] strArr) {
		Integer[] arr = new Integer[strArr.length];
		for(int i=0; i<strArr.length; i++) {
			arr[i] = Integer.parseInt(strArr[i]);
		}
		return arr;
	}
}
