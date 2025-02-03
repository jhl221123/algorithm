package baekjoon.Quiz7662;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class Quiz7662 {

	private static final long EMPTY = Long.MAX_VALUE;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();

		while(T-- > 0) {
			PriorityQueue<Integer> asc = new PriorityQueue<>(Comparator.comparingInt(o -> o));
			PriorityQueue<Integer> desc = new PriorityQueue<>(Collections.reverseOrder());
			Map<Integer, Integer> counts = new HashMap<>();

			int k = Integer.parseInt(br.readLine());
			while(k-- > 0) {
				String[] input = br.readLine().split(" ");
				String operator = input[0];
				Integer number = Integer.parseInt(input[1]);

				if("I".equals(operator)) {
					asc.offer(number);
					desc.offer(number);
					counts.put(number, counts.getOrDefault(number, 0) + 1);
				} else if("D".equals(operator)) {
					if(number > 0) {
						remove(desc, counts);
					}

					if(number < 0) {
						remove(asc, counts);
					}
				}
			}

			long min = remove(asc, counts);
			long max = remove(desc, counts);

			if(min == EMPTY) {
				sb.append("EMPTY\n");
				continue;
			}

			if(max == EMPTY) {
				max = min;
			}

			sb.append(max).append(" ").append(min).append("\n");
		}

		System.out.print(sb);
	}

	private static long remove(PriorityQueue<Integer> queue, Map<Integer, Integer> counts) {
		while(!queue.isEmpty()) {
			Integer number = queue.poll();
			Integer count = counts.getOrDefault(number, 0);
			if(count == 0) continue;

			if(count == 1) {
				counts.remove(number);
				return number;
			}

			counts.put(number, count - 1);
			return number;
		}

		return EMPTY;
	}
}
