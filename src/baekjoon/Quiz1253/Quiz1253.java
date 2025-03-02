package baekjoon.Quiz1253;

import java.io.*;
import java.util.*;

/*
Gold4: 좋다 / [sort, two-pointer]
1. 모든 수를 순회하며 좋은 수인지 판별한다.
2. 양쪽 끝에서 합을 구해나간다.
2-1. 판별 대상은 제외한다.
2-2. 합이 크다면 r을 감소시킨다.
2-3. 합이 작다면 l을 증가시킨다.
*/
public class Quiz1253 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[] A = new int[N];
		String[] numbers = br.readLine().split(" ");
		for(int i=0; i<N; i++) {
			A[i] = Integer.parseInt(numbers[i]);
		}

		Arrays.sort(A);

		int count = 0;

		for(int i=0; i<N; i++) {
			if(isGoodNumber(A, i)) {
				count++;
			}
		}

		System.out.println(count);
	}

	private static boolean isGoodNumber(int[] A, int idx) {
		int target = A[idx];
		int l = 0;
		int r = A.length - 1;

		while (l < r) {
			if (l == idx) {
				l++;
				continue;
			}
			if (r == idx) {
				r--;
				continue;
			}

			int sum = A[l] + A[r];
			if (sum == target) {
				return true;
			} else if (sum < target) {
				l++;
			} else {
				r--;
			}
		}
		return false;
	}
}
