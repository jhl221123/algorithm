package baekjoon.Quiz17179;

import java.util.*;
import java.io.*;

public class Quiz17179 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int L = Integer.parseInt(st.nextToken());
		int[] arr = new int[M+1];
		for(int i=0; i<M; i++) {
			arr[i] = Integer.parseInt(br.readLine());
		}
		// 마지막 케익 조각 크기도 고려해야 하기 때문
		arr[M] = L;

		StringBuilder sb = new StringBuilder();
		while(N-- > 0) {
			int K = Integer.parseInt(br.readLine());
			int l = 0;
			int r = L;
			int answer = 0;
			while(l <= r) {
				int m = (l + r) / 2;
				int cnt = divideBy(m, arr);
				if(cnt >= K){
					answer = m;
					l = m + 1;
				} else {
					r = m - 1;
				}
			}
			sb.append(answer).append("\n");
		}
		System.out.print(sb);
	}

	private static int divideBy(int m, int[] arr) {
		int cnt = 0;
		int prior = 0;
		// 마지막 조각도 m 보다 큰 것을 확인해야 한다.
		for(int i=0; i<arr.length; i++) {
			if(arr[i] - prior >= m) {
				cnt++;
				prior = arr[i];
			}
		}
		// 마지막 조각이 m 보다 크거나 같다면, 현재 m은 정답 후보가 된다. 대신 자른 횟수는 1 감소 시켜야 한다.
		// 마지막 조각이 m 보다 작은 경우, m보다 최소 크기의 케익이 있다는 것. 따라서 m을 감소시키기 위해 횟수를 1 감소
		// 위 두 가지 케이스를 모두 고려했을 때, 항상 cnt를 1 감소시켜야 한다.
		return cnt-1;
	}
}
