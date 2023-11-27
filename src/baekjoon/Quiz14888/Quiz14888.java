package baekjoon.Quiz14888;

import java.util.Scanner;

// 전체 시간 복잡도 < O(4^(N-1))
public class Quiz14888 {
	static int N;
	static int[] An;
	static int[] operator;
	static int MAX = Integer.MIN_VALUE;
	static int MIN = Integer.MAX_VALUE;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		An = new int[N];
		for(int i=0; i<N; i++) {
			An[i] = sc.nextInt();
		}
		operator = new int[4];
		for(int i=0; i<4; i++) {
			operator[i] = sc.nextInt();
		}
		recur(1, An[0]);
		System.out.println(MAX);
		System.out.println(MIN);
	}
	private static void recur(int start, int sum) {
		if(start == N) {
			MAX = Math.max(MAX, sum);
			MIN = Math.min(MIN, sum);
			return;
		}
		for(int i=0; i<4; i++) {
			if(operator[i] > 0) {
				operator[i]--;
				if(i==0) recur(start+1, sum+An[start]);
				else if(i==1) recur(start+1, sum-An[start]);
				else if(i==2) recur(start+1, sum*An[start]);
				else recur(start+1, sum/An[start]);
				operator[i]++;
			}
		}
	}
}
