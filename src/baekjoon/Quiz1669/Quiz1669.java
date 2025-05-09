package baekjoon.Quiz1669;

import java.io.*;

/*
Gold5: 멍멍이 쓰다듬기 / [math]
1. 원숭이와 멍멍이의 키차이가 N * N + N 보다 커질 때까지 N을 증가시킨다.
2. N * 2가 최소 일수가 되며, 결과에서 N을 뺀 값이 키차이보다 크다면 일수를 1 감소시킨다.
*/
public class Quiz1669 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] md = br.readLine().split(" ");
		int monkey = Integer.parseInt(md[0]);
		int dog = Integer.parseInt(md[1]);
		int gap = dog - monkey;

		if(gap == 0) {
			System.out.println(0);
			return;
		}

		long half = 1;
		long dist = 0;
		while(true) {
			dist = half * half + half;
			if(dist >= gap) break;
			half++;
		}

		long min = half * 2;
		if(dist - half >= gap) {
			min--;
		}

		System.out.println(min);
	}
}
