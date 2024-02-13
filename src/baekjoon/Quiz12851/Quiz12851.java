package baekjoon.Quiz12851;

import java.util.*;
public class Quiz12851 {
	static int[] dx = {-1, 1, 2};
	static int[] visit;
	static int[] count;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int K = sc.nextInt();
		visit = new int[100001];
		count = new int[100001];

		ArrayDeque<Integer> ad = new ArrayDeque<>();
		ad.addLast(N);
		visit[N]++;
		count[N]++;
		while(!ad.isEmpty()) {
			int point = ad.removeFirst();
			for(int i=0; i<3; i++) {
				int mp = dx[i] == 2 ? point * dx[i] : point + dx[i];
				if(mp<0 || mp>100000) continue;
				if(visit[mp] == 0) {
					visit[mp] = visit[point] + 1;
					count[mp] = count[point];
					ad.addLast(mp);
				} else if(visit[mp] == visit[point] + 1) {
					count[mp] += count[point];
				}
			}
		}
		System.out.println(visit[K]-1);
		System.out.println(count[K]);
	}
}
