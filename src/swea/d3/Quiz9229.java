package swea.d3;

import java.util.Scanner;

public class Quiz9229 {
	static int N;
	static int M;
	static int[] arr;
	static int max;
	static int[] tgt;
	public static void main(String[] args) throws Exception {

		Scanner sc = new Scanner(System.in);
		int T;
		T=sc.nextInt();

		for(int test_case = 1; test_case <= T; test_case++)
		{
			max = -1;
			N = sc.nextInt();
			M = sc.nextInt();
			arr = new int[N];
			tgt = new int[2];
			for(int i=0; i<N; i++) {
				arr[i] = sc.nextInt();
			}
			recur(0, 0);
			System.out.println("#" + test_case + " " +max);
		}
	}
	private static void recur(int idx, int sIdx) {
		if(idx == 2) {
			int currentSum = tgt[0] + tgt[1];
			if(currentSum<=M) max = Math.max(max, currentSum);
			return;
		}
		for(int i=sIdx; i<N; i++) {
			tgt[idx] = arr[i];
			recur(idx+1, i+1);
		}
	}
}
