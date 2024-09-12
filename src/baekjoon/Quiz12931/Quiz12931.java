package baekjoon.Quiz12931;

import java.util.*;
import java.io.*;

public class Quiz12931 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[] arr = new int[N];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i=0; i<N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}

		int cnt = 0;
		while(true) {
			boolean isEnd = true;
			for(int i=0; i<N; i++) {
				if(arr[i] % 2 != 0) {
					arr[i] -= 1;
					cnt++;
				}
				if(arr[i] != 0) isEnd = false;
			}

			if(isEnd) break;

			for(int i=0; i<N; i++) {
				arr[i] /= 2;
			}
			cnt++;
		}

		System.out.println(cnt);
	}
}
