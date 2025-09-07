package baekjoon.Quiz10610;

import java.io.*;

/*
Silver4: 30 / [greedy]
*/
public class Quiz10610 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String N = br.readLine();
		boolean flag = false;
		int sum = 0;
		int[] arr = new int[10];
		for(int i=0; i<N.length(); i++) {
			int n = N.charAt(i) - '0';
			arr[n] += 1;
			sum += n;
			if(n == 0) {
				flag = true;
			}
		}
		if(!flag) {
			System.out.println(-1);
		} else {
			if(sum % 3 != 0) {
				System.out.println(-1);
			} else {
				StringBuilder sb = new StringBuilder();
				for(int i=9; i>=0; i--) {
					for(int j=0; j<arr[i]; j++) {
						sb.append(i);
					}
				}
				System.out.println(sb);
			}
		}
	}
}
