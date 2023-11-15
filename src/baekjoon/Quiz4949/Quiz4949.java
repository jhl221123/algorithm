package baekjoon.Quiz4949;

import java.util.ArrayDeque;
import java.util.Scanner;

// 전체 시간 복잡도: O(N*L), 문자 개수: N, 문자열 길이: L
public class Quiz4949 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		StringBuilder sb = new StringBuilder();
		while(true) {
			String str = sc.nextLine();
			if(str.equals(".")) break;
			// 1. 입력받은 문자열의 문자 하나씩 순회한다.
			ArrayDeque<Character> ad = new ArrayDeque<>();
			boolean flag = false;
			for(int i=0; i<str.length(); i++) {
				// 2. 문자가 '(' 또는 '[' 라면 ad에 입력한다.
				char target = str.charAt(i);
				if(target=='(' || target=='[') ad.addLast(target);
				// 3. 문자가 ')' 또는 ']' 라면 ad 최상단 요소를 확인하고 제거한다.
				// 3-1. ad가 비어있거나, 두 가지 문자가 짝을 이루지 않는다면 no를 반환한다.
				if(target==')' || target==']') {
					if(ad.isEmpty()) {
						flag = true;
						break;
					}
					if(!isPair(target, ad.removeLast())) {
						flag = true;
						break;
					}
				}
			}
			// 4. 마지막에 ad가 비어있지 않다면 no를 반환한다.
			if(!ad.isEmpty()) flag = true;
			if(flag) sb.append("no\n");
			else sb.append("yes\n");
		}
		System.out.println(sb);
	}
	public static boolean isPair(char target, char pair) {
		return (target == ')' && pair == '(') || (target == ']' && pair == '[');
	}
}
