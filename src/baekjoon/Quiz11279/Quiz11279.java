package baekjoon.Quiz11279;

import java.util.*;
import java.io.*;

public class Quiz11279 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		PriorityQueue<Integer> q = new PriorityQueue<>((o1, o2) -> o2 - o1);
		StringBuilder sb = new StringBuilder();
		int N = Integer.parseInt(br.readLine());
		for(int i=0; i<N; i++) {
			int target = Integer.parseInt(br.readLine());
			if(target == 0) {
				if(!q.isEmpty()) sb.append(q.poll()).append("\n");
				else sb.append(0).append("\n");
			} else {
				q.offer(target);
			}
		}
		System.out.println(sb);
	}
}
