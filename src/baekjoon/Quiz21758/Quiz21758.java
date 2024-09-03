package baekjoon.Quiz21758;

import java.io.*;
import java.util.*;

public class Quiz21758 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int max = 0;
		int[] arr = new int[N];
		int[] acc = new int[N+1];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i=0; i<N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		for(int i=1; i<=N; i++) {
			acc[i] = acc[i-1] + arr[i-1];
		}

		// 꿀통: 오른쪽
		for(int i=1; i<N-1; i++) {
			max = Math.max(max, acc[N] - acc[1] - arr[i] + acc[N] - acc[i+1]);
		}

		// 꿀통: 왼쪽
		for(int i=1; i<N-1; i++) {
			max = Math.max(max, acc[N-1] - acc[0] - arr[i] + acc[i] - acc[0]);
		}

		// 꿀통: 가운데
		for(int i=1; i<N-1; i++) {
			max = Math.max(max, acc[i + 1] - acc[1] + acc[N - 1] - acc[i]);
		}

		System.out.println(max);
	}
}
