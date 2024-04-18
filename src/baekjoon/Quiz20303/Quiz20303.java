package baekjoon.Quiz20303;

import java.util.*;
import java.io.*;

public class Quiz20303 {
	static int N, M, K;
	static List<List<Integer>> list;
	static boolean[] visit;
	static int[] candy;
	static List<Group> groups;
	static int[] dp;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		candy = new int[N+1];
		visit = new boolean[N+1];
		list = new ArrayList<>();
		groups = new ArrayList<>();
		dp = new int[K+1];
		// 사탕 입력
		st = new StringTokenizer(br.readLine());
		for(int i=1; i<=N; i++) {
			candy[i] = Integer.parseInt(st.nextToken());
		}
		// 친구 관계 입력
		for(int i=0; i<=N; i++) {
			list.add(new ArrayList<>());
		}
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			list.get(from).add(to);
			list.get(to).add(from);
		}
		// 그룹화
		separateGroup();
		// 배낭
		knapsack();
		System.out.println(dp[K-1]);
	}
	static void separateGroup() {
		ArrayDeque<Integer> ad = new ArrayDeque<>();
		for(int i=1; i<=N; i++) {
			if(visit[i]) continue;
			ad.addLast(i);
			int size = 0;
			int value = 0;
			while(!ad.isEmpty()) {
				int c = ad.removeFirst();
				if(visit[c]) continue;
				visit[c] = true;
				size++;
				value += candy[c];
				for(int ne : list.get(c)) {
					if(visit[ne]) continue;
					ad.addLast(ne);
				}
			}
			groups.add(new Group(size, value));
			//            System.out.println("size:value = " + size + ":" + value);
		}
	}
	static void knapsack() {
		for(Group group : groups) {
			int s = group.size;
			int v = group.value;
			for(int i=K; i>=s; i--) {
				dp[i] = Math.max(dp[i], dp[i-s]+v);
			}
		}
	}
	static class Group {
		int size, value;
		public Group(int size, int value) {
			this.size = size;
			this.value = value;
		}
	}
}
