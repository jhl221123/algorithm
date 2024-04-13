package baekjoon.Quiz1202;

import java.util.*;
import java.io.*;

public class Quiz1202 {
	static TreeMap<Integer, Integer> bags = new TreeMap<>();
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		Gold[] golds = new Gold[N];
		Integer[] bagSize = new Integer[K];

		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			golds[i] = new Gold(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), 0);
		}
		for(int i=0; i<K; i++) {
			bagSize[i] = Integer.parseInt(br.readLine());
		}
		Arrays.sort(golds, (g1, g2) -> g2.V - g1.V);
		Arrays.sort(bagSize, Comparator.comparingInt(b -> b));

		for(int i=0; i<K; i++) {
			bags.put(bagSize[i], bags.getOrDefault(bagSize[i], 0)+1);
		}

		long sum = 0L;
		for(int i=0; i<N; i++) {
			if(isInputBag(golds[i].M)) sum+=golds[i].V;
			if(bags.isEmpty()) break;
		}
		System.out.println(sum);
	}
	static boolean isInputBag(int M) {
		// list에서 M보다 한칸 큰 가방 제거
		Map.Entry<Integer, Integer> entry = bags.ceilingEntry(M);
		if(entry == null) return false;
		else {
			if(entry.getValue() > 1) bags.put(entry.getKey(), entry.getValue() - 1);
			else bags.remove(entry.getKey());
			return true;
		}
	}
	static class Gold {
		int M, V, idx;
		public Gold(int M, int V, int idx) {
			this.M = M;
			this.V = V;
			this.idx = idx;
		}
	}
}
