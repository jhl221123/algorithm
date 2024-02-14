package baekjoon.Quiz2606;

import java.util.*;

public class Quiz2606 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int M = sc.nextInt();
		List<Integer>[] graph = new List[N+1];
		for(int i=0; i<=N; i++) {
			graph[i] = new ArrayList<>();
		}
		for(int i=0; i<M; i++) {
			int l = sc.nextInt();
			int r = sc.nextInt();
			graph[l].add(r);
			graph[r].add(l);
		}
		ArrayDeque<Integer> ad = new ArrayDeque<>();
		int[] visit = new int[N+1];
		ad.addLast(1);
		visit[1] = 1;
		while(!ad.isEmpty()) {
			int point = ad.removeFirst();
			for(int target : graph[point]) {
				if(visit[target] == 0) {
					visit[target] = visit[point] + 1;
					ad.addLast(target);
				}
			}
		}
		int cnt = 0;
		for(int i=1; i<=N; i++) {
			if(visit[i]>0) cnt++;
		}
		System.out.println(cnt-1);
	}
}
