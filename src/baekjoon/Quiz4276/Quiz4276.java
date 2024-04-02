package baekjoon.Quiz4276;

import java.util.*;
import java.io.*;

public class Quiz4276 {
	static long[] cnt;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		while(true) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			long start = Long.parseLong(st.nextToken());
			long end = Long.parseLong(st.nextToken());
			if(start < 0) break;
			boolean flag = false;
			if(start == 0) {
				flag = true;
				start = 1;
			}
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
					cnt[i] += (end - start + 1) * digit;
				}
				digit *= 10;
			}
			if(flag) cnt[0]++;
			sb.append(cnt[0]).append("\n");
		}
		System.out.println(sb);
	}
	static void count(long num, long digit) {
		while(num > 0) {
			int idx = (int)(num%10);
			cnt[idx] += digit;
			num /= 10;
		}
	}
}
