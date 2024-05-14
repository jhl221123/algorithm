package baekjoon.Quiz1436;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Quiz1436 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int cnt = 0;
		for(int i=666; i<=3000000; i++) {
			if(String.valueOf(i).contains("666")) cnt++;
			if(cnt == N) {
				System.out.println(i);
				break;
			}
		}
	}
}
