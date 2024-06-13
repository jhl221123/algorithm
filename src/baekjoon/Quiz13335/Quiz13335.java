package baekjoon.Quiz13335;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Quiz13335 {
	static int N, W, L, totalTime, passed, weightOnBridge, truckIdx;
	static List<Truck> bridge;
	static Truck[] trucks;
	public static void main(String[] args) throws IOException {
		input();
		measureTruckMoveTime();
		System.out.println(totalTime);
	}

	// 모든 트럭이 다리를 건널 때까지 반복한다.
	// 새로운 트럭 입장 전, 다리를 건널 수 있는 트럭이 있는지 먼저 확인한다.
	// 새로운 트럭이 다리에 입장한다.
	// 다리 위의 트럭들이 한 칸씩 이동한다.
	// 1초 증가한다.
	private static void measureTruckMoveTime() {
		while(passed<N) {
			passBridge();
			enterBridge();
			moveTruckOnBridge();
			totalTime++;
		}
	}

	// 현재 다리에 트럭이 하나라도 있고, 다리 길이만큼 이동했다면 다리를 벗어난다.
	// 다리 위의 모든 트럭 무게에서 지금 벗어나는 트럭의 무게를 제거한다.
	// 다리에서 트럭을 제거한다.
	// 통과한 트럭 수가 증가한다.
	private static void passBridge() {
		if(!bridge.isEmpty() && bridge.get(0).getDist() == W) {
			weightOnBridge -= bridge.get(0).getWeight();
			bridge.remove(0);
			passed++;
		}
	}

	// 아직 다리에 진입하지 못한 트럭이 남아있고, 다리위 트럭의 총 무게가 L을 넘지 않는다면 새로운 트럭이 입장한다.
	// 다리 위의 총 트럭 무게에 방금 입장한 트럭의 무게를 더한다.
	// 다리에 새로운 트럭에 입장한다.
	private static void enterBridge() {
		if(truckIdx < N && weightOnBridge + trucks[truckIdx].getWeight() <= L) {
			weightOnBridge += trucks[truckIdx].getWeight();
			bridge.add(trucks[truckIdx++]);
		}
	}

	private static void moveTruckOnBridge() {
		for(Truck truck : bridge) {
			truck.move();
		}
	}

	private static void input() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		W = Integer.parseInt(st.nextToken());
		L = Integer.parseInt(st.nextToken());
		bridge = new ArrayList<>();
		trucks = new Truck[N];
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<N; i++) {
			trucks[i] = new Truck(Integer.parseInt(st.nextToken()), 0);
		}
	}

	static class Truck {
		private int weight, dist;

		public Truck(int weight, int dist) {
			this.weight = weight;
			this.dist = dist;
		}

		public void move() {
			this.dist++;
		}

		public int getWeight() {
			return this.weight;
		}

		public int getDist() {
			return this.dist;
		}
	}
}
