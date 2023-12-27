package baekjoon.Quiz1339;

import java.util.Arrays;
import java.util.Scanner;

// 전체 시간 복잡도: O(N*L), L: 단어 길이
public class Quiz1339 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int[] alphabet = new int[26];
		int ans = 0;
		while(N-- > 0) {
			char[] word = sc.next().toCharArray();
			int score = 1;
			// 각 단어별 사용된 자릿수를 먼저 파악한다.
			for(int i=word.length-1; i>=0; i--) {
				alphabet[word[i]-'A'] += score;
				score *= 10;
			}
		}
		Arrays.sort(alphabet);
		// 자릿수 비중이 큰 순서대로 번호를 부여하면서 답을 도출한다.
		for(int i=0; i<=9; i++) {
			ans += alphabet[25-i] * (9-i);
		}
		System.out.println(ans);
	}
}
