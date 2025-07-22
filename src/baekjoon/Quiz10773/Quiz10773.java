package baekjoon.Quiz10773;

import java.io.*;
import java.util.*;

/*
Silver4: 제로 / [data-structure]
*/
public class Quiz10773 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int K = Integer.parseInt(br.readLine());
		ArrayDeque<Integer> ad = new ArrayDeque<>();
		for(int i=0; i<K; i++) {
			int num = Integer.parseInt(br.readLine());
			if(num == 0 && !ad.isEmpty()) {
				ad.removeFirst();
			} else {
				ad.addFirst(num);
			}
		}

		int sum = 0;
		while(!ad.isEmpty()) {
			sum += ad.removeFirst();
		}
		System.out.println(sum);
	}
}
