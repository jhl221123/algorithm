package baekjoon.Quiz2110;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// 전체 시간 복잡도: O(N*logX), X: 10^9
public class Quiz2110 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int C = Integer.parseInt(st.nextToken());
		Integer[] arr = new Integer[N];
		// 집 주소 입력, O(N)
		for(int i=0; i<N; i++) {
			arr[i] = Integer.parseInt(br.readLine());
		}
		// 주소 정렬, Tim Sort: O(NlogN)
		Arrays.sort(arr);
		// 이분탐색으로 최적의 거리를 찾는다., O(NlogX)
		int a = 0;
		int b = 1000000000;
		int ans = 0;
		while(a<=b) {
			int m = (a+b)/2;
			// 가장 인접한 거리가 m이 되는지 확인, O(logX)
			if(isPossible(arr, m, C)) {
				ans = m;
				a = m+1;
			} else b = m-1;
		}
		System.out.println(ans);
	}
	static boolean isPossible(Integer[] arr, int m, int C) {
		// 정렬된 집 배열을 돌면서 m보다 길이가 커지면 공유기 설치, O(logX)
		int current = arr[0];
		C--;
		for(int i=1; i<arr.length; i++) {
			if(arr[i] - current >= m) {
				C--;
				current = arr[i];
			}
		}
		return C <= 0;
	}
}
