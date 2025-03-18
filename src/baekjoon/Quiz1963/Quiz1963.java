package baekjoon.Quiz1963;

import java.util.*;
import java.io.*;

/*
Gold4: 소수 경로 / [math, bfs, sieve of eratosthenes]
1. 네 자리의 소수를 모두 구한다.
2. 각 자리수를 변경하며 소수인 경우만 탐색을 이어한다.
3. 목표 비밀번호에 도달했다면 이동 횟수를, 도달하지 못했다면 문자열을 출력한다.
*/
public class Quiz1963 {

	private static boolean[] isPrime;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		initializePrimes();
		StringBuilder sb = new StringBuilder();

		while(T-- > 0) {
			String[] source = br.readLine().split(" ");
			int from = Integer.parseInt(source[0]);
			int to = Integer.parseInt(source[1]);

			sb.append(calculateConversionCount(from, to)).append("\n");
		}

		System.out.println(sb);
	}

	private static void initializePrimes() {
		isPrime = new boolean[10000];
		Arrays.fill(isPrime, true);

		for(int i=2; i<10000; i++) {
			if(isPrime[i]) {
				for(int j=i*i; j<10000; j+=i) {
					isPrime[j] = false;
				}
			}
		}
	}

	private static String calculateConversionCount(int from, int to) {
		ArrayDeque<Integer> ad = new ArrayDeque<>();
		Map<Integer, Integer> visited = new HashMap<>();

		ad.addLast(from);
		visited.put(from, 0);
		while(!ad.isEmpty()) {
			int num = ad.removeFirst();
			int count = visited.get(num);

			if(num == to) {
				return String.valueOf(count);
			}

			int[] digits = {num / 1000, (num / 100) % 10, (num / 10) % 10, num % 10};

			for(int i=0; i<4; i++) {
				int temp = digits[i];

				for(int j=0; j<10; j++) {
					if(i==0 && j==0) {
						continue;
					}

					digits[i] = j;
					int next = toInt(digits);

					if(!isPrime[next]) {
						continue;
					}

					if(!visited.containsKey(next)) {
						ad.addLast(next);
						visited.put(next, count + 1);
					}
				}

				digits[i] = temp;
			}
		}

		return "Impossible";
	}

	private static int toInt(int[] digits) {
		int num = 0;

		for(int i=0, exp=3; i<4; i++, exp--) {
			num += digits[i] * Math.pow(10, exp);
		}

		return num;
	}
}
