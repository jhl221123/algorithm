package baekjoon.Quiz17471;

import java.util.*;
import java.io.*;

public class Quiz17471 {
	// 구역과 인접 상태를 그래프로 관리
	// 지역구의 조건
	// 1. 지역구 내 모든 구역은 인접해야 한다.
	// 2. 인구수 차이가 최소가 되어야 한다.
	// 탐색 과정
	// 1. 10C1 ~ 10C5 까지 모든 경우의 수에 대해 두 구역으로 나뉘는 지 탐색 후 가능한 경우만 최소값 비교
	static int N;
	static int[] counts;
	static List<List<Integer>> list;
	static boolean[] visit;
	static int min = Integer.MAX_VALUE;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		counts = new int[N+1];
		list = new ArrayList<>();
		for(int i=0; i<=N; i++) {
			list.add(new ArrayList<>());
		}
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i=1; i<=N; i++) {
			counts[i] = Integer.parseInt(st.nextToken());
		}
		for(int i=1; i<=N; i++) {
			st = new StringTokenizer(br.readLine());
			int connect = Integer.parseInt(st.nextToken());
			for(int j=1; j<=connect; j++) {
				int other = Integer.parseInt(st.nextToken());
				list.get(i).add(other);
				list.get(other).add(i);
			}
		}
		// 지역들을 조합하고 지역구 생성 가능한지 확인
		visit = new boolean[N+1];
		subset(1);
		if(min == Integer.MAX_VALUE) min = -1;
		System.out.println(min);
	}
	private static void subset(int srcIdx) {
		if(srcIdx == N+1) {
			// 지역구 생성
			List<Integer> regionA = new ArrayList<>();
			List<Integer> regionB = new ArrayList<>();
			for(int i=1; i<=N; i++) {
				if(visit[i]) regionA.add(i);
				else regionB.add(i);
			}
			// 현재 지역들의 조합이 충족 -> 해당 조합으로 지역구 생성 가능한지 확인
			isPossible(regionA, regionB);
			return;
		}
		visit[srcIdx] = true;
		subset(srcIdx+1);
		visit[srcIdx] = false;
		subset(srcIdx + 1);
	}

	private static void isPossible(List<Integer> a, List<Integer> b) {
		// 각 지역구가 모두 연결되어있는지 확인
		if(check(a) && check(b)) {
			// 연결되어 있다면 최소값 갱신
			min = Math.min(min, Math.abs(count(a) - count(b)));
		}
		// 연결되어 있지 않다면 무시
	}
	private static boolean check(List<Integer> regionList) {
		if(regionList.isEmpty()) return false;
		ArrayDeque<Integer> ad = new ArrayDeque<>();
		boolean[] temp = new boolean[N+1];
		ad.addLast(regionList.get(0));
		temp[regionList.get(0)] = true;
		while(!ad.isEmpty()) {
			int next = ad.removeFirst();
			for(int region : list.get(next)) {
				if(!temp[region] && regionList.contains(region)) {
					temp[region] = true;
					ad.addLast(region);
				}
			}
		}
		for(int target : regionList) {
			if(!temp[target]) return false;
		}
		return true;
	}
	private static int count(List<Integer> list) {
		int sum = 0;
		for(int target : list) {
			sum += counts[target];
		}
		return sum;
	}
}
