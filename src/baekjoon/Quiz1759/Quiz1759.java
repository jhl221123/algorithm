package baekjoon.Quiz1759;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Scanner;

// 전체 시간 복잡도: O(C!/L!*(C-L)!)
public class Quiz1759 {
	static int L;
	static char[] nums;
	static LinkedList<Character> code;
	static LinkedList<Character> consonant;
	static LinkedList<Character> vowel;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		L = sc.nextInt();
		int C = sc.nextInt();
		nums = new char[C];
		for(int i=0; i<C; i++) {
			nums[i] = sc.next().charAt(0);
		}
		Arrays.sort(nums);
		code = new LinkedList<>();
		consonant = new LinkedList<>();
		vowel = new LinkedList<>();
		StringBuilder sb = new StringBuilder();
		recur(0, sb);
		System.out.print(sb);
	}
	// 1. base 실행 조건
	// 1-1. 각 배열의 모음과 자음 조건을 충족
	// 1-2. L개의 문자를 충족
	// 2. recur 실행 로직
	// 2-1. 암호 배열에 문자 추가
	// 2-2. 모음과 자음 배열에 추가
	// 2-3. C배열의 다음 idx로 recur 실행
	// 2-4. 암호 배열에서 제거
	// 2-5. 모음, 자음 배열에서 제거
	private static void recur(int start, StringBuilder sb) {
		if (code.size() == L && consonant.size() >= 2 && !vowel.isEmpty()) {
			code.stream().forEach(sb::append);
			sb.append("\n");
			return;
		}
		for(int i=start; i<nums.length; i++) {
			char target = nums[i];
			code.add(target);
			if(isConsonant(target)) consonant.add(target);
			else vowel.add(target);
			recur(i+1, sb);
			if(isConsonant(target)) consonant.removeLast();
			else vowel.removeLast();
			code.removeLast();
		}
	}
	private static boolean isConsonant(char target) {
		return target != 'a' && target != 'e' && target != 'i' && target != 'o' && target != 'u';
	}
}
