package baekjoon.Quiz1826;

import java.util.*;
import java.io.*;

public class Quiz1826 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;

		// 주유소 입력
		int N = Integer.parseInt(br.readLine());
		GasStation[] stations = new GasStation[N];
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			stations[i] = new GasStation(a, b);
		}

		Arrays.sort(stations, Comparator.comparingInt(o -> o.a));

		// 마을 위치와 현재 연료 입력
		st = new StringTokenizer(br.readLine());
		int L = Integer.parseInt(st.nextToken());
		int P = Integer.parseInt(st.nextToken());

		int e = P;
		int ne = P;
		int idx = 0;
		int cnt = 0;
		PriorityQueue<Integer> priorStations = new PriorityQueue<>((o1, o2) -> o2 - o1);
		while(true) {
			// 마을에 도착 가능한 상태라면 종료
			if(ne >= L) break;

			// 가장 멀리 이동 가능한 주유소를 탐색
			while (idx < N && stations[idx].a <= e) {
				if(ne > e + stations[idx].b) ne = e + stations[idx].b;
				else priorStations.offer(stations[idx].b);
				idx++;
			}

			// 도중에 연료를 넣지 못한 경우
			if(ne == e) {
				// 지나온 주유소 중 채울 수 있는 곳이 있었다면 해당 주유소를 방문처리
				if(!priorStations.isEmpty()) {
					Integer prior = priorStations.poll();
					e += prior;
					ne += prior;
					cnt++;
					continue;
				}
				// 지나오 주유소가 없거나 방문해도 마을에 도착하지 못한다면 종료
				cnt = -1;
				break;
			}

			// 이동 가능한 거리를 갱신
			e = ne;
			cnt++;
		}

		System.out.println(cnt);
	}

	static class GasStation {
		int a, b;

		public GasStation(int a, int b) {
			this.a = a;
			this.b = b;
		}
	}
}
