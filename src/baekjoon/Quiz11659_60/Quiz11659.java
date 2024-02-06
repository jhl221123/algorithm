package baekjoon.Quiz11659_60;

import java.util.*;
import java.io.*;

public class Quiz11659 {
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int[] arr = new int[N+1];
		st = new StringTokenizer(br.readLine());
		for(int i=1; i<=N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		int[] acc = new int[N+1];
		for(int i=1; i<=N; i++) {
			acc[i] = acc[i-1] + arr[i];
		}
		StringBuilder sb = new StringBuilder();
		while(M-- > 0) {
			st = new StringTokenizer(br.readLine());
			int l = Integer.parseInt(st.nextToken());
			int r = Integer.parseInt(st.nextToken());
			sb.append(acc[r] - acc[l-1]).append("\n");
		}
		System.out.println(sb);
	}
}
