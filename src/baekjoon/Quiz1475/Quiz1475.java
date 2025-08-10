package baekjoon.Quiz1475;

import java.io.*;

/*
Silver5: 방 번호 / [implementation]
*/
public class Quiz1475 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String N = br.readLine();
		int[] arr = new int[9];
		for(int i=0; i<N.length(); i++) {
			int digit = N.charAt(i) - '0';
			if(digit == 9) digit = 6;
			arr[digit]++;
		}
		arr[6] = (arr[6] + 1) / 2;

		int min = 0;
		for(int i=0; i<9; i++) {
			min = Math.max(min, arr[i]);
		}
		System.out.println(min);
	}
}
