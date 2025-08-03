package baekjoon.Quiz18258;

import java.io.*;
import java.util.*;

/*
Silver4: 큐 2 / [data structure]
*/
public class Quiz18258 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();

		ArrayDeque<Integer> ad = new ArrayDeque<>();
		while(N-- > 0) {
			String[] cmd = br.readLine().split(" ");
			if("push".equals(cmd[0])) {
				ad.addLast(Integer.parseInt(cmd[1]));
			} else if("pop".equals(cmd[0])) {
				int target = ad.isEmpty() ? -1 : ad.removeFirst();
				sb.append(target).append("\n");
			} else if("size".equals(cmd[0])) {
				sb.append(ad.size()).append("\n");
			} else if("empty".equals(cmd[0])) {
				int target = ad.isEmpty() ? 1 : 0;
				sb.append(target).append("\n");
			} else if("front".equals(cmd[0])) {
				int target = ad.isEmpty() ? -1 : ad.getFirst();
				sb.append(target).append("\n");
			} else if("back".equals(cmd[0])) {
				int target = ad.isEmpty() ? -1 : ad.getLast();
				sb.append(target).append("\n");
			}
		}

		System.out.print(sb);
	}
}
