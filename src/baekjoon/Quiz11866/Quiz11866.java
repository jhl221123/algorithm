package baekjoon.Quiz11866;

import java.io.*;
import java.util.*;

/*
Silver4: 요세푸스 문제 0 / [data-structure]
*/
public class Quiz11866 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] nk = br.readLine().split(" ");
		int N = Integer.parseInt(nk[0]);
		int K = Integer.parseInt(nk[1]);
		ArrayDeque<Integer> l = new ArrayDeque<>();
		ArrayDeque<Integer> r = new ArrayDeque<>();
		for(int i=1; i<=N; i++) {
			l.addLast(i);
		}

		StringBuilder sb = new StringBuilder();
		sb.append("<");
		boolean flag = true;
		int count = 1;
		while(true) {
			if(l.isEmpty() && r.isEmpty()) break;
			if(flag) {
				if(l.isEmpty()) {
					flag = !flag;
					continue;
				}
				Integer num = l.removeFirst();
				if(count % K != 0) {
					r.addLast(num);
				} else {
					sb.append(num).append(", ");
				}
				count++;
			} else {
				if(r.isEmpty()) {
					flag = !flag;
					continue;
				}
				Integer num = r.removeFirst();
				if(count % K != 0) {
					l.addLast(num);
				} else {
					sb.append(num).append(", ");
				}
				count++;
			}
		}
		sb.setLength(sb.length() - 2);
		System.out.println(sb.append(">"));
	}
}
