package baekjoon.Quiz14921;

import java.util.*;
import java.io.*;

/*
Gold5: 용액 합성하기 / [Two Pointer]
1. 양끝 지점에서 가운데로 이동하며 0에 가장 가까운 합을 찾는다.
2. 현재 두 용액의 합이 0에 제일 가까운지 확인한 후, r과 l을 조정한다.
2-1. 두 수의 합이 양수라면 r--
2-2. 두 수의 합이 음수라면 l++
*/
public class Quiz14921 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[] arr = new int[N];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i=0; i<N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}

		int l = 0;
		int r = N - 1;
		int ans = arr[l] + arr[r];
		while(l < r) {
			int sum = arr[l] + arr[r];
			if(Math.abs(sum) <= Math.abs(ans)) {
				ans = sum;
			}

			if(sum > 0) {
				r--;
			} else {
				l++;
			}
		}

		System.out.println(ans);
	}
}
