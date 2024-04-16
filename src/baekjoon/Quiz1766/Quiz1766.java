package baekjoon.Quiz1766;

import java.util.*;
import java.io.*;

public class Quiz1766 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		List<Integer>[] list = new List[N+1];
		for(int i=0; i<=N; i++) {
			list[i] = new ArrayList<>();
		}
		int[] topo = new int[N+1];
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			int pre = Integer.parseInt(st.nextToken());
			int ne = Integer.parseInt(st.nextToken());
			list[pre].add(ne);
			topo[ne]++;
		}
		PriorityQueue<Integer> q = new PriorityQueue<>();
		for(int i=1; i<=N; i++) {
			if(topo[i] == 0) q.offer(i);
		}
		StringBuilder sb = new StringBuilder();
		while(!q.isEmpty()) {
			int c = q.poll();
			sb.append(c).append(" ");
			for(int ne : list[c]) {
				topo[ne]--;
				if(topo[ne] == 0) q.offer(ne);
			}
		}
		System.out.println(sb);
	}
}
