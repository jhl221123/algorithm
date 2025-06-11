package baekjoon.Quiz25401;

import java.io.*;
import java.util.*;

/*
Gold5: 카드 바꾸기 / [dp]
*/
public class Quiz25401 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int N = Integer.parseInt(br.readLine());
		int[] cards = new int[N];

		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			cards[i] = Integer.parseInt(st.nextToken());
		}

		int min = N - 1;
		for (int i = 0; i < N; i++) {
			for (int j = i + 1; j < N; j++) {
				int diff = cards[j] - cards[i];
				int dist = j - i;

				int d = diff / dist;
				int count = 0;

				for (int n = 0; n < N; n++) {
					int a1 = cards[i];
					int an = a1 + (n - i) * d;
					if (cards[n] != an) {
						count++;
					}
				}

				min = Math.min(min, count);
			}
		}

		System.out.println(min);
	}
}
