package baekjoon.Quiz15649_7;

import java.io.*;
import java.util.*;

public class Quiz15650 {
	private static int N;
	private static int M;
	private static int[] tgt;
	private static boolean[] check;
	private static StringBuilder sb = new StringBuilder();
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		tgt = new int[M];
		check = new boolean[N];
		recur(0);
		System.out.println(sb);
	}
	private static void recur(int idx) {
		if(idx==M) {
			for(int i=0; i<M; i++) {
				sb.append(tgt[i]).append(" ");
			}
			sb.append("\n");
			return;
		}
		for(int i=1; i<=N; i++) {
			if(check[i-1]) continue;
			if(idx>0 && tgt[idx-1]>i) continue;
			check[i-1] = true;
			tgt[idx]=i;
			recur(idx+1);
			check[i-1] = false;
		}
	}
}
