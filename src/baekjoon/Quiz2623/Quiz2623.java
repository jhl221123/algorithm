package baekjoon.Quiz2623;

import java.util.*;
import java.io.*;

public class Quiz2623 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());

		List<Integer>[] list = new List[N+1];
		for(int i=0; i<=N; i++) {
			list[i] = new ArrayList<>();
		}
		int[] dist = new int[N+1];
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			int cnt = Integer.parseInt(st.nextToken());
			int pre = Integer.parseInt(st.nextToken());
			int ne = Integer.parseInt(st.nextToken());
			list[pre].add(ne);
			dist[ne]++;
			for(int j=1; j<=cnt-2; j++) {
				pre = ne;
				ne = Integer.parseInt(st.nextToken());
				list[pre].add(ne);
				dist[ne]++;
			}
		}
		ArrayDeque<Integer> ad = new ArrayDeque<>();
		boolean[] ck = new boolean[N+1];
		for(int i=1; i<=N; i++) {
			if(dist[i]==0) ad.addLast(i);
		}

		StringBuilder sb = new StringBuilder();
		while(!ad.isEmpty()) {
			int c = ad.removeFirst();
			ck[c] = true;
			sb.append(c).append("\n");
			for(int ne : list[c]) {
				dist[ne]--;
				if(dist[ne] == 0) ad.addLast(ne);
			}
		}
		for(int i=1; i<=N; i++) {
			if(!ck[i]) {
				sb = new StringBuilder().append(0);
				break;
			}
		}
		System.out.print(sb);
	}
}
