package swea.d2;

import java.util.Scanner;

public class Quiz1204 {
	public static void main(String[] args) throws Exception {
		Scanner sc = new Scanner(System.in);
		int T;
		T=sc.nextInt();

		for(int test_case = 1; test_case <= T; test_case++) {
			int test_num = sc.nextInt();
			int[] score = new int[1001];
			for(int i=1; i<=1000; i++) {
				score[sc.nextInt()]++;
			}
			int max = 0;
			int ans = 0;
			for(int i=1; i<=1000; i++) {
				if(max <= score[i]) {
					max = score[i];
					ans = i;
				}
			}

			System.out.println("#"+test_num+" "+ans);
		}
	}
}
