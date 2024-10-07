package baekjoon.Quiz1715;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

public class Quiz1715 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		PriorityQueue<Integer> pq = new PriorityQueue<>();
		while(N-- > 0) {
			pq.offer(Integer.parseInt(br.readLine()));
		}

		int sum = 0;
		while(pq.size() > 1) {
			int next = pq.poll() + pq.poll();
			sum += next;
			pq.offer(next);
		}

		System.out.println(sum);
	}
}