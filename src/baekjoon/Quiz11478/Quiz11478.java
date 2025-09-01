package baekjoon.Quiz11478;

import java.io.*;
import java.util.*;

/*
Silver3: 서로 다른 부분 문자열의 개수 / [data structure]
*/
public class Quiz11478 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String str = br.readLine();
		int N = str.length();

		Set<String> set = new HashSet<>();
		for(int len=1; len<=N; len++) {
			for(int s=0; s<N-len+1; s++) {
				set.add(str.substring(s, s + len));
			}
		}

		System.out.println(set.size());
	}
}
