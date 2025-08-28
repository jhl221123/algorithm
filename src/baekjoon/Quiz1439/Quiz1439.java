package baekjoon.Quiz1439;

import java.io.*;

/*
Silver5: 뒤집기 / [greedy]
*/
public class Quiz1439 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String str = br.readLine();
		int prev = str.charAt(0) - '0';
		int[] counts = new int[2];
		counts[prev]++;
		for(int i=1; i<str.length(); i++) {
			int next = str.charAt(i) - '0';
			if(next != prev) {
				counts[next]++;
				prev = next;
			}
		}

		System.out.println(Math.min(counts[0], counts[1]));
	}
}
