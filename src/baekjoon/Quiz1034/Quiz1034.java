package baekjoon.Quiz1034;

import java.util.*;
import java.io.*;

/*
Gold4: 램프 / [brute-force]
1. 진입 차수가 0인 작업부터 처리한다.
1-1. 작업에 소요된 시간을 최대 시간과 비교해 갱신한다.
2. 다음 작업의 차수를 감소시킨다.
2-1. 이전 작업의 최대 시간을 갱신하며, 다음 작업을 시각할 수 있는 최소 소요 시간을 구한다.
*/
public class Quiz1034 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] nm = br.readLine().split(" ");
		int N = Integer.parseInt(nm[0]);
		int M = Integer.parseInt(nm[1]);
		String[] bulbs = new String[N];
		Map<String, Integer> countMap = new HashMap<>();
		for(int i=0; i<N; i++) {
			String bulb = br.readLine();
			bulbs[i] = bulb;
			countMap.put(bulb, countMap.getOrDefault(bulb, 0) + 1);
		}

		int K = Integer.parseInt(br.readLine());

		int max = 0;
		for(int i=0; i<N; i++) {
			String bulb = bulbs[i];
			int offCount = 0;

			for(int j=0; j<M; j++) {
				if(bulb.charAt(j) == '0') {
					offCount++;
				}
			}

			int remain = K - offCount;
			if(remain < 0 || remain % 2 == 1) {
				continue;
			}

			max = Math.max(max, countMap.get(bulb));
		}

		System.out.println(max);
	}
}
