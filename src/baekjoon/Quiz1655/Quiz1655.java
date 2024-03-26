package baekjoon.Quiz1655;

import java.util.*;
import java.io.*;

public class Quiz1655 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		PriorityQueue<Integer> lq = new PriorityQueue<>((o1, o2) -> o2 - o1);
		PriorityQueue<Integer> rq = new PriorityQueue<>((o1, o2) -> o1 - o2);
		StringBuilder sb = new StringBuilder();
		int N = Integer.parseInt(br.readLine());
		for(int i=0; i<N; i++) {
			int lt = lq.isEmpty() ? 0 : lq.peek();
			int rt = rq.isEmpty() ? 0 : rq.peek();
			int target = Integer.parseInt(br.readLine());
			if(lq.isEmpty() || target <= lt) lq.offer(target);
			else if(rq.isEmpty() || target >= rt) rq.offer(target);
			else { // 사이값
				if(lq.size() <= rq.size()) lq.offer(target);
				else rq.offer(target);
			}
			// 개수 조정
			if(lq.size() - rq.size() >=2) rq.offer(lq.poll());
			else if(rq.size() - lq.size() >=2) lq.offer(rq.poll());
			// 값 출력
			if(lq.size() >= rq.size()) sb.append(lq.peek()).append("\n");
			else sb.append(rq.peek()).append("\n");
		}
		System.out.println(sb);
	}
}
