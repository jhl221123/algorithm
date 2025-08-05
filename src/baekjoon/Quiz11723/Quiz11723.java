package baekjoon.Quiz11723;

import java.io.*;

/*
Silver5: 집합 / [data structure]
*/
public class Quiz11723 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int M = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();

		boolean[] arr = new boolean[21];
		while(M-- > 0) {
			String[] cmd = br.readLine().split(" ");
			if("add".equals(cmd[0])) {
				int num = Integer.parseInt(cmd[1]);
				arr[num] = true;
			} else if("remove".equals(cmd[0])) {
				int num = Integer.parseInt(cmd[1]);
				arr[num] = false;
			} else if("check".equals(cmd[0])) {
				int num = Integer.parseInt(cmd[1]);
				sb.append(arr[num] ? 1 : 0).append("\n");
			} else if("toggle".equals(cmd[0])) {
				int num = Integer.parseInt(cmd[1]);
				arr[num] = !arr[num];
			} else if("all".equals(cmd[0])) {
				for(int i=1; i<=20; i++) {
					arr[i] = true;
				}
			} else if("empty".equals(cmd[0])) {
				for(int i=1; i<=20; i++) {
					arr[i] = false;
				}
			}
		}

		System.out.print(sb);
	}
}
