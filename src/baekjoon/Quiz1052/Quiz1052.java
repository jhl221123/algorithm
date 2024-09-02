package baekjoon.Quiz1052;

import java.util.*;
import java.io.*;

public class Quiz1052 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		int answer = 0;
		int idx = -1;

		String binary = Integer.toBinaryString(N);
		int cnt = Integer.bitCount(N);
		if(cnt > K) {
			for(int i=0; i<binary.length(); i++) {
				if(K==0) {
					idx = i;
					break;
				}
				if(binary.charAt(i) == '1') K--;
			}
			String sub = binary.substring(idx);
			int num = Integer.parseInt(sub, 2);
			answer = (int)(Math.pow(2, binary.length() - idx) - num);
		}

		System.out.println(answer);
	}
}
