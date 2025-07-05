package baekjoon.Quiz1949;

import java.util.*;
import java.io.*;

/*
Gold2: 우수 마을 / [dp, tree]
*/
public class Quiz1949 {

	private static int N;
	private static int[] villages;
	private static int[][] dp;
	private static List<List<Integer>> tree;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		villages = new int[N + 1];
		String[] people = br.readLine().split(" ");
		for(int i=1; i<=N; i++) {
			villages[i] = Integer.parseInt(people[i - 1]);
		}

		tree = new ArrayList<>();
		for(int i=0; i<=N; i++) {
			tree.add(new ArrayList<>());
		}
		for(int i=0; i<N-1; i++) {
			String[] edge = br.readLine().split(" ");
			int from = Integer.parseInt(edge[0]);
			int to = Integer.parseInt(edge[1]);
			tree.get(from).add(to);
			tree.get(to).add(from);
		}

		dp = new int[N + 1][2];
		dfs(1, 0);
		System.out.println(Math.max(dp[1][0], dp[1][1]));
	}

	private static void dfs(int current, int parent) {
		dp[current][1] += villages[current];
		List<Integer> children = tree.get(current);
		for(int i=0; i<children.size(); i++) {
			int child = children.get(i);
			if(child == parent) continue;
			dfs(child, current);
			dp[current][0] += Math.max(dp[child][0], dp[child][1]);
			dp[current][1] += dp[child][0];
		}
	}
}
