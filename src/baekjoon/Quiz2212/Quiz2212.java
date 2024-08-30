package baekjoon.Quiz2212;

import java.io.*;
import java.util.*;

public class Quiz2212 {
	static int N, K;
	static TreeSet<Integer> sensor;
	public static void main(String[] args) throws IOException {
		input();
		PriorityQueue<Integer> pq = inputDistanceBetweenSensor();
		removeTopKOf(pq);
		int minSum = sumDistance(pq);
		System.out.println(minSum);
	}

	private static int sumDistance(PriorityQueue<Integer> pq) {
		int answer = 0;
		while(!pq.isEmpty()) {
			answer += pq.poll();
		}
		return answer;
	}

	private static void removeTopKOf(PriorityQueue<Integer> pq) {
		int cnt = 1;
		while(cnt < K) {
			pq.poll();
			cnt++;
		}
	}

	private static PriorityQueue<Integer> inputDistanceBetweenSensor() {
		PriorityQueue<Integer> pq = new PriorityQueue<>((o1, o2) -> o2 - o1);
		Integer prior = sensor.pollFirst();
		Integer next;
		while(!sensor.isEmpty()) {
			next = sensor.pollFirst();
			pq.offer(prior - next);
			prior = next;
		}
		return pq;
	}

	private static void input() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		K = Integer.parseInt(br.readLine());
		sensor = new TreeSet<>((o1, o2) -> o2 - o1);
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i=0; i<N; i++) {
			sensor.add(Integer.parseInt(st.nextToken()));
		}
	}
}
