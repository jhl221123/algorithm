package baekjoon.Quiz2805;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 전체 시간 복잡도: O(N*logH)
public class Quiz2805 {
	// 절단기로 자른 나무들의 합 계산, O(N)
	static long getSomeTrees(int[] arr, int H) {
		long sum = 0;
		for(int i=0; i<arr.length; i++) {
			if(H<arr[i]) sum += arr[i]-H;
		}
		return sum;
	}
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int[] trees = new int[N];

		// 나무들 길이 입력, O(N)
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<N; i++) {
			trees[i] = Integer.parseInt(st.nextToken());
		}
		// 절단기 높이 5억부터 이진탐색, O(N*logH)
		int a = 0;
		int b = 1000000000;
		int ans = 0;
		while(a<=b) {
			int m = (a+b)/2;
			// 절단기 높이에 따른 나무 길이 합 반환, O(N)
			if(getSomeTrees(trees, m)<M) b = m-1;
			else {
				a = m+1;
				ans = m;
			}
		}
		// M을 처음 초과하는 값을 반환
		System.out.println(b);
	}
}
