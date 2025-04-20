package baekjoon.Quiz1722;

import java.util.*;
import java.io.*;

/*
Gold5: 순열의 순서 / [math, brute-force]
1. k번째 순열은 (N-1)! 부터 1! 단위로 소거하며 각 위치의 원소를 찾는다.
2. 순열의 순서는 각 원소의 순서 * fac을 더하면서 찾는다.
*/
public class Quiz1722 {

	private static long[] factorial;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		factorial = new long[N + 1];
		long[] cmd = Arrays.stream(br.readLine().split(" "))
			.mapToLong(Long::parseLong)
			.toArray();

		LinkedList<Integer> list = new LinkedList<>();
		for(int i=1; i<=N; i++) {
			list.add(i);
		}

		if(cmd[0] == 1) {
			long order = cmd[1];
			StringBuilder sb = new StringBuilder();

			for(int i=N-1; i>0; i--) {
				long curFac = factorial(i);

				for(int j=0; j<list.size(); j++) {
					if(order <= curFac) {
						sb.append(list.remove(j)).append(" ");
						break;
					}
					order -= curFac;
				}
			}

			sb.append(list.remove(0)).append(" ");
			System.out.println(sb);
		} else {
			long order = 1;

			for(int i=1, j=N-1; i<N; i++, j--) {
				int idx = list.indexOf((int)cmd[i]);
				order += idx * factorial(j);
				list.remove(Integer.valueOf((int)cmd[i]));
			}

			System.out.println(order);
		}
	}

	private static long factorial(int num) {
		if(factorial[num] > 0) return factorial[num];
		if(num == 1) return 1;
		else return factorial[num] = num * factorial(num - 1);
	}
}
