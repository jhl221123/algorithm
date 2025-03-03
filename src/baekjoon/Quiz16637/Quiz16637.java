package baekjoon.Quiz16637;

import java.util.*;
import java.io.*;

/*
Gold3: 괄호 추가하기 / [implementation, brute force]
1. 깊이 우선 탐색으로 모든 경우의 수를 확인한다.
2. 괄호가 없는 경우, 현재 연산자와 값으로 바로 연산한다.
3. 괄호가 있는 경우, 해당 괄호를 먼저 연산한 후 그 결과와 현재 값을 연산한다.
*/
public class Quiz16637 {

	private static List<Integer> numbers;
	private static List<Character> operations;
	private static int max = Integer.MIN_VALUE;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		String formula = br.readLine();
		numbers = new ArrayList<>();
		operations = new ArrayList<>();

		for(int i=0; i<N; i++) {
			char c = formula.charAt(i);
			if(c == '+' || c == '-' || c == '*') {
				operations.add(c);
				continue;
			}
			numbers.add(c - '0');
		}

		dfs(numbers.get(0), 0);
		System.out.println(max);
	}

	private static void dfs(int operand, int operationIdx) {
		if(operationIdx >= operations.size()) {
			max = Math.max(max, operand);
			return;
		}

		int next = numbers.get(operationIdx + 1);
		char operation = operations.get(operationIdx);
		dfs(calculate(operation, operand, next), operationIdx + 1);

		if(operationIdx + 1 < operations.size()) {
			int nNext = numbers.get(operationIdx + 2);
			char nOperation = operations.get(operationIdx + 1);
			int nResult = calculate(nOperation, next, nNext);
			dfs(calculate(operation, operand, nResult), operationIdx + 2);
		}
	}

	private static int calculate(char operation, int operand1, int operand2) {
		if(operation == '+') {
			return operand1 + operand2;
		}

		if(operation == '-') {
			return operand1 - operand2;
		}

		if(operation == '*') {
			return operand1 * operand2;
		}

		return -1;
	}
}
