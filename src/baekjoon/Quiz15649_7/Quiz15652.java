package baekjoon.Quiz15649_7;

import java.io.*;
import java.util.*;

public class Quiz15652 {
	private static int N;
	private static int M;
	private static int[] tgt;
	private static StringBuilder sb = new StringBuilder();
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		// check x, N idx -> 그대로 전달
		tgt = new int[M];
		recur(1, 0);
		System.out.println(sb);
	}
	private static void recur(int rIdx, int tIdx) {
		if(tIdx == M) {
			for(int i=0; i<M; i++) {
				sb.append(tgt[i]).append(" ");
			}
			sb.append("\n");
			return;
		}
		for(int i=rIdx; i<=N; i++) {
			tgt[tIdx] = i;
			//            if(tIdx>0 && i<tgt[tIdx-1]) continue;
			recur(i, tIdx+1);
		}
	}
}
