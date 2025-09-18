package baekjoon.Quiz3986;

import java.io.*;
import java.util.*;

/*
Silver4: 좋은 단어 / [data structure]
*/
public class Quiz3986 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());

		int count = 0;
		while(N-- > 0) {
			String word = br.readLine();
			ArrayDeque<Character> ad = new ArrayDeque<>();

			int length = word.length();
			for(int i=0; i<length; i++) {
				if(ad.isEmpty()) {
					ad.addLast(word.charAt(i));
				} else {
					if(ad.getLast() == word.charAt(i)) {
						ad.removeLast();
					} else {
						ad.addLast(word.charAt(i));
					}
				}
			}

			if(ad.isEmpty()) {
				count++;
			}
		}

		System.out.println(count);
	}
}
