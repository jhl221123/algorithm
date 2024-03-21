package baekjoon.Quiz2075;

import java.util.*;
import java.io.*;

public class Quiz2075 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		PriorityQueue<Integer> q = new PriorityQueue<>((o1, o2) -> o2 - o1);
		int N = Integer.parseInt(br.readLine());
		StringTokenizer st;
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<N; j++){
				q.offer(Integer.parseInt(st.nextToken()));
			}
		}
		for(int i=0; i<N-1; i++) {
			q.poll();
		}
		System.out.println(q.poll());
	}
}
