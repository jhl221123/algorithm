package baekjoon.Quiz13975;

import java.io.*;
import java.util.*;

public class Quiz13975 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();

		while(T-- > 0) {
			int N = Integer.parseInt(br.readLine());
			PriorityQueue<Long> pq = new PriorityQueue<>();
			StringTokenizer st = new StringTokenizer(br.readLine());
			while(N-- > 0) {
				pq.offer(Long.parseLong(st.nextToken()));
			}

			long sum = 0;
			while(pq.size() > 1) {
				long next = pq.poll() + pq.poll();
				sum += next;
				pq.offer(next);
			}
			sb.append(sum).append("\n");
		}
		System.out.print(sb);
	}
}
