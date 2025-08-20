package baekjoon.Quiz16953;

import java.io.*;
import java.util.*;

/*
Silver2: A → B / [bfs]
*/
public class Quiz16953 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] ab = br.readLine().split(" ");
		long A = Long.parseLong(ab[0]);
		long B = Long.parseLong(ab[1]);

		ArrayDeque<long[]> ad = new ArrayDeque<>();
		ad.addLast(new long[] {A, 0});

		long ans = -2;
		while(!ad.isEmpty()) {
			long[] node = ad.removeFirst();
			long num = node[0];
			long cnt = node[1];

			if(num > B) continue;
			if(num == B) {
				ans = cnt;
				break;
			}

			ad.addLast(new long[] {num * 2, cnt + 1});
			ad.addLast(new long[] {num * 10 + 1, cnt + 1});
		}

		System.out.println(ans + 1);
	}
}
