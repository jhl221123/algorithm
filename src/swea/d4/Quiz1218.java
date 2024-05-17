package swea.d4;

import java.util.*;
import java.io.FileInputStream;

public class Quiz1218 {
	public static void main(String[] args) throws Exception {
		Scanner sc = new Scanner(System.in);
		int T = 10;

		for (int test_case = 1; test_case <= T; test_case++) {
			int N = sc.nextInt();
			String str = sc.next();
			Deque<Character> deque = new ArrayDeque<>();
			boolean flag = false;
			int result = 0;
			for (int i = 0; i < N; i++) {
				char target = str.charAt(i);
				if (isOpen(target))
					deque.addFirst(target); // 여는 괄호들은 스택으로
				else { // 닫는 괄호들은 확인
					// 짝이 맞는 경우
					if (deque.getFirst() == transToPair(target))
						deque.removeFirst();
						// 짝이 맞지 않는 경우
					else {
						flag = true;
						break;
					}
				}
			}

			// 마지막에 스택에 괄호가 남아있다면 실패
			// 스택이 비어있고, 짝이 모두 맞는 경우
			if (!flag && deque.isEmpty())
				result = 1;
			System.out.println("#" + test_case + " " + result);
		}
	}

	private static boolean isOpen(char target) {
		return target == '(' || target == '[' || target == '{' || target == '<';
	}

	private static char transToPair(char target) {
		if (target == ')')
			return '(';
		else if (target == ']')
			return '[';
		else if (target == '}')
			return '{';
		else
			return '<';
	}
}
