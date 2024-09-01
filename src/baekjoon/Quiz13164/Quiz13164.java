package baekjoon.Quiz13164;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Quiz13164 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		int[] height = new int[N];
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<N; i++) {
			height[i] = Integer.parseInt(st.nextToken());
		}

		PriorityQueue<Integer> pq = new PriorityQueue<>((o1, o2) -> o2 - o1);
		for(int i=1; i<N; i++) {
			pq.offer(height[i] - height[i-1]);
		}

		while(K-- > 1) {
			pq.poll();
		}

		int sum = 0;
		while(!pq.isEmpty()) {
			sum += pq.poll();
		}

		System.out.println(sum);
	}
}
