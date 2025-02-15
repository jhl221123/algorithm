package baekjoon.Quiz2251;

import java.util.*;
import java.io.*;

/*
1. 세 가지 물통 중 물이 있는 곳은 나머지 물통에 옮기며 bfs 탐색한다.
2. 물통에 존재하는 물 비율이 이미 발견되었다면 패스한다.
3. A가 0일 때, C의 물 양을 기록한다.
*/
public class Quiz2251 {

	public static void main(String[] args) throws IOException {
		Bundle bundle = initializeBundle();
		System.out.println(findAllPossibleWaters(bundle, 0, 2));
	}

	private static Bundle initializeBundle() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int bottleCount = st.countTokens();

		Bundle bundle = new Bundle();
		for(int i = 0; i < bottleCount; i++) {
			bundle.add(new Bottle(i, Integer.parseInt(st.nextToken())));
		}

		return bundle;
	}

	private static String findAllPossibleWaters(Bundle initial, int emptyBottleId, int targetBottlId) {
		int bundleSize = initial.getSize();
		Set<String> ratios = new HashSet<>();
		Set<Integer> possibleWaters = new HashSet<>();

		ArrayDeque<Bundle> bundleQueue = new ArrayDeque<>();
		bundleQueue.addLast(initial);
		while(!bundleQueue.isEmpty()) {
			Bundle bundle = bundleQueue.removeFirst();
			String ratio = bundle.getRatio();

			if(ratios.contains(ratio)) {
				continue;
			}
			ratios.add(ratio);

			if(bundle.getBottle(emptyBottleId).isEmpty()) {
				possibleWaters.add(bundle.getBottle(targetBottlId).getWater());
			}

			for(int from=0; from<bundleSize; from++) {
				for(int to=0; to<bundleSize; to++) {
					if(from == to) continue;

					Bundle clone = bundle.copy();
					clone.pour(from, to);
					if(ratios.contains(clone.getRatio())) {
						continue;
					}

					bundleQueue.addLast(clone);
				}
			}
		}

		return orderAscending(possibleWaters);
	}

	private static String orderAscending(Set<Integer> possibleWaters) {
		TreeSet<Integer> order = new TreeSet<>(possibleWaters);
		StringBuilder sb = new StringBuilder();

		for(Integer water : order) {
			sb.append(water).append(" ");
		}

		return sb.toString();
	}

	static class Bottle {
		private int id;
		private int volume;
		private int water;

		private Bottle(int id, int volume, int water) {
			this.id = id;
			this.volume = volume;
			this.water = water;
		}

		public Bottle(int id, int volume) {
			this.id = id;
			this.volume = volume;
			if(id == 2) {
				this.water = volume;
			} else {
				this.water = 0;
			}
		}

		public Bottle copy() {
			return new Bottle(this.id, this.volume, this.water);
		}

		public void pourTo(Bottle bottle) {
			if(this.water == 0) return;

			int possible = bottle.getVolume() - bottle.getWater();
			int pourWater = Math.min(this.water, possible);
			this.water -= pourWater;
			bottle.receive(pourWater);
		}

		public void receive(int increment) {
			this.water += increment;
		}

		public boolean isEmpty() {
			return this.water == 0;
		}

		public int getId() {
			return this.id;
		}

		public int getVolume() {
			return this.volume;
		}

		public int getWater() {
			return this.water;
		}
	}

	static class Bundle {

		private Map<Integer, Bottle> bottleMap;

		public Bundle() {
			this.bottleMap = new HashMap<>();
		}

		public Bundle copy() {
			Bundle clone = new Bundle();
			clone.add(bottleMap.get(0).copy());
			clone.add(bottleMap.get(1).copy());
			clone.add(bottleMap.get(2).copy());

			return clone;
		}

		public void add(Bottle bottle) {
			this.bottleMap.put(bottle.getId(), bottle);
		}

		public void pour(int from, int to) {
			bottleMap.get(from).pourTo(bottleMap.get(to));
		}

		public Bottle getBottle(int id) {
			return this.bottleMap.get(id);
		}

		public int getSize() {
			return this.bottleMap.size();
		}

		public String getRatio() {
			StringBuilder ratio = new StringBuilder();
			ratio.append(this.bottleMap.get(0).getWater()).append(" ");
			ratio.append(this.bottleMap.get(1).getWater()).append(" ");
			ratio.append(this.bottleMap.get(2).getWater());

			return ratio.toString();
		}
	}
}
