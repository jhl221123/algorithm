package baekjoon.Quiz7568;

import java.util.*;
import java.io.*;

// 부르트 포스, O(N^2)
public class Quiz7568 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[][] size = new int[N][2];
		for(int i=0; i<N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			size[i][0] = Integer.parseInt(st.nextToken());
			size[i][1] = Integer.parseInt(st.nextToken());
		}
		StringBuilder sb = new StringBuilder();
		for(int i=0; i<N; i++) {
			int kg = size[i][0];
			int cm = size[i][1];
			int cnt = 0;
			for(int j=0; j<N; j++) {
				if(i==j) continue;
				int nkg = size[j][0];
				int ncm = size[j][1];
				if(kg < nkg) {
					if(cm < ncm) {
						cnt++;
					}
				}
			}
			sb.append(cnt+1).append(" ");
		}
		System.out.println(sb);
	}
}
