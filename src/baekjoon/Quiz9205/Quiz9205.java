package baekjoon.Quiz9205;

import java.util.*;
import java.io.*;

public class Quiz9205 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int t = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		while(t-- > 0) {
			int n = Integer.parseInt(br.readLine());
			List<Point> list = new ArrayList<>();
			for(int i=0; i<n+2; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				list.add(new Point(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
			}
			List<List<Integer>> aj = new ArrayList<>();
			for(int i=0; i<n+2; i++) {
				aj.add(new ArrayList<>());
			}
			for(int i=0; i<n+1; i++) {
				Point base = list.get(i);
				for(int j=i+1; j<n+2; j++) {
					Point pair = list.get(j);
					if(Math.abs(base.y - pair.y) + Math.abs(base.x - pair.x) <= 1000) {
						aj.get(i).add(j);
						aj.get(j).add(i);
					}
				}
			}
			//bfs
			boolean[] visit = new boolean[n+2];
			ArrayDeque<Integer> q = new ArrayDeque<>();
			q.addLast(0);
			while(!q.isEmpty()) {
				int now = q.removeFirst();
				if(visit[now]) continue;
				visit[now] = true;
				for(int ne : aj.get(now)) {
					q.addLast(ne);
				}
			}
			if(visit[n+1]) sb.append("happy").append("\n");
			else sb.append("sad").append("\n");
		}
		System.out.print(sb);
	}
	static class Point {
		int y, x;
		public Point(int y, int x) {
			this.y = y;
			this.x = x;
		}
	}
}
