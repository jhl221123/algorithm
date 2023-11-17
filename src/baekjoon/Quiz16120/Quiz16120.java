package baekjoon.Quiz16120;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// 전체 시간 복잡도: O(L)
public class Quiz16120 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String str = br.readLine();
		char[] temp = new char[str.length()];
		int len = -1;
		for(int i=0; i<str.length(); i++) {
			temp[++len] = str.charAt(i);
			if(len >=3
				&& temp[len] == 'P'
				&& temp[len-1] == 'A'
				&& temp[len-2] == 'P'
				&& temp[len-3] == 'P') len -=3;
		}
		StringBuilder sb = new StringBuilder();
		for(int i=0; i<=len; i++) {
			sb.append(temp[i]);
		}
		if(sb.toString().equals("P")) System.out.println("PPAP");
		else  System.out.println("NP");
	}
}
