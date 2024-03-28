package baekjoon.Quiz1005;

import java.util.*;
import java.io.*;

public class Quiz1005 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		while(T-- > 0) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			int K = Integer.parseInt(st.nextToken());
			Tower[] towers = new Tower[N+1];
			st = new StringTokenizer(br.readLine());
			for(int i=1; i<=N; i++) {
				int time = Integer.parseInt(st.nextToken());
				towers[i] = new Tower(time, time);
			}
			for(int i=0; i<K; i++) {
				st = new StringTokenizer(br.readLine());
				int pre = Integer.parseInt(st.nextToken());
				int ne = Integer.parseInt(st.nextToken());
				towers[pre].nextIdxs.add(ne);
				towers[ne].topo++;
			}
			ArrayDeque<Integer> ad = new ArrayDeque<>();
			for(int i=1; i<=N; i++) {
				if(towers[i].topo == 0) ad.addLast(i);
			}
			int W = Integer.parseInt(br.readLine());
			while(!ad.isEmpty()) {
				int c = ad.removeFirst(); // c에 위치한 건물은 이미 최대 시간을 가진다
				for(int ne : towers[c].nextIdxs) {
					towers[ne].topo--;
					towers[ne].total = Math.max(towers[ne].total, towers[c].total + towers[ne].time);
					if(towers[ne].topo == 0) ad.addLast(ne);
				}
			}
			sb.append(towers[W].total).append("\n");
		}
		System.out.println(sb);
	}
	static class Tower {
		int time, topo, total;
		List<Integer> nextIdxs = new ArrayList<>();
		public Tower(int time, int total) {
			this.time = time;
			this.total = total;
		}
	}
}
