package baekjoon.Quiz9663;

import java.io.*;

public class Quiz9663 {
	private static int N;
	private static int[] arr;
	private static int count = 0;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		arr = new int[N];
		recur(0);
		System.out.println(count);
	}
	private static void recur(int col) {
		if(col==N) {
			count++;
			return;
		}
		for(int i=0; i<N; i++) {
			arr[col] = i;
			if(isPossible(col)) {
				recur(col+1);
			}
		}
	}
	private static boolean isPossible(int col) {
		for(int i=0; i<col; i++) {
			if(Math.abs(arr[col]-arr[i]) == col-i) return false;
			if(arr[i]==arr[col]) return false;
		}
		return true;
	}
}
