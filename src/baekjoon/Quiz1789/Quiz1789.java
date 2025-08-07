package baekjoon.Quiz1789;

import java.io.*;

/*
Silver5: 수들의 합 / [greedy]
*/
public class Quiz1789 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		long N = Long.parseLong(br.readLine());

		int cnt = 0;
		long num = 1;
		while(true) {
			N -= num;
			cnt++;
			if(N <= num) {
				break;
			}
			num++;
		}

		System.out.println(cnt);
	}
}
