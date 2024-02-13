package baekjoon.Quiz1697;

import java.util.*;

public class Quiz1697 {
	static int[] dx = {-1, 1, 2};
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int M = sc.nextInt();
		int[] visit = new int[100001];
		ArrayDeque<Integer> ad = new ArrayDeque<>();
		ad.addLast(N);
		visit[N]++;
		while(!ad.isEmpty()) {
			int point = ad.removeFirst();
			for(int i=0; i<3; i++) {
				int mp = dx[i] == 2 ? point * 2 : point + dx[i];
				if(mp < 0 || mp > 100000) continue;
				if(visit[mp] == 0) {
					visit[mp] = visit[point]+1;
					ad.addLast(mp);
				}
				if(mp == M) {
					System.out.println(visit[mp]-1);
					return;
				}
			}
		}
	}
}
