package baekjoon.Quiz11728;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

// 전체 시간 복잡도: O(N+M)
public class Quiz11728 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		// A, B배열 입력, O(N)
		int[] A = getArr(N, new StringTokenizer(br.readLine()));
		int[] B = getArr(M, new StringTokenizer(br.readLine()));
		// A의 원소가 B의 원소보다 크거나 같다면 B의 원소를 입력
		int[] ans = new int[N+M];
		int idx = 0;
		int bIdx = 0;
		for(int i=0; i<N; i++) {
			while(bIdx<M && A[i]>=B[bIdx]) {
				ans[idx++] = B[bIdx++];
			}
			// 반대라면 A의 원소를 입력
			ans[idx++] = A[i];
		}
		for(int i=bIdx; i<M; i++) {
			ans[idx++] = B[i];
		}
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		for(int i=0; i<N+M; i++) {
			bw.write(ans[i] + " ");
		}
		bw.flush();
	}

	static int[] getArr(int length, StringTokenizer numbers) {
		int[] arr = new int[length];
		for(int i=0; i<length; i++) {
			arr[i] = Integer.parseInt(numbers.nextToken());
		}
		return arr;
	}
}
