package baekjoon.Quiz1019;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Quiz1019 {
	static long[] cnt;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		long start = 1;
		long end = Long.parseLong(br.readLine());
		cnt = new long[10];
		long digit = 1;
		while(start <= end) {
			while(start % 10 != 0 && start <= end) {
				count(start++, digit);
			}
			while(end % 10 != 9 && start <= end) {
				count(end--, digit);
			}
			if(start > end) break;
			start /= 10;
			end /= 10;
			for(int i=0; i<10; i++) {
				cnt[i] += (end - start +1) * digit;
			}
			digit *= 10;
		}
		StringBuilder sb = new StringBuilder();
		for(long num : cnt) {
			sb.append(num).append(" ");
		}
		System.out.println(sb);
	}
	private static void count(long num, long digit) {
		while(num > 0) {
			int idx = (int)(num % 10);
			cnt[idx] += digit;
			num /= 10;
		}
	}
}
