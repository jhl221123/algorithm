package baekjoon.Quiz15649_7;

import java.io.*;
import java.util.*;

public class Quiz15649 {
	private static int N;
	private static int M;
	private static boolean[] select;
	private static int[] tgt;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		select = new boolean[N];
		tgt = new int[M];
		perm(0);
	}
	private static void perm(int idx) {
		if(idx==M) {
			for(int num : tgt) {
				System.out.print(num + 1 + " ");
			}
			System.out.println();
			return;
		}
		for(int i=0; i<N; i++) {
			if(select[i]) continue;
			tgt[idx] = i;
			select[i] = true;
			perm(idx+1);
			select[i] = false;
		}
	}
}
