package baekjoon.Quiz2458;

import java.util.*;
import java.io.*;

public class Quiz2458 {
	static int N, M;
	static List<List<Integer>> list;
	static int[] topo;
	static ArrayDeque<Integer> ad = new ArrayDeque<>();
	static List<Integer> order;
	static int[] rankArr;
	static int[] cnt;
	static int max;
	static int ans;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		list = new ArrayList<>();
		for(int i=0; i<=N; i++) {
			list.add(new ArrayList<>());
		}
		topo = new int[N+1];
		rankArr = new int[N+1];
		cnt = new int[N+1];
		order = new ArrayList<>();
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			int pre = Integer.parseInt(st.nextToken());
			int ne = Integer.parseInt(st.nextToken());
			list.get(pre).add(ne);
			topo[ne]++;
		}
		// 진입차수 0인 노드 큐에 추가
		for(int i=1; i<=N; i++) {
			if(topo[i]==0) {
				order.add(i);
				rankArr[i] = 0;
				ad.addLast(i);
			}
		}
		initRank();
		initCnt();
		checkRank();
		System.out.println(ans);
	}
	private static void initRank() {
		int rank = 1;
		while(!ad.isEmpty()) {
			int pre = ad.removeFirst();
			for(int ne : list.get(pre)) {
				topo[ne]--;
				if(topo[ne] == 0) {
					order.add(ne);
					rankArr[ne] = rank;
					ad.add(ne);
				}
			}
			rank++;
		}
	}
	private static void initCnt() {
		for(int i=1; i<=N; i++) {
			cnt[rankArr[i]]++;
		}
	}
	private static void checkRank() {
		for(int i : order) {
			if(cnt[rankArr[i]] <= 1 && rankArr[i] >= max) ans++;
			int adjMin = Integer.MAX_VALUE;
			for(int ne : list.get(i)) {
				adjMin = Math.min(adjMin, rankArr[ne]);
			}
			max = Math.max(max, adjMin);
		}
	}
}
