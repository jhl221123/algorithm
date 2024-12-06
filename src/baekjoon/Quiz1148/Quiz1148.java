package baekjoon.Quiz1148;

import java.io.*;
import java.util.*;

public class Quiz1148 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		List<int[]> words = new ArrayList<>();
		StringBuilder result = new StringBuilder();
		TreeSet<String> minIdxSet = new TreeSet<>();
		TreeSet<String> maxIdxSet = new TreeSet<>();
		int[] wordCountByIdx = new int[9];
		int min;
		int max;

		while(true) {
			String word = br.readLine();
			if("-".equals(word)) break;
			int[] wordChars = countCharOf(word);
			words.add(wordChars);
		}

		// 1. 리스트 순회, 해당 문자열의 0 -> 8 (idx) 까지 순회
		while(true) {
			String puzzle = br.readLine();
			if("#".equals(puzzle)) break;

			Arrays.fill(wordCountByIdx, 0);
			int[] puzzleChars = countCharOf(puzzle);

			for(int idx=0; idx<9; idx++) {

				// 2. 단어 사전을 순회
				for(int[] wordChars : words) {
					// 3. 단어가 문자열의 idx 값을 포함한다면 해당 단어를 만들 수 있는지 확인
					// 4. 만들 수 있다면 idx의 개수 증가
					if(wordChars[puzzle.charAt(idx) - 'A'] > 0
						&& possibleCreate(wordChars, puzzleChars)) {
						wordCountByIdx[idx]++;
					}

				}
			}

			// 5. 개수가 가장 작은 idx와 큰 idx를 추출
			min = 200001;
			max = 0;
			for(int idx=0; idx<9; idx++) {
				min = Math.min(min, wordCountByIdx[idx]);
				max = Math.max(max, wordCountByIdx[idx]);
			}

			for(int idx=0; idx<9; idx++) {
				if(wordCountByIdx[idx] == min) {
					minIdxSet.add(String.valueOf(puzzle.charAt(idx)));
				}
				if(wordCountByIdx[idx] == max) {
					maxIdxSet.add(String.valueOf(puzzle.charAt(idx)));
				}
			}

			for(String minIdx : minIdxSet) {
				result.append(minIdx);
			}

			result.append(" ").append(min).append(" ");

			for(String maxIdx : maxIdxSet) {
				result.append(maxIdx);
			}

			result.append(" ").append(max).append("\n");

			minIdxSet.clear();
			maxIdxSet.clear();
		}

		System.out.print(result);
	}

	private static int[] countCharOf(String target) {
		int[] alphabetCount = new int[26];
		for(int i=0; i<target.length(); i++) {
			alphabetCount[target.charAt(i) - 'A']++;
		}
		return alphabetCount;
	}

	private static boolean possibleCreate(int[] wordChars, int[] puzzleChars) {
		for(int i=0; i<wordChars.length; i++) {
			if(wordChars[i] > puzzleChars[i]) return false;
		}
		return true;
	}
}
