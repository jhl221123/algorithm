package baekjoon.Quiz15649_7;

import java.io.*;
import java.util.*;

public class Quiz15651 {
	private static int N;
	private static int M;
	private static int[] tgt;
	private static StringBuilder sb = new StringBuilder();
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		tgt = new int[M];
		perm(0);
		System.out.println(sb);
	}
	private static void perm(int idx) {
		if(idx==M) {
			for(int num : tgt) {
				sb.append(num + 1).append(" ");
			}
			sb.append("\n");
			return;
		}
		for(int i=0; i<N; i++) {
			tgt[idx] = i;
			perm(idx+1);
		}
	}
}
