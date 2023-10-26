package baekjoon.Quiz1806;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 전체 시간 복잡도: O(N)
public class Quiz1806 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int S = Integer.parseInt(st.nextToken());
		int[] arr = new int[N];
		st = new StringTokenizer(br.readLine());
		// 수열 입력, O(N)
		for(int i=0; i<N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		// 0.포인터가 같은 곳을 가리키는 경우 부분합을 2로 나눈다.
		// 1.부분합이 S보다 크거나 같은 경우
		// 1-1.플래그를 확인하고 최소길이를 갱신한다.
		// 2.부분합이 S보다 작은 경우
		// 2-1.i를 올린다.
		int j = 0;
		int sum = arr[0];
		int minLength = N+1;
		// O(N)
		for(int i=0; i<N; i++) {
			while(sum<S && j<N-1) {
				sum += arr[++j];
			}
			if(sum>=S) minLength = Math.min(minLength, j-i+1);
			sum -= arr[i];
		}
		System.out.println(minLength > N ? 0 : minLength);
	}
}
