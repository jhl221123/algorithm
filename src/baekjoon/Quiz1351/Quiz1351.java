package baekjoon.Quiz1351;

import java.util.*;
import java.io.*;

/*
Gold5: 무한 수열 / [dp, hash]
1. P와 Q로 나눈 값을 분할 및 dp로 관리하고 더해나간다.
2. 공간을 절약하기 위해 배열 대신 맵을 활용해 dp를 관리한다.
*/
public class Quiz1351 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] npq = br.readLine().split(" ");
		long N = Long.parseLong(npq[0]);
		long P = Long.parseLong(npq[1]);
		long Q = Long.parseLong(npq[2]);

		Map<Long, Long> dp = new HashMap<>();
		System.out.println(dfs(dp, N, P, Q));
	}

	private static long dfs(Map<Long, Long> map, long number, long P, long Q) {
		if(number == 0) {
			return 1L;
		}

		if(map.containsKey(number)) {
			return map.get(number);
		}

		long partByP = number / P;
		long partByQ = number / Q;

		map.put(number, dfs(map, partByP, P, Q) + dfs(map, partByQ, P, Q));
		return map.get(number);
	}
}
