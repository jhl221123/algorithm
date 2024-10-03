package baekjoon;

import java.io.*;
import java.util.*;

public class Quiz1083 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[] nums = new int[N];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i=0; i<N; i++) {
			nums[i] = Integer.parseInt(st.nextToken());
		}
		int S = Integer.parseInt(br.readLine());

		// 가장 높은 자리수부터 "이동 가능한 횟수 범위내 가장 큰 수와 교체"
		// 교체는 큰 수가 한 칸씩 앞으로 버블 정렬되도록 처리
		for (int i = 0; i < N; i++) {
			if (S == 0) break;
			int target = nums[i];
			int targetIdx = i;
			for(int j = i; j<= i + S; j++) {
				if(j>=N) break;
				if(target < nums[j]) {
					target = nums[j];
					targetIdx = j;
				}
			}
			if(i == targetIdx) continue;
			swap(nums, i, targetIdx);
			S -= (targetIdx - i);
		}

		StringBuilder sb = new StringBuilder();
		for(int i=0; i<N; i++) {
			sb.append(nums[i]).append(" ");
		}
		System.out.println(sb);
	}

	private static void swap(int[] nums, int from, int to) {
		for(int i=to; i>from; i--) {
			int temp = nums[i];
			nums[i] = nums[i-1];
			nums[i-1] = temp;
		}
	}
}
