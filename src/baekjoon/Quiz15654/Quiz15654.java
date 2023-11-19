package baekjoon.Quiz15654;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.Scanner;

// 전체 시간 복잡도: O(N^M)
public class Quiz15654 {
	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int M = sc.nextInt();
		Integer[] arr = new Integer[N];
		for(int i=0; i<N; i++) {
			arr[i] = sc.nextInt();
		}
		// Tim Sort(NlogN);
		Arrays.sort(arr);
		int depth = 0;
		Integer[] result = new Integer[M];
		boolean[] check = new boolean[N];
		recursion(arr, result, check, M, depth);
	}
	private static void recursion(Integer[] arr, Integer[] result, boolean[] check, int M, int depth) throws
		IOException {
		if(depth==M) {
			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
			for(int i=0; i<M; i++) {
				bw.write(result[i] + " ");
			}
			bw.write("\n");
			bw.flush();
			return;
		}
		for(int i=0; i<arr.length; i++) {
			if(!check[i]) {
				check[i] = true;
				result[depth++] = arr[i];
				recursion(arr, result, check, M, depth);
				check[i] = false;
				depth--;
			}
		}
	}
}
