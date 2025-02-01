package baekjoon.Quiz1918;

import java.util.*;
import java.io.*;

/*
1. 피연산자는 바로 출력한다.
2. 여는 괄호는 조건 없이 연산자 스택에 넣는다.
3. 닫는 괄호가 나오면, 연산자 스택에서 여는 괄호가 나올때까지 출력한다.
4. '*', '/', '+', '-'는 우선 순위에 따라 출력한다.
5. 스택에 남아있는 연산자들을 출력한다.
 */

public class Quiz1918 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String str = br.readLine();
		System.out.println(convertToPostfix(str));
	}

	private static String convertToPostfix(String str) {
		Deque<Character> operators = new ArrayDeque<>();
		StringBuilder result = new StringBuilder();

		for (char c : str.toCharArray()) {
			if (Character.isUpperCase(c)) {
				result.append(c);
				continue;
			}

			if (c == '(') {
				operators.addFirst(c);
				continue;
			}

			if (c == ')') {
				while (!operators.isEmpty() && operators.peekFirst() != '(') {
					result.append(operators.removeFirst());
				}
				operators.pollFirst();
				continue;
			}

			while (!operators.isEmpty() && precedence(operators.peekFirst()) >= precedence(c)) {
				result.append(operators.removeFirst());
			}
			operators.addFirst(c);
		}

		while (!operators.isEmpty()) {
			result.append(operators.removeFirst());
		}

		return result.toString();
	}

	private static int precedence(char op) {
		return switch (op) {
			case '*', '/' -> 2;
			case '+', '-' -> 1;
			default -> 0;
		};
	}
}
