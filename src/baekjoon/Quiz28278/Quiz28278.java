package baekjoon.Quiz28278;

import java.io.*;
import java.util.*;

/*
Silver4: 스택 2 / [data structure]
*/
public class Quiz28278 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();

		ArrayDeque<Integer> ad = new ArrayDeque<>();
		for(int i=0; i<N; i++) {
			String[] cmd = br.readLine().split(" ");
			if("1".equals(cmd[0])) {
				ad.addFirst(Integer.parseInt(cmd[1]));
				continue;
			} else if("2".equals(cmd[0])) {
				if(ad.isEmpty()) {
					sb.append(-1);
				} else {
					sb.append(ad.removeFirst());
				}
			} else if("3".equals(cmd[0])) {
				sb.append(ad.size());
			} else if("4".equals(cmd[0])) {
				if(ad.isEmpty()) {
					sb.append(1);
				} else {
					sb.append(0);
				}
			} else if("5".equals(cmd[0])) {
				if(ad.isEmpty()) {
					sb.append(-1);
				} else {
					sb.append(ad.getFirst());
				}
			}
			sb.append("\n");
		}

		System.out.print(sb);
	}
}
