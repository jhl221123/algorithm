package baekjoon.Quiz1021;

import java.io.*;
import java.util.*;

/*
Silver3: 회전하는 큐 / [data-structure]
*/
public class Quiz1021 {
	public static void main(String[] arg) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] nm = br.readLine().split(" ");
		int N = Integer.parseInt(nm[0]);
		int M = Integer.parseInt(nm[1]);

		int[] targets = Arrays.stream(br.readLine().split(" "))
			.mapToInt(Integer::parseInt)
			.toArray();

		ArrayDeque<Integer> ad = new ArrayDeque<>();
		for(int i=1; i<=N; i++) {
			ad.addLast(i);
		}

		int count = 0;
		for(int i=0; i<M; i++) {
			int target = targets[i];

			ArrayDeque<Integer> l = new ArrayDeque<>();
			ArrayDeque<Integer> r = new ArrayDeque<>();
			while(true) {
				if(!ad.isEmpty()) {
					l.addLast(ad.removeFirst());
				}

				if(!l.isEmpty() && l.getLast() == target) {
					l.removeLast();
					while(!r.isEmpty()) {
						ad.addLast(r.removeFirst());
					}
					while(!l.isEmpty()) {
						count++;
						ad.addLast(l.removeFirst());
					}
					break;
				}

				if(!ad.isEmpty()) {
					r.addFirst(ad.removeLast());
				}

				if(!r.isEmpty() && r.getFirst() == target) {
					r.removeFirst();
					count++;
					while(!l.isEmpty()) {
						ad.addFirst(l.removeLast());
					}
					while(!r.isEmpty()) {
						count++;
						ad.addFirst(r.removeLast());
					}
					break;
				}
			}
		}

		System.out.println(count);
	}
}
