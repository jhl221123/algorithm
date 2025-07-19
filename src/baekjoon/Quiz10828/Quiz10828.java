package baekjoon.Quiz10828;

import java.io.*;
import java.util.*;

/*
Silver4: 스택 / [dp]
*/
public class Quiz10828 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		ArrayDeque<Integer> ad = new ArrayDeque<>();

		StringBuilder sb = new StringBuilder();
		while(N-- > 0) {
			String[] cmd = br.readLine().split(" ");
			String c = cmd[0];

			if("push".equals(c)) {
				ad.addFirst(Integer.parseInt(cmd[1]));
			} else if("pop".equals(c)) {
				if(ad.isEmpty()) {
					sb.append(-1);
				} else {
					sb.append(ad.removeFirst());
				}
				sb.append("\n");
			} else if("size".equals(c)) {
				sb.append(ad.size()).append("\n");
			} else if("empty".equals(c)) {
				if(ad.isEmpty()) {
					sb.append(1);
				} else {
					sb.append(0);
				}
				sb.append("\n");
			} else if("top".equals(c)) {
				if(ad.isEmpty()) {
					sb.append(-1);
				} else {
					sb.append(ad.getFirst());
				}
				sb.append("\n");
			}
		}
		System.out.print(sb);
	}
}
