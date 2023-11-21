package baekjoon.Quiz11725;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

// 전체 시간 복잡도: O(N)
public class Quiz11725 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		List<Integer>[] list = new ArrayList[N+1];
		for(int i=0; i<N+1; i++) {
			list[i] = new ArrayList<>();
		}
		StringTokenizer st;
		for(int i=0; i<N-1; i++) {
			st = new StringTokenizer(br.readLine());
			int nodeA = Integer.parseInt(st.nextToken());
			int nodeB = Integer.parseInt(st.nextToken());
			list[nodeA].add(nodeB);
			list[nodeB].add(nodeA);
		}
		int[] parents = new int[N+1];
		boolean[] check = new boolean[N+1];
		find(list, parents, check, 1);
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		for(int i=2; i<=N; i++) {
			bw.write(parents[i] + "\n");
		}
		bw.flush();
	}
	private static void find(List<Integer>[] list, int[] parents, boolean[] check, int node) {
		check[node] = true;
		for(int i=0; i<list[node].size(); i++) {
			int child = list[node].get(i);
			if(!check[child]) {
				parents[child] = node;
				find(list, parents, check, child);
			}
		}
	}
}
