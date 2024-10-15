package baekjoon.Quiz25556;

import java.io.*;
import java.util.*;

public class Quiz25556 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[] arr = new int[N];
		int[] stacks = new int[4];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i=0; i<N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}

		// 1. 4개의 스택 중, 현재 숫자보다 작고 차이가 가장 적은 곳에 추가
		// 2. 현재 숫자보다 작은 곳이 없다면 NO
		stacks[0] = arr[0];
		for(int i=1; i<N; i++) {
			int min = 100001;
			int minIdx = -1;
			for(int j=0; j<4; j++) {
				if(stacks[j] < arr[i]) {
					if(min > arr[i] - stacks[j]) {
						min = arr[i] - stacks[j];
						minIdx = j;
					}
				}
			}
			if(minIdx == -1) {
				System.out.println("NO");
				return;
			}
			stacks[minIdx] = arr[i];
		}
		System.out.println("YES");
	}
}
