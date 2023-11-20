package baekjoon.Quiz15654_7;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.Scanner;

// 전체 시간 복잡도: O(N^M)
public class Quiz15656 {
	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int M = sc.nextInt();
		int[] arr = new int[N];
		for(int i=0; i<N; i++) {
			arr[i] = sc.nextInt();
		}
		//Dual-Pivot Quick Sort, O(N*N)
		Arrays.sort(arr);
		int[] Nm = new int[M];
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		recursion(arr, Nm, 0, bw);
		bw.flush();
	}
	private static void recursion(int[] arr, int[] Nm, int depth, BufferedWriter bw) throws IOException {
		if(depth == Nm.length) {
			for(int i=0; i<Nm.length; i++) {
				bw.write(Nm[i] + " ");
			}
			bw.write("\n");
			return;
		}
		for(int i=0; i<arr.length; i++) {
			Nm[depth++] = arr[i];
			recursion(arr, Nm, depth, bw);
			depth--;
		}
	}
}
