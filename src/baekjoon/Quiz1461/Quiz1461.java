package baekjoon.Quiz1461;

import java.io.*;
import java.util.*;

public class Quiz1461 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());

		PriorityQueue<Integer> right = new PriorityQueue<>((o1, o2) -> o2 - o1);
		PriorityQueue<Integer> left = new PriorityQueue<>();
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<N; i++) {
			int num = Integer.parseInt(st.nextToken());
			if(num > 0) right.offer(num);
			else left.offer(num);
		}

		int sum = 0;
		int r = 0;
		int l = 0;
		if(!right.isEmpty()) r = right.peek();
		if(!left.isEmpty()) l = Math.abs(left.peek());

		if(r > l) {
			sum += r;
			for(int i=0; i<M; i++) {
				if(!right.isEmpty()) right.poll();
			}
		} else if (l > r) {
			sum += l;
			for(int i=0; i<M; i++) {
				if(!left.isEmpty()) left.poll();
			}
		}

		while (!right.isEmpty() || !left.isEmpty()) {
			if (!right.isEmpty()) {
				r = right.poll();
				sum += (r * 2);
				for (int i = 0; i < M - 1; i++) {
					if (!right.isEmpty())
						right.poll();
				}
			}

			if (!left.isEmpty()) {
				l = Math.abs(left.poll());
				sum += (l * 2);
				for (int i = 0; i < M - 1; i++) {
					if (!left.isEmpty())
						left.poll();
				}
			}
		}

		System.out.println(sum);
	}
}
