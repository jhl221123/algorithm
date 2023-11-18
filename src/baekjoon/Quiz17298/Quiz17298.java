package baekjoon.Quiz17298;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

// 전체 시간 복잡도: O(N)
public class Quiz17298 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());
		int[] arr = new int[N];
		for(int i=0; i<N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		ArrayDeque<Integer> ad = new ArrayDeque<>();
		for(int i=0; i<N; i++) {
			while(!ad.isEmpty() && arr[ad.peek()] < arr[i]) {
				arr[ad.pop()] = arr[i];
			}
			ad.push(i);
		}
		while(!ad.isEmpty()) {
			arr[ad.pop()] = -1;
		}
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		for(int i=0; i<N; i++) {
			bw.write(arr[i] + " ");
		}
		bw.flush();
	}
}
