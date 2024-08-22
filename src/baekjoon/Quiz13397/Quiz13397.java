package baekjoon.Quiz13397;

import java.util.*;
import java.io.*;

// 시간 복잡도: Nlog(N)
public class Quiz13397 {
	static int N, M;
	static int[] arr;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		arr = new int[N];
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<arr.length; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}

		// mid: 모든 구간 점수의 최솟값
		int l = 0;
		int r = 10000;
		int mid;
		int answer = 0;
		while(l <= r) {
			mid = (l + r) / 2;
			if(countPartitionBy(mid) <= M) {
				r = mid - 1;
				answer = mid;
			} else {
				l = mid + 1;
			}
		}
		System.out.println(answer);
	}

	private static int countPartitionBy(int target) {
		// min, max: 구간 내 최댓값과 최솟값
		int min = 10000;
		int max = 1;
		int cnt = 0;
		for(int i=0; i<arr.length; i++) {
			max = Math.max(max, arr[i]);
			min = Math.min(min, arr[i]);
			if(max - min > target) {
				cnt++;
				min = arr[i];
				max = arr[i];
			}
		}
		return cnt + 1;
	}
}
