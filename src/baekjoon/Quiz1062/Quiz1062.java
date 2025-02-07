package baekjoon.Quiz1062;

import java.io.*;
import java.util.*;

public class Quiz1062 {

	private static int N, K, max;
	private static String[] words;
	private static boolean[] isReadableLetter;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		words = new String[N];
		for(int i=0; i<N; i++) {
			String word = br.readLine();
			words[i] = word.substring(4, word.length() - 4);
		}

		if(K < 5) {
			System.out.println(0);
			return;
		}

		if (K == 26) {
			System.out.println(N);
			return;
		}

		initializeReadableLetter();
		countMaximumReadableWords(5, 0);
		System.out.println(max);
	}

	private static void countMaximumReadableWords(int selectedCount, int idx) {
		if(selectedCount == K) {
			int count = countReadableWords();
			max = Math.max(max, count);
			return;
		}

		for(int i=idx; i<26; i++) {
			if(!isReadableLetter[i]) {
				isReadableLetter[i] = true;
				countMaximumReadableWords(selectedCount + 1, i + 1);
				isReadableLetter[i] = false;
			}
		}
	}

	private static int countReadableWords() {
		int count = 0;

		for (int i = 0; i < words.length; i++) {
			if (isReadableWord(words[i])) count++;
		}

		return count;
	}

	private static boolean isReadableWord(String word) {
		for (int i = 0; i < word.length(); i++) {
			if (!isReadableLetter[word.charAt(i) - 'a']) {
				return false;
			}
		}

		return true;
	}

	private static void initializeReadableLetter() {
		isReadableLetter = new boolean[26];
		isReadableLetter['a' - 'a'] = true;
		isReadableLetter['c' - 'a'] = true;
		isReadableLetter['i' - 'a'] = true;
		isReadableLetter['n' - 'a'] = true;
		isReadableLetter['t' - 'a'] = true;
	}
}
