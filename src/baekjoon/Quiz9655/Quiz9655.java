package baekjoon.Quiz9655;

import java.io.*;

/*
Silver5: 돌 게임 / [game theory]
*/
public class Quiz9655 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		if(N % 2 == 1) {
			System.out.println("SK");
		} else {
			System.out.println("CY");
		}
	}
}
