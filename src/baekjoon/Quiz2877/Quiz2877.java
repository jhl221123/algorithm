package baekjoon.Quiz2877;

import java.util.Scanner;

// 전체 시간 복잡도: O(logK)
public class Quiz2877 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int K = sc.nextInt();
		StringBuilder sb = new StringBuilder();
		int target = K+1;
		while(target != 1) {
			sb.append((target % 2 == 0) ? 4 : 7);
			target /= 2;
		}
		System.out.println(sb.reverse());
	}
}
