package baekjoon.Quiz19598;

import java.util.*;
import java.io.*;

public class Quiz19598 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt((int[] a) -> a[0]));
		PriorityQueue<int[]> q = new PriorityQueue<>(Comparator.comparingInt((int[] a) -> a[1]));
		int ans = 1;
		int cr = 1;
		StringTokenizer st = null;
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			pq.offer(new int[]{Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())});
		}
		while(!pq.isEmpty()) {
			int[] target = pq.poll();
			while(!q.isEmpty()) {
				int[] pe = q.peek();
				if(pe[1]<=target[0]) {
					q.poll();
					cr++;
				} else break;
			}
			if(cr>0) cr--;
			else ans++;
			q.offer(target);
		}
		System.out.println(ans);
	}
}
