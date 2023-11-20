package baekjoon.Quiz15654_7;

import java.util.Arrays;
import java.util.Scanner;

// 전체 시간 복잡도: O(2^N)??
public class Quiz15655 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int M = sc.nextInt();
		int[] arr = new int[N];
		for(int i=0; i<N; i++) {
			arr[i] = sc.nextInt();
		}
		//Dual-Pivot Quick Sort, O(N^2)
		Arrays.sort(arr);
		int[] result = new int[M];
		boolean[] check = new boolean[N];
		recursion(arr, result, 0, check, 0);
	}
	private static void recursion(int[] arr, int[] result, int depth, boolean[] check, int base) {
		if(depth == result.length) {
			StringBuilder sb = new StringBuilder();
			for(int i=0; i<result.length; i++) {
				sb.append(result[i] + " ");
			}
			System.out.println(sb);
			return;
		}
		for(int i=base; i<arr.length; i++) {
			if(!check[i]) {
				check[i] = true;
				result[depth++] = arr[i];
				recursion(arr, result, depth, check, i);
				check[i] = false;
				depth--;
			}
		}
	}
}
