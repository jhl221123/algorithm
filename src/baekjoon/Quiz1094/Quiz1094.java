package baekjoon.Quiz1094;

import java.io.*;

/*
Silver5: 막대기 / [math]
*/
public class Quiz1094 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int count = 0;
		for(int i=6; i>=0; i--) {
			int n = (int) Math.pow(2, i);
			if(N >= n) {
				count++;
				N -= n;
			}
		}

		System.out.println(count);
	}
}
