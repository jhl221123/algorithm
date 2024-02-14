package baekjoon.Quiz11286;

import java.util.*;
import java.io.*;
public class Quiz11286 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		StringBuilder sb= new StringBuilder();
		PriorityQueue<Integer> pq = new PriorityQueue<>((o1, o2) -> {
			if(Math.abs(o1) == Math.abs(o2)) return o1 - o2;
			return Math.abs(o1) - Math.abs(o2);
		});
		while(N-- > 0) {
			int cmd = Integer.parseInt(br.readLine());
			if(cmd == 0) {
				if(pq.isEmpty()) sb.append(0 + "\n");
				else sb.append(pq.poll()).append("\n");
			} else pq.offer(cmd);
		}
		System.out.println(sb);
	}
}
