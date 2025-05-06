package baekjoon.Quiz5904;

import java.io.*;

/*
Gold5: Moo 게임 / [divide and conquer, recursion]
1. 위치 N이 포함된 Moo 길이를 구한다.
2. 1번 과정에서 구한 Moo 문자열에서 N이 left prev, mid, right prev 중 어느 곳에 위치하는지 재귀를 통해 도출한다.
*/
public class Quiz5904 {

	private static int N;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		int length = 3;
		int k = 0;

		while(length < N) {
			k++;
			length = length * 2 + 3 + k;
		}

		dfs(length, k);
	}

	private static void dfs(int length, int k) {
		int prev = (length - (3 + k)) / 2;
		if(k == 0) {
			if(N == 1) {
				System.out.println("m");
				return;
			} else {
				System.out.println("o");
				return;
			}
		}
		if(N <= prev) {
			dfs(prev, k - 1);
		} else if(N <= prev + 3 + k) {
			if(N == prev + 1) {
				System.out.println("m");
			} else {
				System.out.println("o");
			}
		} else {
			N -= (prev + 3 + k);
			dfs(prev, k - 1);
		}
	}
}
