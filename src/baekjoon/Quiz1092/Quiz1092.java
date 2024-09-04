package baekjoon.Quiz1092;

import java.io.*;
import java.util.*;

public class Quiz1092 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		Integer[] cranes = new Integer[N];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i=0; i<N; i++) {
			cranes[i] = Integer.parseInt(st.nextToken());
		}
		Arrays.sort(cranes, (o1, o2) -> o2 - o1);
		int M = Integer.parseInt(br.readLine());
		List<Integer> boxes = new ArrayList<>();
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<M; i++) {
			boxes.add(Integer.parseInt(st.nextToken()));
		}

		boxes.sort((o1, o2) -> o2 - o1);

		if (boxes.get(0) > cranes[0]) {
			System.out.println(-1);
		} else {
			int time = 0;
			while (!boxes.isEmpty()) {
				time++;
				for (int i = 0; i < N; ++i) {
					for (int j = 0; j < boxes.size(); ++j) {
						if (cranes[i] >= boxes.get(j)) {
							boxes.remove(j);
							break;
						}
					}
				}
			}
			System.out.println(time);
		}
	}
}
