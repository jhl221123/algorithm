package swea.d3;

import java.util.*;

public class Quiz1228 {
	public static void main(String[] args) throws Exception {
		Scanner sc = new Scanner(System.in);
		int T;
		T=10;

		for(int test_case = 1; test_case <= T; test_case++) {
			int N = sc.nextInt();
			LinkedList<Integer> ad = new LinkedList<>();
			for(int i=0; i<N; i++) {
				ad.add(sc.nextInt());
			}
			int M = sc.nextInt();
			for(int i=0; i<M; i++) {
				String prefix = sc.next();
				int start = sc.nextInt();
				int C = sc.nextInt();
				for(int j=0; j<C; j++) {
					ad.add(start++, sc.nextInt());
				}
			}
			StringBuilder sb = new StringBuilder();
			sb.append("#").append(test_case).append(" ");
			for(int i=0; i<10; i++) {
				sb.append(ad.poll()).append(" ");
			}
			System.out.println(sb);
		}
	}
}
