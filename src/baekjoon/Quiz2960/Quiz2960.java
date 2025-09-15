package baekjoon.Quiz2960;

import java.io.*;
import java.util.*;

/*
Silver4: 에라토스테네스의 체 / [math]
*/
public class Quiz2960 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] NK = br.readLine().split(" ");
		int N = Integer.parseInt(NK[0]);
		int K = Integer.parseInt(NK[1]);

		ArrayDeque<Integer> ad = new ArrayDeque<>();
		for(int i=2; i<=N; i++) {
			ad.addLast(i);
		}

		boolean[] visited = new boolean[N + 1];
		int count = 0;
		int ans = 0;
		bk: while(!ad.isEmpty()) {
			int num = ad.removeFirst();
			if(visited[num]) continue;

			for(int i=num; i<=N; i+=num) {
				if(visited[i]) continue;
				visited[i] = true;
				count++;
				if(count == K) {
					ans = i;
					break bk;
				}
			}
		}

		System.out.println(ans);
	}
}
