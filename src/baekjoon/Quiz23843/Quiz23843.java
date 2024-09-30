package baekjoon.Quiz23843;

import java.io.*;
import java.util.*;

public class Quiz23843 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		Integer[] arr = new Integer[N];
		PriorityQueue<Integer> pq = new PriorityQueue<>(M);

		st = new StringTokenizer(br.readLine());
		for(int i=0; i<N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		Arrays.sort(arr, (o1, o2) -> o2 - o1);

		for(int i=0; i<M; i++) {
			if(i >= N) break;
			pq.offer(arr[i]);
		}

		for(int i=M; i<N; i++) {
			pq.offer(pq.poll() + arr[i]);
		}

		while(pq.size() != 1) {
			pq.poll();
		}

		System.out.println(pq.poll());
	}
}
